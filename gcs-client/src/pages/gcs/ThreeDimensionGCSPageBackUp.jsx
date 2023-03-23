import ApplicationContainer from "../../components/templates/Container/MapContainer";
import AgentInfo from "../../components/molecules/AgentInfo/AgentInfo";
import React, { useCallback, useEffect, useRef, useState } from "react";
import styled from "styled-components";
import { useDispatch, useSelector } from "react-redux";
import { NavLink } from "react-router-dom";
import { Canvas } from "@react-three/fiber";
import { OrbitControls, PerspectiveCamera, Sky } from "@react-three/drei";
import {context as contextStore, fetchContext} from "../../store/context";
import { Physics } from "@react-three/cannon";
import * as StompJs from '@stomp/stompjs';
import SockJs from "sockjs-client";
import GcsInfo from "../../components/molecules/SideController/GcsInfo";
import SimpleDrone from "../../components/atoms/three/SimpleDrone";
import { Vector3 } from "three";
import Sidebar from "../../components/templates/Sidebar/Sidebar";
import Colors from "../../styles/colors";
import GcsHeader from "../../components/templates/Header/GcsHeader";
import Spinner from "../../components/atoms/Spinner/Spinner";

const ThreeDimensionGCSPage = () => {
  const dispatch = useDispatch();
  const {loading, data: context} = useSelector((state) => state.context);
  const [socketLoading, isSocketLoading] = useState(true);
  const [center, setCenter] = useState({
    lat: process.env.REACT_APP_BASE_LAT,
    lng: process.env.REACT_APP_BASE_LNG,
    alt: process.env.REACT_APP_BASE_ALT
  });

  const cameraRef = useRef();
  const controlRef = useRef();
  const client = new StompJs.Client({
    brokerURL: `${process.env.REACT_APP_REALTIME_END_POINT}/api/realtime/info`,
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
  })

  const wsSubscribe = () => {
    client.onConnect = () => {
      client.subscribe('/topic/monitoring', (msg) => {
        const parsedMessage = JSON.parse(msg.body);

        const agent = {
          sysid: parsedMessage.sysid,
          battery: parsedMessage.battery,
          angle: {
            roll: parsedMessage.roll,
            pitch: parsedMessage.pitch,
            yaw: parsedMessage.yaw,
          },
          active: parsedMessage.active,
          ned: {
            x: parsedMessage.pos_x,
            y: parsedMessage.pos_y,
            z: parsedMessage.pos_z,
          },
          velocity: {
            vx: parsedMessage.vx,
            vy: parsedMessage.vy,
            vz: parsedMessage.vz
          }
        }

        dispatch(contextStore.actions.update({
          key: parsedMessage.sysid,
          agent: agent
        }));
      }, {id: "user"})
    }
  }

  const handleMeshClick = (event) => {
    const mesh = event.object;
    const distance = 2;
    const zoomFactor = 2;

    const cameraPosition = new Vector3().copy(mesh.position).add(new Vector3(distance, distance, distance));
    const targetPosition = mesh.position.clone();
    const distanceToMesh = cameraPosition.distanceTo(mesh.position);
    const zoom = distanceToMesh / distance * zoomFactor;

    cameraRef.current.position.copy(cameraPosition);
    controlRef.current.target.copy(targetPosition);
    controlRef.current.zoom = zoom;
    controlRef.current.update();
  }

  const handleCenterChange = useCallback((e) => {
    setCenter((c) => ({
      ...center,
      [e.target.name]: e.target.value,
    }));
  })

  useEffect(() => {
    dispatch(fetchContext())
    isSocketLoading(true);


    window.addEventListener('scroll', () => {}, { passive: true });

    if (typeof WebSocket !== 'function') {
      client.webSocketFactory = function () {
        return new SockJs(`${process.env.REACT_APP_API_END_POINT}/api/realtime/info`);
      };
    }

    client.activate();
    wsSubscribe();
    isSocketLoading(false);
  }, []);


  return(
    <ApplicationContainer>
      <MapSidebar>
        {(loading || socketLoading) ? <Spinner /> : (
          <>
            <GcsSidebarAgentListContainer>
              {context.agents && Object.values(context.agents)?.map((agent, i) => (
                <AgentInfo
                  key={i}
                  agentObject={agent}
                />
              ))}
            </GcsSidebarAgentListContainer>
            <GcsInfo
              position={center}
              onCenterChange={handleCenterChange}/>
          </>
        )}
      </MapSidebar>
      <GcsHeader/>
      <MainContainer>
        {(loading || socketLoading) ? null : (
          <ThreeContainer>
            <Canvas>
              <PerspectiveCamera
                ref={cameraRef}
                fov={75}
                aspect={window.innerWidth / window.innerHeight}
                near={0.1}
                far={1000}
                position={[0, 0, 5]}
                />
              <OrbitControls
                ref={controlRef}
                enableDamping
                dampingFactor={0.5}
                autoRoutate={true}
                minDistance={2}
                maxDistance={200}
                maxPolarAngle={Math.PI / 2 + 0.1}
                args={[cameraRef.current]}
              />
              <Sky
                distance={450000}
                sunPosition={[0, 1, 0]}
                inclination={0}
                rayleigh={0.1}
                turbidity={10}
              />
              <ambientLight intensity={0.5} />
              <axesHelper scale={1000} args={[5]}/>
              <gridHelper args={[1000, 1000]} />

              <Physics>
                {context.agents && Object.values(context.agents)?.map((agent, i) => (
                  <SimpleDrone
                    key={i}
                    ned={agent.ned}
                    agentObj={agent}
                    onClick={handleMeshClick}
                  />
                ))}
                <SimpleDrone
                  key={4}
                  agentObj={context.agents[2]}
                  ned={{x: 1, y: 3, z: 1}}
                  onClick={handleMeshClick}
                />

                {/*<ThreeGround />*/}
              </Physics>
            </Canvas>
          </ThreeContainer>
        )}
      </MainContainer>
    </ApplicationContainer>
  )
}

const GcsSidebarAgentListContainer = styled.div`
  overflow: scroll;
  height: 70%;

  ::-webkit-scrollbar {
    display: none;
  }
`

const StyledNavLink = styled(NavLink)`
  &:visited {
    color: black;
    text-decoration: none;
  }
  &:link {
    color: black;
    text-decoration: none;
  }

  &.active {
    display: inline-block;
    box-sizing: border-box;
    font-weight: 700;
  }
`;

const MainContainer = styled.div`
  align-items: center;
  height: 93vh;
`;

const ThreeContainer = styled.div`
  display: flex;
  height: 100%;
`;


const MapSidebar = styled(Sidebar)`
  background: ${Colors.backgroundSidebar}
`


export default ThreeDimensionGCSPage;