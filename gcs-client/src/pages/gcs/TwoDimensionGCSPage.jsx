import { NavLink } from "react-router-dom";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { context as contextStore } from "../../store/context";
import styled from "styled-components";
import ApplicationContainer from "../../components/templates/Container/MapContainer";
import Video from "../../components/atoms/Video/Video";
import AgentInfo from "../../components/molecules/AgentInfo/AgentInfo";
import { Wrapper } from "@googlemaps/react-wrapper";
import Map from "../../components/molecules/Map/Map";
import Drone from "../../components/molecules/Drone/Drone";
import Sidebar from "../../components/templates/Sidebar/Sidebar";
import * as StompJs from "@stomp/stompjs";
import SockJs from "sockjs-client";
import GcsHeader from "../../components/templates/Header/GcsHeader";

const TwoDimensionGCSPage = () => {
  // TODO env 처리
  const key = 'AIzaSyDJVwYS6Jf_KFZA_x1Qhoo2D5FzBsN9Fmk';
  const dispatch = useDispatch();
  const {loading, data: context} = useSelector((state) => state.context);
  const [socketLoading, isSocketLoading] = useState(true);
  const [focused, setFocused] = useState(-1);
  const [clicks, setClicks] = React.useState([]);
  const [zoom, setZoom] = React.useState(13);
  const [center, setCenter] = React.useState({
    lat: process.env.REACT_APP_BASE_LAT,
    lng: process.env.REACT_APP_BASE_LNG,
    alt: process.env.REACT_APP_BASE_ALT
  });

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
          roll: parsedMessage.roll,
          pitch: parsedMessage.pitch,
          yaw: parsedMessage.yaw,
          active: true,
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



  useEffect( () => {
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

  const onClickAgentInfo = (e, id) => {
    setFocused(id);
  }

  const onMapClick = (e) => {
    dispatch(contextStore.actions.updatePosition({
        key:focused,
        position:{
          lat: e.latLng?.lat(),
          lng: e.latLng?.lng(),
          alt: 10.0
        }
      }));
    setClicks([...clicks, e.latLng]);
    console.log(clicks);
  }

  const onMapRender = (status) => {
    return <h1>{status}</h1>;
  };

  const onMapIdle = (m) => {
    setZoom(m.getZoom());
    setCenter({
      lat: m.getCenter().lat(),
      lng: m.getCenter().lng()
    })
    setCenter(m.getCenter().toJSON());
  };

  return(
    <ApplicationContainer>
      {loading || socketLoading ? null : (
        <MapSidebar>
          <MapSidebarAgentListContainer>
            {context.agents && Object.values(context.agents)?.map((agent, i) => (
              <AgentInfo
                key={i}
                agentObject={agent}
              />
            ))}
          </MapSidebarAgentListContainer>
          <SideBarVideo/>
        </MapSidebar>
      )}
      <GcsHeader/>
      <MainContainer>
        <MapContainer>
          <Wrapper apiKey={key} render={onMapRender}>
            <Map
              center={center}
              zoom={zoom}
              onClick={onMapClick}
              onIdle={onMapIdle}
              style={{height: "100%", width: '100%'}}
              disableDefaultUI={true}
              mapTypeId={'satellite'}
            >

              {context.agents &&
                Object.values(context.agents).map((agent, i) => (
                <Drone
                  key={i}
                  color={agent.color}
                  position={agent.position}
                />
              ))}

            </Map>
          </Wrapper>
        </MapContainer>
      </MainContainer>
    </ApplicationContainer>
  )
}

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

const Place = styled.span`
  display: inline-block;
  margin: 2vh 2vh 2vh 2vh;
  font-size: 1.5rem;
  font-weight: 400;
  :hover {
    cursor: pointer;
  }
`;

const MapSidebarAgentListContainer = styled.div`
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

const MapContainer = styled.div`
  display: flex;
  height: 100%;
`;

const MapSidebar = styled(Sidebar)`
  
`


const SideBarVideo = styled(Video)`
`


export default TwoDimensionGCSPage;