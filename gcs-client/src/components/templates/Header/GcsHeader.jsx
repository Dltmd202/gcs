import styled from "styled-components";
import Header from "./Header";
import React, {useEffect, useState} from "react";
import {FontSize, FontWeight} from "../../../styles/font";
import Colors from "../../../styles/colors";
import {useSelector} from "react-redux";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBell} from "@fortawesome/free-regular-svg-icons";
import { faChevronDown } from "@fortawesome/free-solid-svg-icons";
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
import GcsControlModal from "./GcsControlModal";
import GcsScenarioModal from "./GcsScenarioModal";
import GcsAlertModal from "./GcsAlertModal";
import GcsUserModal from "./GcsUserModal";
import GcsParameterModal from "./GcsParameterModal";
import paramApi from "../../../api/param";

const GcsHeader = () => {
  // TODO context selector 사용해도 될지 검토해야함
  const {loading, data: user} = useSelector((state) => state.user);
  const [paramKeyList, setParamKeyList] = useState([]);
  const [showAlert, setShowAlert] = useState(false);
  const [showScenario, setShowScenario] = useState(false);
  const [showController, setShowController] = useState(false);
  const [showUserController, setShowUserController] = useState(false);
  const [showParamSet, setShowParamSet] = useState(false);
  const [loadingParamSet, setLoadingParamSet] = useState(false);


  const handleUserToggle = () => {
    if(showAlert)
      setShowAlert(!showAlert)
    setShowUserController(!showUserController);
  }

  const handleControllerToggle = () => {
    setShowController(!showController);
    setShowParamSet(false);
    setShowScenario(false);
  }

  const handleScenarioToggle = () => {
    setShowController(false);
    setShowParamSet(false);
    setShowScenario(!showScenario);
  }

  const handleParameterToggle = () => {
    setShowController(false);
    setShowParamSet(!showParamSet);
    setShowScenario(false);
  }

  const handleModalToggle = () => {
    if(showUserController)
      setShowUserController(!showUserController)
    setShowAlert(!showAlert);
  };

  const getParamKeyList = async () => {
    setLoadingParamSet(true);
    try{
      const paramKey = await paramApi.paramKeyList();
      setParamKeyList(paramKey.data.response);
    }catch (e){

    }
    setLoadingParamSet(false);
  }


  useEffect(() => {
    getParamKeyList();
  }, [])

  return (
    <StyledGcsHeader>
      <GcsControlContainer>
        <HeaderOrderContainer>
          <ModalDropDown onClick={handleControllerToggle} >
            <ModalDropDownName>
              Control
            </ModalDropDownName>
            <GcsDropDownIcon icon={faChevronDown} size={"sm"} color={Colors.point}/>
          </ModalDropDown>
          <GcsControlModal showController={showController}/>

          <ModalDropDown onClick={handleScenarioToggle} >
            <ModalDropDownName>
              Scenario
            </ModalDropDownName>
            <GcsDropDownIcon icon={faChevronDown} size={"sm"} color={Colors.point}/>
          </ModalDropDown>
          <GcsScenarioModal showScenario={showScenario}/>

          <ModalDropDown onClick={handleParameterToggle} >
            <ModalDropDownName>
              Parameter
            </ModalDropDownName>
            <GcsDropDownIcon icon={faChevronDown} size={"sm"} color={Colors.point}/>
          </ModalDropDown>
          {!loadingParamSet &&
            <GcsParameterModal showScenario={showParamSet} paramKeyList={paramKeyList}/>
          }
        </HeaderOrderContainer>
      </GcsControlContainer>

      <HeaderUserContainer>
        <GcsHeaderUserAlert onClick={handleModalToggle}>
          <GcsHeaderBellIcon icon={faBell} size={"lg"}/>
        </GcsHeaderUserAlert>
        <GcsAlertModal showAlert={showAlert}/>

        <ModalDropDown onClick={handleUserToggle} >
          <ModalDropDownName>
            <GcsHeaderUserInfo>
              {user.name}
            </GcsHeaderUserInfo>
          </ModalDropDownName>
        </ModalDropDown>
        <GcsUserModal showUser={showUserController}/>

      </HeaderUserContainer>
    </StyledGcsHeader>
  )
}

export default React.memo(GcsHeader);

const GcsControlContainer = React.memo(styled.div`
  display: flex;
  height: 100%;
`)

const GcsDropDownIcon = React.memo(styled(FontAwesomeIcon)`
  
`)

export const ModalDropDownName = React.memo(styled.div`
  margin-right: 0.5em;
`)

export const ModalDropDown = React.memo(styled.div`
  display: flex;
  align-items: center;
  font-size: ${FontSize.micro};
  height: 100%;
  padding: 0 0.5em;
  margin-right: 0.5em;
  
  &:hover {
    background-color: ${Colors.backgroundHover};
  }
`)

export const GcsOrderButton = React.memo(styled(HeaderButton)`
  display: flex;
  align-items: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};

  :hover {
    background: rgba(255, 255, 255, 0.2);
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

const HeaderOrderContainer = React.memo(styled.div`
  display: flex;
  height: 100%;
  align-items: center;
`)

const HeaderUserContainer = React.memo(styled.div`
  display: flex;
  align-items: center;
  height: 100%;
`)
