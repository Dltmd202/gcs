import agentApi from "../../../api/agent";
import React, {useEffect, useState} from "react";
import Modal, {ModalBody, ModalContainer, ModalHeader, ModalHeaderName} from "../../atoms/modal/Modal";
import { GcsOrderButton } from "./GcsHeader";
import styled from "styled-components";
import contextApi from "../../../api/context";

const GcsControlModal = ({showController}) => {
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

  const handleReboot = () => {
    agentApi.globalReboot();
  }

  const handleSetPoint = () => {
    agentApi.globalDestination(0, 0, -2);
  }

  const handleGlobalLED = () => {
    agentApi.globalLED(0, 0, 0, 0, 0, 1);
  }

  const toggleRepeatedSetPoint = async () => {
    contextApi.toggleSetPoint();
  }

  return (
    showController &&
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
          <GcsOrderButton onClick={handleReboot}>
            REBOOT
          </GcsOrderButton>
          <GcsOrderButton onClick={handleGlobalLED}>
            LED
          </GcsOrderButton>
          <GcsOrderButton onClick={handleSetPoint}>
            SET POINT
          </GcsOrderButton>
          <GcsOrderButton onClick={toggleRepeatedSetPoint}>
            On SETPOINT
          </GcsOrderButton>

        </ModalBody>
      </ModalContainer>
    </GcsControllerModal>
  )
}

export default React.memo(GcsControlModal);

const ModalHeadSub = React.memo(styled.div`
  
`)

const GcsControllerModal = React.memo(styled(Modal)`
  width: 50vh;
  min-height: 10vh;
  top: 6vh;
`)

const StyledGcsOrderContainer = React.memo(styled.div`
  display: flex;
  height: 100%;
  align-items: center;
`)