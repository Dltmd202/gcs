import agentApi from "../../../api/agent";
import React, {useCallback, useEffect, useRef, useState} from "react";
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

const areEqual = (prev, next) => {
  return prev.showController === next.showController;
}


const GcsControlModal = ({showController}) => {
  const [x, setX] = useState(0);
  const [y, setY] = useState(0);
  const [z, setZ] = useState(0);
  const [r, setR] = useState(0);
  const [g, setG] = useState(0);
  const [b, setB] = useState(0);
  const [bright, setBright] = useState(0);
  const [isRepeatedSetPoint, setRepeatedSetPoint] = useState(false);
  const [showPointDetail, setShowPointDetail] = useState(false);
  const [showTakeoffDetail, setTakeoffDetail] = useState(false);
  const [showLEDDetail, setLEDDetail] = useState(false);
  const setPointXReference = useRef();
  const setPointYReference = useRef();
  const setPointZReference = useRef();
  const takOffAltitudeReference = useRef();
  const setLEDRReference = useRef();
  const setLEDGReference = useRef();
  const setLEDBReference = useRef();
  const setLEDBrightReference = useRef();


  useEffect(() => {
    const fetchRepeatPoint = async () => {
      try{
        const res = await contextApi.isRepeatSetPoint();
        return res.data.response;
      } catch (e){

      }
    }

    setRepeatedSetPoint(fetchRepeatPoint())
  }, []);

  const handleTakeOffButton = useCallback(() => {
    if(!showTakeoffDetail){
      setTakeoffDetail(true);
      setShowPointDetail(false);
      setLEDDetail(false);
      return;
    } else if(z === null) {
      takOffAltitudeReference.current.focus();
      alert("Set Altitude")
      return;
    }
    agentApi.globalTakeOff(z);
  }, [showTakeoffDetail, z]);


  const handleLandButton = useCallback(() => {
    agentApi.globalLand();
  }, []);

  const handleOffboardButton = useCallback(() => {
    agentApi.globalOffboard();
  }, []);

  const handleArmButton = useCallback(() => {
    agentApi.globalArm();
  }, []);

  const handleDisarmButton = useCallback(() => {
    agentApi.globalDisarm();
  }, []);

  const handleReboot = useCallback(() => {
    agentApi.globalReboot();
  }, []);

  const handleSetPoint = useCallback(() => {
    if(!showPointDetail){
      setShowPointDetail(true);
      setTakeoffDetail(false);
      setLEDDetail(false);
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
  }, [showPointDetail, x, y, z]);

  const handleGlobalLED = useCallback(() => {
    if(!showLEDDetail){
      setShowPointDetail(false);
      setTakeoffDetail(false);
      setLEDDetail(true);
      return;
    }
    agentApi.globalLED(0, r, g, b, bright, 1);
  }, [r, g, b, bright]);

  const toggleRepeatedSetPoint = useCallback(async () => {
    try{
      const res = await contextApi.toggleSetPoint();
      setRepeatedSetPoint(res.data.response);
    } catch (e){

    }
  }, [isRepeatedSetPoint]);

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
          <ModalInputContainer display={showLEDDetail}>
            <ModalInputPair>
              <ModalInputLabel>R</ModalInputLabel>
              <ModalInput
                value={r || ''}
                onChange={(e) => setR(e.target.value)}
                type={"number"}
                ref={setLEDRReference}
              />
            </ModalInputPair>
            <ModalInputPair>
              <ModalInputLabel>G</ModalInputLabel>
              <ModalInput
                value={g || ''}
                onChange={(e) => setG(e.target.value)}
                type={"number"}
                ref={setLEDGReference}
              />
            </ModalInputPair>
            <ModalInputPair>
              <ModalInputLabel>B</ModalInputLabel>
              <ModalInput
                value={b || ''}
                onChange={(e) => setB(e.target.value)}
                type={"number"}
                ref={setLEDBReference}
              />
            </ModalInputPair>
            <ModalInputPair>
              <ModalInputLabel>Brightness</ModalInputLabel>
              <ModalInput
                value={bright || ''}
                onChange={(e) => setBright(e.target.value)}
                type={"number"}
                ref={setLEDBrightReference}
              />
            </ModalInputPair>
          </ModalInputContainer>
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

export default React.memo(GcsControlModal, areEqual);

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