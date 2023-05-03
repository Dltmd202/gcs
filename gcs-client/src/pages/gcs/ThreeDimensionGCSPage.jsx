import ApplicationContainer from "../../components/templates/Container/MapContainer";
import AgentInfo from "../../components/molecules/AgentInfo/AgentInfo";
import React, { useCallback, useEffect, useRef, useState } from "react";
import styled from "styled-components";
import { useDispatch, useSelector } from "react-redux";
import {context as contextStore, fetchContext} from "../../store/context";
import * as StompJs from '@stomp/stompjs';
import SockJs from "sockjs-client";
import { Vector3 } from "three";
import Sidebar from "../../components/templates/Sidebar/Sidebar";
import Colors from "../../styles/colors";
import GcsHeader from "../../components/templates/Header/GcsHeader";
import Spinner from "../../components/atoms/Spinner/Spinner";
import CanvasContainer from "../../components/templates/Container/CanvasContainer";
import Swal from "sweetalert2";
import {useNavigate} from "react-router-dom";
import Logger from "../../components/molecules/logger/Logger";
import AgentDetailInfo from "../../components/molecules/AgentInfo/AgentDetailInfo";
import SimpleDrone from "../../components/atoms/three/SimpleDrone";

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
  const [focused, focus] = useState(null);


  const wsSubscribe = () => {
    client.onConnect = () => {
      client.subscribe('/topic/monitoring', (msg) => {
        const parsedMessage = JSON.parse(msg.body);
        const agent = {
          sysid: parsedMessage.sysid,
          battery: parsedMessage.battery,
          status: parsedMessage.status1,
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
          },
          rtk: {
            x: parsedMessage.rtk_n,
            y: parsedMessage.rtk_e,
            z: parsedMessage.rtk_d,
          },
          tow: parsedMessage.tow,
          param: {}
        }

        dispatch(contextStore.actions.update({
          key: parsedMessage.sysid,
          agent: agent
        }));
      }, {id: "user"})

      client.subscribe(`/topic/param`, (msg) => {
        const {sysid, paramId, value} = JSON.parse(msg.body);
        dispatch(contextStore.actions.updateParam({
          key: sysid,
          param: paramId,
          val: value
        }))
      })
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
                <AgentInfo
                  key={i}
                  agentObject={agent}
                  focus={focus}
                />
              ))}
            </GcsSidebarAgentListContainer>
            {focused && (
              <AgentDetailInfo agentObject={context.agents[focused]}/>
            )}
          </>
        )}
      </MapSidebar>
      <GcsHeader/>
      <MainContainer>
        {(contextLoading || socketLoading) ? null : (
          <MainGcsContainer>
            <CanvasContainer cameraRef={cameraRef} controlRef={controlRef}>
              {context.agents && Object.values(context.agents)?.map((agent, i) => (
                <SimpleDrone
                  key={i}
                  agentObj={agent}
                  onClick={handleMeshClick}
                  focused={focused === agent.sysid}
                />
              ))}
            </CanvasContainer>
            <Logger
              category={"ERROR"}
              content={"[SYSID - 1] Battery Problem"}
            />
          </MainGcsContainer>
        )}
      </MainContainer>
    </ApplicationContainer>
  )
}

const GcsSidebarAgentListContainer = styled.div`
  overflow: scroll;
  height: 50%;

  ::-webkit-scrollbar {
    display: none;
  }
`

const MainGcsContainer = styled.div`
  height: 100%;
  width: 100%;
`

const MainContainer = styled.div`
  align-items: center;
  height: 95%;
  display: -webkit-box;
  display: -webkit-flex;
`;

const MapSidebar = styled(Sidebar)`
  background: ${Colors.background}
`


export default ThreeDimensionGCSPage;