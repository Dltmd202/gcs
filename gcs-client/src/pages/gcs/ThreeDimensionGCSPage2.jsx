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
import contextApi from "../../api/context";

const ThreeDimensionGCSPage2 = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [socketLoading, isSocketLoading] = useState(true);
  const [center, setCenter] = useState({
    lat: process.env.REACT_APP_BASE_LAT,
    lng: process.env.REACT_APP_BASE_LNG,
    alt: process.env.REACT_APP_BASE_ALT
  });
  const [contextLoading, setContextLoading] = useState(false);
  const [contextError, setContextError] = useState(false);
  const [context, setContext] = useState({});

  const cameraRef = useRef();
  const controlRef = useRef();
  const client = new StompJs.Client({
    brokerURL: `${process.env.REACT_APP_REALTIME_END_POINT}/api/realtime/info`,
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
  })
  const [focused, focus] = useState(null);
  const [log, setLog] = useState([]);
  const [cnt, setCnt] = useState(0);

  const wsSubscribe = () => {
    client.onConnect = () => {
      client.subscribe('/topic/monitoring', (msg) => {
        const pm = JSON.parse(msg.body);
        const agent= {
          sysid: pm.sysid,
          battery: pm.battery,
          status: pm.status1,
          angle: {
            roll: pm.roll,
            pitch: pm.pitch,
            yaw: pm.head,
          },
          ned: {
            x: pm.pos_x,
            y: pm.pos_y,
            z: pm.pos_z,
          },
          velocity: {
            vx: 0,
            vy: 0,
            vz: 0
          },
          rtk: {
            x: pm.rtk_n,
            y: pm.rtk_e,
            z: pm.rtk_d,
          },
          led: {
            r: pm.r,
            g: pm.g,
            b: pm.b,
          },
          tow: pm.tow,
          param: {}
        }
        console.log(context.agents[key]);
        const key = agent.sysid;
        context.agents[key].led = agent.led;
        context.agents[key].tow = agent.tow;
        context.agents[key].battery = agent.battery;
        context.agents[key].angle = agent.angle;
        context.agents[key].velocity = agent.velocity;
        context.agents[key].status = agent.status;
        context.agents[key].rtk = agent.rtk;
        context.agents[key].ned = agent.ned;
        setCnt(cnt + 1);
        console.log(cnt);
      }, {id: "user"})

      client.subscribe(`/topic/param`, (msg) => {
        const {sysid, paramId, value} = JSON.parse(msg.body);
        dispatch(contextStore.actions.updateParam({
          key: sysid,
          param: paramId,
          val: value
        }))
      })

      client.subscribe(`/topic/log`, (msg) => {
        const m = JSON.parse(msg.body);
        console.log(m);
        setLog(prevLog => [...prevLog, m]);
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
    setContextLoading(true);
    const getCurrentContext = async () => {
      try{
        const res = await contextApi.getRunningContext();
        setContext(res.data.response);
      } catch (e){
        // TODO error handling
      }
    }

    getCurrentContext();
    isSocketLoading(true);
    window.addEventListener('scroll', () => {}, { passive: true });

    if (typeof WebSocket !== 'function') {
      client.webSocketFactory = function () {
        return new SockJs(`${process.env.REACT_APP_API_END_POINT}/api/realtime/info`);
      };
    }

    client.activate();
    wsSubscribe();

    setContextLoading(false);
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
            <Logger>
              {log}
            </Logger>
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


export default ThreeDimensionGCSPage2;