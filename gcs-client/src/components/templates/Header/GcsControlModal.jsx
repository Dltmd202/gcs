import agentApi from "../../../api/agent";
import React, {useEffect, useRef, useState} from "react";
import Modal, {
  ModalBody,
  ModalContainer,
  ModalHeader,
  ModalHeaderName, ModalInput,
  ModalInputContainer, ModalInputLabel, ModalInputPair
} from "../../atoms/modal/Modal";
import { GcsOrderButton } from "./GcsHeader";
import styled from "styled-components";
import contextApi from "../../../api/context";

const GcsControlModal = ({showController}) => {
  const [x, setX] = useState(null);
  const [y, setY] = useState(null);
  const [z, setZ] = useState(null);
  const [isRepeatedSetPoint, setRepeatedSetPoint] = useState(false);
  const [showPointDetail, setShowPointDetail] = useState(false);
  const [showTakeoffDetail, setTakeoffDetail] = useState(false);
  const setPointXReference = useRef();
  const setPointYReference = useRef();
  const setPointZReference = useRef();
  const takOffAltitudeReference = useRef();

  const fetchRepeatPoint = async () => {
    try{
      const res = await contextApi.isRepeatSetPoint();
      return res.data.response;
    } catch (e){

    }
  }

  useEffect(() => {
    setRepeatedSetPoint(fetchRepeatPoint())
  }, []);

  const handleTakeOffButton = () => {
    if(!showTakeoffDetail){
      setTakeoffDetail(true);
      setShowPointDetail(false);
      return;
    } else if(z === null) {
      takOffAltitudeReference.current.focus();
      alert("Set Altitude")
      return;
    }
    agentApi.globalTakeOff(z);
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
    if(!showPointDetail){
      setShowPointDetail(true);
      setTakeoffDetail(false);
      return;
    } else if(x === null){
      setPointXReference.current.focus();
      alert("Set X")
      return;
    } else if(y === null){
      setPointYReference.current.focus();
      alert("Set Y")
      return;
    } else if(z === null){
      setPointZReference.current.focus();
      alert("Set Z")
      return;
    }
    agentApi.globalDestination(x, y, z);
  }

  const handleGlobalLED = () => {
    agentApi.globalLED(0, 0, 0, 0, 0, 1);
  }

  const toggleRepeatedSetPoint = async () => {
    try{
      const res = await contextApi.toggleSetPoint();
      setRepeatedSetPoint(res.data.response);
    } catch (e){

    }
  }

  return (
    showController &&
    <GcsControllerModal>
      <ModalContainer>
        <ModalHeader>
          <ModalHeaderName>
            Controller
          </ModalHeaderName>
          <div>
            <div>
              setPoint - {isRepeatedSetPoint ? "ON" : "OFF"}
            </div>
          </div>
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
          <ModalInputContainer display={showTakeoffDetail}>
            <ModalInputPair>
              <ModalInputLabel>z</ModalInputLabel>
              <ModalInput
                value={z || ''}
                onChange={(e) => setZ(e.target.value)}
                type={"number"}
                ref={takOffAltitudeReference}
              />
            </ModalInputPair>
          </ModalInputContainer>
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
          <ModalInputContainer display={showPointDetail}>
            <ModalInputPair>
              <ModalInputLabel>x</ModalInputLabel>
              <ModalInput
                value={x || ''}
                onChange={(e) => setX(e.target.value)}
                type={"number"}
                ref={setPointXReference}
              />
            </ModalInputPair>
            <ModalInputPair>
              <ModalInputLabel>y</ModalInputLabel>
              <ModalInput
                value={y || ''}
                onChange={(e) => setY(e.target.value)}
                type={"number"}
                ref={setPointYReference}
              />
            </ModalInputPair>
            <ModalInputPair>
              <ModalInputLabel>z</ModalInputLabel>
              <ModalInput
                value={z || ''}
                onChange={(e) => setZ(e.target.value)}
                type={"number"}
                ref={setPointZReference}
              />
            </ModalInputPair>
          </ModalInputContainer>
          <GcsOrderButton onClick={toggleRepeatedSetPoint}>
            TOGGLE SETPOINT
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