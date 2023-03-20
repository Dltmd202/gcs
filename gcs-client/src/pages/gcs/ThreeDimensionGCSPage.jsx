import ApplicationContainer from "../../components/templates/Container/MapContainer";
import AgentInfo from "../../components/molecules/AgentInfo/AgentInfo";
import React, { useCallback, useEffect, useRef, useState } from "react";
import styled from "styled-components";
import { useDispatch, useSelector } from "react-redux";
import {context as contextStore, fetchContext} from "../../store/context";
import * as StompJs from '@stomp/stompjs';
import SockJs from "sockjs-client";
import GcsInfo from "../../components/molecules/GcsInfo/GcsInfo";
import { Vector3 } from "three";
import Sidebar from "../../components/templates/Sidebar/Sidebar";
import Colors from "../../styles/colors";
import GcsHeader from "../../components/templates/Header/GcsHeader";
import Spinner from "../../components/atoms/Spinner/Spinner";
import CanvasContainer from "../../components/templates/Container/CanvasContainer";
import SimpleDrone from "../../components/atoms/three/SimpleDrone";
import Swal from "sweetalert2";
import {useNavigate} from "react-router-dom";
import SimpleDrone2 from "../../components/atoms/three/SimpleDrone2";

const ThreeDimensionGCSPage = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const {
    loading: contextLoading,
    data: context,
    error: contextError
  } = useSelector((state) => state.context);
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

  useEffect(() => {
    if(contextError){
      Swal.fire({
        icon: 'error',
        text: "Please Load Context",
        confirmButtonColor: Colors.point,
      }).then(() => {
        localStorage.clear();
        navigate('/');
      });
    }
  }, [contextError])


  const handleCenterChange = useCallback((e) => {
    setCenter((c) => ({
      ...center,
      [e.target.name]: e.target.value,
    }));
  })


  return(
    <ApplicationContainer>
      <MapSidebar>
        {(contextLoading || socketLoading) ? <Spinner /> : (
          <>
            <GcsSidebarAgentListContainer>
              {context.agents && Object.values(context.agents)?.map((agent, i) => (
                <AgentInfo key={i} agentObject={agent} />
              ))}
            </GcsSidebarAgentListContainer>
            <GcsInfo position={center} onCenterChange={handleCenterChange}
            />
          </>
        )}
      </MapSidebar>
      <GcsHeader/>
      <MainContainer>
        {(contextLoading || socketLoading) ? null : (
          <CanvasContainer cameraRef={cameraRef} controlRef={controlRef}>
            {context.agents && Object.values(context.agents)?.map((agent, i) => (
              <SimpleDrone2
                key={i}
                agentObj={agent}
                onClick={handleMeshClick}
              />
            ))}
          </CanvasContainer>
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

const MainContainer = styled.div`
  align-items: center;
  height: 93vh;
`;

const MapSidebar = styled(Sidebar)`
  background: ${Colors.backgroundSidebar}
`


export default ThreeDimensionGCSPage;