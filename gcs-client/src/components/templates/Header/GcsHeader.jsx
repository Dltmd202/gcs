import styled from "styled-components";
import Header from "./Header";
import React, {useEffect, useState} from "react";
import {NavLink} from "react-router-dom";
import {FontSize, FontWeight} from "../../../styles/font";
import Colors from "../../../styles/colors";
import {useSelector} from "react-redux";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBell} from "@fortawesome/free-regular-svg-icons";
import {Media} from "../../../styles/media";
import {faCircleInfo, faTriangleExclamation, faCircleXmark} from "@fortawesome/free-solid-svg-icons";
import agentApi from "../../../api/agent";
import HeaderButton from "../../atoms/Button/HeaderButton";

const GcsHeader = () => {

  const {loading, data: user} = useSelector((state) => state.user);
  const [showAlert, setShowAlert] = useState(false);

  const handleModalToggle = () => {
    setShowAlert(!showAlert);
  };

  const handleModalClose = () => {
    setShowAlert(false);
  };

  const handleTakeOffButton = () => {
    agentApi.globalTakeOff(5);
  }

  const handleLandButton = () => {
    agentApi.globalLand();
  }

  const handleOffboardButton = () => {
    agentApi.globalOffboard();
  }

  const handleArmButton = () => {
    agentApi.globalArm();
  }

  const handleDisarmButton = () => {
    agentApi.gloablDisarm();
  }



  useEffect(() => {

  }, [])

  return (
    <StyledGcsHeader>
      <StyledGcsOrderContainer>
        <GcsOrderButton onClick={handleArmButton}>
          ARM
        </GcsOrderButton>
        <GcsOrderButton onClick={handleDisarmButton}>
          DISARM
        </GcsOrderButton>
        <GcsOrderButton onClick={handleTakeOffButton}>
          TAKFOFF
        </GcsOrderButton>
        <GcsOrderButton onClick={handleLandButton}>
          LAND
        </GcsOrderButton>
        <GcsOrderButton onClick={handleOffboardButton}>
          OFFBOARD
        </GcsOrderButton>
      </StyledGcsOrderContainer>

      <StyledGcsUser>
        <GcsHeaderUserAlert onClick={handleModalToggle}>
          <GcsHeaderBellIcon icon={faBell} size={"lg"}/>
        </GcsHeaderUserAlert>
        {showAlert &&
          <GcsAlertModal
            onClose={handleModalClose}
            >
            <GcsAlertModalContainer>
              <GcsAlertModalHeader>
                <GcsAlertModalHeaderName>
                  Notifications
                </GcsAlertModalHeaderName>
                <GcsAlertClearButton
                  onClick={() => {}}
                >
                  Clear all
                </GcsAlertClearButton>
              </GcsAlertModalHeader>
              <GcsAlertModalBody>
                <AlertButton>
                  <AlertIcon icon={faCircleInfo} color={Colors.info}/>
                  <div>송시운이 말을 안듣습니다.</div>
                </AlertButton>
                <AlertButton>
                  <AlertIcon icon={faTriangleExclamation} color={Colors.warning}/>
                  <div>송시운이 말을 안듣습니다.</div>
                </AlertButton>
                <AlertButton>
                  <AlertIcon icon={faCircleXmark} color={Colors.error}/>
                  <div>송시운이 말을 안듣습니다.</div>
                </AlertButton>
              </GcsAlertModalBody>
            </GcsAlertModalContainer>
          </GcsAlertModal>
        }
        <GcsHeaderUserInfo>
          {user.name}
        </GcsHeaderUserInfo>
      </StyledGcsUser>
    </StyledGcsHeader>
  )
}

export default React.memo(GcsHeader);

const GcsOrderButton = styled(HeaderButton)`
  padding: 0 1em;
  :hover {
    box-shadow: 0 0 0 3px rgba(0, 0, 0, 0.2);
    border-radius: 3px;
    background: rgba(0, 0, 0, 0.2);
  }
  
  :active{
    color: ${Colors.textTertiary};
  }
`

const GcsAlertModal = styled.div`
  width: 50vh;
  min-height: 20vh;
  position: absolute;
  top: 6vh;
  right: 3vh;
  border-radius: 0.5em;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
  background: rgba(0, 0, 0, 0.7);
  color: white;
  z-index: 5;

  @media ${Media.sm} {
    max-width: 25vh;
  }
`

const AlertButton = styled(HeaderButton)`
  display: flex;
  align-items: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};
  
  :hover {
    background: rgba(255, 255, 255, 0.2);
  }
`

const AlertIcon = styled(FontAwesomeIcon)`
  margin-right: 1em;
  margin-left: 1em;
`


const GcsAlertModalBody = styled.div`
  margin-top: 0.5em;
`

const GcsAlertClearButton = styled.div`
  color: #11ae79;
  
  :hover {
    text-decoration: underline;
  }
`

const GcsAlertModalHeaderName = styled.div`
  font-weight: ${FontWeight.bold};
`

const GcsAlertModalHeader = styled.div`
  display: flex;
  justify-content: space-between;
`

const GcsAlertModalContainer = styled.div`
  padding: 1em;
`

const GcsHeaderBellIcon = styled(FontAwesomeIcon)`
  
`

const GcsHeaderUserAlert = styled.div`
  width: 4vh;
  height: 4vh;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 1vh;
  
  position: relative;

  :hover {
    box-shadow: 0 0 0 3px rgba(0, 0, 0, 0.2);
    border-radius: 50%;
    background: rgba(0, 0, 0, 0.2);
  }
`

const GcsHeaderUserInfo = styled.div`
  
`

const StyledGcsHeader = styled(Header)`
  justify-content: space-between;
  padding: 1vh 3vh 1vh 1vh;
`

const StyledGcsOrderContainer = styled.div`
  display: flex;
`

const StyledGcsUser = styled.div`
  display: flex;
  margin: 0.5em;
  align-items: center;
`

const StyledNavLink = styled(NavLink)`
  &:visited {
    color: black;
  }
  
  &:link {
    color: black;
  }

  &.active {
    display: inline-block;
    box-sizing: border-box;
    font-weight: ${FontWeight.bold};
  }
`;

const Place = styled.span`
  display: inline-block;
  margin: 0.5vh 1vh;
  :hover {
    cursor: pointer;
    color: ${Colors.textSecondary}
  }
`;
