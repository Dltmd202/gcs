import {useSelector} from "react-redux";
import agentApi from "../../../api/agent";
import React from "react";
import styled from "styled-components";
import Modal, {ModalBody, ModalContainer, ModalHeader, ModalHeaderName} from "../../atoms/modal/Modal";
import {GcsOrderButton} from "./GcsHeader";

const GcsScenarioModal = ({showScenario}) => {
  const {
    loading: contextLoading,
    data: context,
    error: contextError
  } = useSelector((state) => state.context);
  const handleScenarioConfig = () => {
    if(contextLoading || contextError) return;
    let valid = true;
    let cnt = 0;
    let offsetx;
    let offsety;

    context.agents && Object.values(context.agents).map((agent, i) => {
      agentApi.scenarioConfiguration(
        agent.sysid,
        offsetx,
        offsety,
        0,
        `"test/node_${agent.id}.txt"`
      )
    });

    context.agents && Object.values(context.agents).map((agent, i) => {
      if(agent?.status & (1 << 9) !== 0) {
        cnt++;
        if(agent.id === 1){
          offsetx = agent.rtk.e;
          offsety = agent.rtk.n;
        }

      } else {
        valid = false;
      }
    });

    let total = Object.keys(context.agents).length;

    // if(valid && offsetx !== undefined && offsety !== undefined){
    //
    //   alert(`config 보냄 ${cnt / total * 100}%`)
    // } else {
    //   alert(`fixed 다 안됨 ${cnt / total * 100}%`)
    // }
  }

  const handleScenarioSync = () => {
    let valid = true;
    let total = Object.keys(context.agents).length;
    let cnt = 0;

    agentApi.scenarioSync(3);
    context.agents && Object.values(context.agents).map((agent, i) => {
      if(agent?.status & (1 << 17) !== 0 && agent?.status & (1 << 18) !== 0) {
        valid = false;
        cnt++;
      } else {
        valid = false;
      }
    });

    // if(valid){
    //   alert(`start 보냄 ${cnt / total * 100}%`)
    // } else{
    //   alert(`config 아직 안됨 ${cnt / total * 100}%`)
    // }
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
          </ModalHeader>
          <ModalBody>
            <GcsOrderButton onClick={handleScenarioConfig}>
              CONFIG
            </GcsOrderButton>
            <GcsOrderButton onClick={handleScenarioSync}>
              START
            </GcsOrderButton>
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