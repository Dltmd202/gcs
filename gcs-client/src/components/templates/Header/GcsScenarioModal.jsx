import {useSelector} from "react-redux";
import agentApi from "../../../api/agent";
import React, {useEffect, useRef, useState} from "react";
import styled from "styled-components";
import Modal, {
  ModalBody,
  ModalContainer,
  ModalHeader,
  ModalHeaderName, ModalInput,
  ModalInputContainer, ModalInputLabel, ModalInputPair
} from "../../atoms/modal/Modal";
import {GcsOrderButton} from "./GcsHeader";

const GcsScenarioModal = ({showScenario}) => {
  const [scenario, setScenario] = useState("");
  const [time, setTime] = useState(0);
  const [showConfigDetail, setShowConfigDetail] = useState(false);
  const [showStartDetail, setShowStartDetail] = useState(false);
  const [totalCount, setTotalCount] = useState(0);
  const [fixedCount, setFixedCount] = useState(0);
  const [configCount, setConfigCount] = useState(0);

  const [offsetX, setOffsetX] = useState(null);
  const [offsetY, setOffsetY] = useState(null);

  const scenarioNameReference = useRef();
  const startTimeReference = useRef();
  const {
    loading: contextLoading,
    data: context,
    error: contextError
  } = useSelector((state) => state.context);

  useEffect(() => {
    if(!contextLoading && !contextError){
      setTotalCount(Object.keys(context?.agents).length)
      let fixedCnt = 0;
      let configCnt = 0;

      context.agents && Object.values(context.agents).map((agent, i) => {
        if(agent.id === 1){
          setOffsetX(agent.rtk.x);
          setOffsetY(agent.rtk.y);
        }
        if(agent?.status & (1 << 9) !== 0) {
          fixedCnt++;
        }
        if((agent?.status & (1 << 17) !== 0) && (agent?.status & (1 << 18) !== 0)){
          configCnt++;
        }
      });
      setFixedCount(fixedCnt);
      setConfigCount(configCnt);
    }
  }, [context])

  const handleScenarioConfig = () => {
    if(!showConfigDetail){
      setShowStartDetail(false);
      setShowConfigDetail(true);
      return
    } else if(scenario.length < 1){
      scenarioNameReference.current.focus();
      alert("Set Configuration Name")
      return;
    }

    if(totalCount === fixedCount){
      context.agents && Object.values(context.agents).map((agent, i) => {
        agentApi.scenarioConfiguration(
          agent.sysid,
          offsetX,
          offsetY,
          0,
          `${scenario}/node_${agent.id}.txt`
        )
      });
      alert(`config 보냄 ${fixedCount / totalCount * 100}%`)
    } else {
      alert(`fixed 안됨 ${fixedCount / totalCount * 100}%`)
    }
  }

  const handleScenarioSync = () => {
    if(contextLoading || contextError) return;
    if(!showStartDetail){
      setShowStartDetail(true);
      setShowConfigDetail(false);
      return
    } else if(time === null){
      startTimeReference.current.focus();
      alert("Set StartTime")
      return;
    }

    if(totalCount === configCount){
      agentApi.scenarioSync(time);
      alert(`start 보냄 ${configCount / totalCount * 100}%`)
    } else{
      alert(`config 안됨 ${configCount / totalCount * 100}%`)
    }
  }

  const handleScenarioStop = () => {

    agentApi.scenarioStop()
  }

  const handleScenarioReset = () => {
    agentApi.scenarioReset()
  }

  return (
    showScenario &&
      <GcsScenarioModalContainer>
        <ModalContainer>
          <ModalHeader>
            <ModalHeaderName>
              Scenario
            </ModalHeaderName>
            <div>
              <div>
                Fixed - {fixedCount / totalCount * 100}%
              </div>
              <div>
                Config - {configCount / totalCount * 100}%
              </div>
              <div>
                offsetX - {offsetX}
              </div>
              <div>
                offsetY - {offsetY}
              </div>
            </div>
          </ModalHeader>
          <ModalBody>
            <GcsOrderButton onClick={handleScenarioConfig}>
              CONFIG
            </GcsOrderButton>
            <ModalInputContainer display={showConfigDetail}>
              <ModalInputPair>
                <ModalInputLabel>scenario</ModalInputLabel>
                <ModalInput
                  value={scenario || ''}
                  onChange={(e) => setScenario(e.target.value)}
                  ref={scenarioNameReference}
                />
              </ModalInputPair>
            </ModalInputContainer>
            <GcsOrderButton onClick={handleScenarioSync}>
              START
            </GcsOrderButton>
            <ModalInputContainer display={showStartDetail}>
              <ModalInputPair>
                <ModalInputLabel>time</ModalInputLabel>
                <ModalInput
                  value={time}
                  type={"number"}
                  onChange={(e) => setTime(e.target.value)}
                  ref={startTimeReference}
                />
              </ModalInputPair>
            </ModalInputContainer>
            <GcsOrderButton onClick={handleScenarioStop}>
              STOP
            </GcsOrderButton>
            <GcsOrderButton onClick={handleScenarioReset}>
              RESET
            </GcsOrderButton>
          </ModalBody>
        </ModalContainer>

      </GcsScenarioModalContainer>
  )
}

export default React.memo(GcsScenarioModal);

const GcsScenarioModalContainer = React.memo(styled(Modal)`
  width: 50vh;
  min-height: 10vh;
  top: 6vh;
`)