import React from "react";
import styled from "styled-components";
import Modal, {ModalBody, ModalContainer, ModalHeader, ModalHeaderName} from "../../atoms/modal/Modal";
import {faCircleInfo, faCircleXmark, faTriangleExclamation} from "@fortawesome/free-solid-svg-icons";
import Colors from "../../../styles/colors";
import HeaderButton from "../../atoms/Button/HeaderButton";
import {FontSize} from "../../../styles/font";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const GcsAlertModal = ({showAlert}) => {
  return(
    showAlert &&
      <GcsAlertModalContainer>
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
      </GcsAlertModalContainer>
  )
}

export default React.memo(GcsAlertModal);

const GcsAlertModalContainer = React.memo(styled(Modal)`
  width: 50vh;
  min-height: 20vh;
  top: 6vh;
  right: 3vh;
`)

const GcsAlertClearButton = React.memo(styled.div`
  color: ${Colors.functionConfirm};
  
  :hover {
    text-decoration: underline;
  }
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

const AlertIcon = React.memo(styled(FontAwesomeIcon)`
  margin-right: 1em;
  margin-left: 1em;
`)
