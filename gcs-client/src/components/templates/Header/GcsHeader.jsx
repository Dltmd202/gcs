import styled from "styled-components";
import Header from "./Header";
import React, {useEffect, useState} from "react";
import {FontSize, FontWeight} from "../../../styles/font";
import Colors from "../../../styles/colors";
import {useSelector} from "react-redux";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBell} from "@fortawesome/free-regular-svg-icons";
import {faCircleInfo, faTriangleExclamation, faCircleXmark, faChevronDown} from "@fortawesome/free-solid-svg-icons";
import agentApi from "../../../api/agent";
import HeaderButton from "../../atoms/Button/HeaderButton";
import Modal, {
  ModalContainer,
  ModalHeader,
  ModalHeaderName,
  ModalBody,
} from "../../atoms/modal/Modal";
import {useNavigate} from "react-router-dom";
import contextApi from "../../../api/context";

const GcsHeader = () => {
  const {loading, data: user} = useSelector((state) => state.user);
  const [showAlert, setShowAlert] = useState(false);
  const [showController, setShowController] = useState(false);
  const [showUserController, setShowUserController] = useState(false);
  const navigate = useNavigate();

  const handleUserToggle = () => {
    if(showAlert)
      setShowAlert(!showAlert)
    setShowUserController(!showUserController);
  }


  const handleControllerToggle = () => {
    setShowController(!showController)
  }

  const handleModalToggle = () => {
    if(showUserController)
      setShowUserController(!showUserController)
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

  const logoutButton = () => {
    localStorage.removeItem('token');
    navigate('/')
  }

  const reloadButton = () => {
    contextApi.cleanContext();
    navigate('/load')
  }



  useEffect(() => {

  }, [])

  return (
    <StyledGcsHeader>
      <StyledGcsOrderContainer>
        <ModalDropDown onClick={handleControllerToggle} >
          <ModalDropDownName>
            Control
          </ModalDropDownName>
          <GcsDropDownIcon icon={faChevronDown} size={"sm"} color={Colors.point}/>
        </ModalDropDown>
        {showController &&
          <GcsControllerModal>
            <ModalContainer>
              <ModalHeader>
                <ModalHeaderName>
                  Controller
                </ModalHeaderName>
              </ModalHeader>
              <ModalBody>
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
              </ModalBody>
            </ModalContainer>
          </GcsControllerModal>
        }
      </StyledGcsOrderContainer>

      <StyledGcsUser>
        <GcsHeaderUserAlert onClick={handleModalToggle}>
          <GcsHeaderBellIcon icon={faBell} size={"lg"}/>
        </GcsHeaderUserAlert>
        {showAlert &&
          <GcsAlertModal
            onClose={handleModalClose}
            >
            <ModalContainer>
              <ModalHeader>
                <ModalHeaderName>
                  Notifications
                </ModalHeaderName>
                <GcsAlertClearButton
                  onClick={() => {}}
                >
                  Clear all
                </GcsAlertClearButton>
              </ModalHeader>
              <ModalBody>
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
              </ModalBody>
            </ModalContainer>
          </GcsAlertModal>
        }
        <ModalDropDown onClick={handleUserToggle} >
          <ModalDropDownName>
            <GcsHeaderUserInfo>
              {user.name}
            </GcsHeaderUserInfo>
          </ModalDropDownName>
        </ModalDropDown>

        {showUserController &&
          <GcsUserModal
            onClose={handleUserToggle}
          >
            <ModalContainer>
              <UserModalHeader>
                <UserModalHeaderName>
                  {user.name}
                </UserModalHeaderName>
              </UserModalHeader>
              <UserModalBody>
                <UserButton onClick={logoutButton}>
                  <UserButtonContent>
                    logout
                  </UserButtonContent>
                </UserButton>
                <UserButton onClick={reloadButton}>
                  <UserButtonContent>
                    reload
                  </UserButtonContent>
                </UserButton>
              </UserModalBody>
            </ModalContainer>
          </GcsUserModal>
        }

      </StyledGcsUser>
    </StyledGcsHeader>
  )
}

export default React.memo(GcsHeader);

const GcsDropDownIcon = React.memo(styled(FontAwesomeIcon)`
  
`)

const ModalDropDownName = React.memo(styled.div`
  margin-right: 0.5em;
`)

const UserModalBody = React.memo(styled(ModalBody)`
`)

const UserModalHeaderName = React.memo(styled.div`
  text-align: right;
`)

const UserModalHeader = React.memo(styled(ModalHeader)`
  justify-content: space-between;
  flex-direction: column-reverse;
`)


const ModalDropDown = React.memo(styled.div`
  display: flex;
  align-items: center;
  font-size: ${FontSize.micro};
  height: 100%;
  padding: 0 0.5em;
  
  &:hover {
    background-color: ${Colors.backgroundHover};
  }
`)

const GcsOrderButton = React.memo(styled(HeaderButton)`
  display: flex;
  align-items: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};

  :hover {
    background: rgba(255, 255, 255, 0.2);
  }
`)

const GcsControllerModal = React.memo(styled(Modal)`
  width: 50vh;
  min-height: 10vh;
  top: 6vh;
`)

const GcsUserModal = React.memo(styled(Modal)`
  width: 15vh;
  min-height: 5vh;
  top: 6vh;
  right: 3vh;
`)

const GcsAlertModal = React.memo(styled(Modal)`
  width: 50vh;
  min-height: 20vh;
  top: 6vh;
  right: 3vh;
`)

const AlertButton = React.memo(styled(HeaderButton)`
  display: flex;
  align-items: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};
  
  :hover {
    background: rgba(255, 255, 255, 0.2);
  }
`)


const UserButton = React.memo(styled(HeaderButton)`
  display: flex;
  align-items: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};
  maring-top: 0;
  text-align: right;
  flex-direction: row-reverse;
  
  :hover {
    background: rgba(255, 255, 255, 0.2);
  }
`)

const UserButtonContent = React.memo(styled.div`
  
`)

const AlertIcon = React.memo(styled(FontAwesomeIcon)`
  margin-right: 1em;
  margin-left: 1em;
`)



const GcsAlertClearButton = React.memo(styled.div`
  color: ${Colors.functionConfirm};
  
  :hover {
    text-decoration: underline;
  }
`)



const GcsHeaderBellIcon = React.memo(styled(FontAwesomeIcon)`
  
`)

const GcsHeaderUserAlert = React.memo(styled.div`
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
    background: ${Colors.backgroundModal};
  }
`)

const GcsHeaderUserInfo = React.memo(styled.div`
  font-weight: ${FontWeight.bold};
  font-size: ${FontSize.small};
  line-height: 100%;
`)

const StyledGcsHeader = React.memo(styled(Header)`
  background-color: ${Colors.background};
  color: ${Colors.textPrimary};
  
  justify-content: space-between;
`)

const StyledGcsOrderContainer = React.memo(styled.div`
  display: flex;
  height: 100%;
  align-items: center;
`)

const StyledGcsUser = React.memo(styled.div`
  display: flex;
  align-items: center;
  height: 100%;
`)
