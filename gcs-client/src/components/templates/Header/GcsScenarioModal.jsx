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
import {agentStatusMask} from "../../../module/coordinate/agentStatus";
import ProgressBar from "../../atoms/progress/ProgressBar";
import Colors from "../../../styles/colors";

const GcsScenarioModal = ({showScenario}) => {
  const [scenario, setScenario] = useState("");
  const [time, setTime] = useState(10);
  const [leftTime, setLeftTime] = useState(-1);
  const [isWaiting, setWaiting] = useState(false);
  const [showConfigDetail, setShowConfigDetail] = useState(false);
  const [showStartDetail, setShowStartDetail] = useState(false);
  const [showAutoSort, setShowAutoSort] = useState(false);
  const [totalCount, setTotalCount] = useState(0);
  const [fixedCount, setFixedCount] = useState(0);
  const [tow, setTow] = useState(0);
  const [configCount, setConfigCount] = useState(0);

  const [offsetX, setOffsetX] = useState(0);
  const [offsetY, setOffsetY] = useState(0);

  const [hasSentConfig, sendConfig] = useState(false);
  const [hasSentStart, sendStart] = useState(false);

  const [gridX, setGridX] = useState(5);
  const [gridY, setGridY] = useState(5);
  const [gridInterval, setGridInterval] = useState(3);

  const scenarioNameReference = useRef();
  const startTimeReference = useRef();
  const gridXReference = useRef();
  const gridYReference = useRef();
  const gridIntervalReference = useRef();

  const {
    loading: contextLoading,
    data: context,
    error: contextError
  } = useSelector((state) => state.context);


  useEffect(() => {
    setTotalCount(Object.keys(context?.agents).length)
    if(!contextLoading && !contextError && showScenario){
      let fixedCnt = 0;
      let configCnt = 0;

      context.agents && Object.values(context.agents).map((agent, i) => {
        if(agent.id === 1){
          if(!hasSentConfig){
            setOffsetX(agent.rtk.x);
            setOffsetY(agent.rtk.y);
          }
          if(!isWaiting){
            setTow(agent.tow);
          }
        }

        if((agent.status & agentStatusMask[9].mask) !== 0) {
          fixedCnt++;
        }
        if(((agent.status & agentStatusMask[17].mask) !== 0) &&
          ((agent.status & agentStatusMask[18].mask) !== 0)){
          configCnt++;
        }
      });
      setFixedCount(fixedCnt);
      setConfigCount(configCnt);
    }
  }, [context])

  useEffect(() => {
    let interval = null;
    if(isWaiting){
      interval = setInterval(() => {
        setLeftTime(prevTime => {
          if(prevTime < 0){
            setWaiting(false);
            setLeftTime(-1);
            return 0;
          } else{
            return prevTime - 1000;
          }
        });
      }, 1000);
    } else {
      clearInterval(interval)
    }

    return () => clearInterval(interval)
  }, [isWaiting])

  const handleScenarioConfig = () => {
    if(!showConfigDetail){
      setShowStartDetail(false);
      setShowConfigDetail(true);
      setShowAutoSort(false);
      return
    } else if(scenario.length < 1){
      scenarioNameReference.current.focus();
      alert("Set Configuration Name")
      return;
    }

    if(totalCount === fixedCount){
      alert(`config 세팅을 전송했습니다.`)
      context.agents && Object.values(context.agents).map((agent, i) => {
        agentApi.scenarioConfiguration(
          agent.sysid,
          offsetX,
          offsetY,
          0,
          `${scenario}/node_${agent.id}.txt`
        )
      });
      sendConfig(true);
    } else {
      alert(`모든 드론의 GPS가 Fixed 되지 않았습니다.`);
    }
  }

  const handleScenarioSync = () => {
    if(contextLoading || contextError) return;
    if(!showStartDetail){
      setShowStartDetail(true);
      setShowConfigDetail(false);
      setShowAutoSort(false);
      return
    } else if(time === null){
      startTimeReference.current.focus();
      alert("Set StartTime")
      return;
    }

    if(totalCount === configCount){
      agentApi.scenarioSync(time + (tow / 1000));
      setLeftTime(time * 1000);
      setWaiting(true);
      alert(`start 보냄 ${configCount / totalCount * 100}%`)
    } else{
      alert(`config 안됨 ${configCount / totalCount * 100}%`)
    }
  }

  const handleScenarioStop = () => {
    agentApi.scenarioStop()
  }

  const handleScenarioReset = () => {
    sendConfig(false);
    sendStart(false);
    agentApi.scenarioReset()
  }

  const handleAutoSorting = () => {
    if(!showAutoSort){
      setShowStartDetail(false);
      setShowConfigDetail(false);
      setShowAutoSort(true);
      return
    }

    const len = Object.keys(context.agents).length;
    const orderById = new Array(len + 1);

    Object.keys(context.agents).forEach((key) => {
      orderById[context.agents[key].id] = context.agents[key].sysid
    })

    let idx = 2;
    const iterateSort = () => {
      setTimeout(() => {
        const prevAgent = context.agent[orderById[idx - 1]];
        const agent = context.agent[orderById[idx]];

        agentApi.land(prevAgent.sysid);

        let x = idx % gridX;
        let y = idx / gridX;

        agentApi.offboard(agent.sysid);
        agentApi.destination(
          agent.sysid,
          offsetX + gridInterval * x,
          offsetY + gridInterval * y,
          -1
        )
        agentApi.arm(agent.sysid);

        if(idx <= len){
          iterateSort();
        }
      }, 20000);
    }

    iterateSort();
    agentApi.globalLand();
    agentApi.globalDisarm();

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
              {leftTime >= 0 ? leftTime / 1000 : ""}
            </div>
          </ModalHeader>
          <ModalBody>
            <ModalBodyStatus>
              <StatusRow>
                <StatusLabel>offsetX</StatusLabel>
                <StatusValue>{offsetX}</StatusValue>
              </StatusRow>
              <StatusRow>
                <StatusLabel>offsetY</StatusLabel>
                <StatusValue>{offsetY}</StatusValue>
              </StatusRow>
              <StatusRow>
                <StatusLabel>Fixed</StatusLabel>
                <StatusValue>
                  <ProgressBar color={Colors.point} progress={`${fixedCount / totalCount * 100}%`}/>
                </StatusValue>
              </StatusRow>
              <StatusRow>
                <StatusLabel>Config</StatusLabel>
                <StatusValue>
                  <ProgressBar color={Colors.point} progress={`${configCount / totalCount * 100}%`}/>
                </StatusValue>
              </StatusRow>
            </ModalBodyStatus>
            <GcsOrderButton onClick={handleScenarioConfig}>
              CONFIG
            </GcsOrderButton>
            <ModalInputContainer display={showConfigDetail}>
              <ModalInputPair>
                <ModalInputLabel>scenario</ModalInputLabel>
                <ModalInput
                  value={scenario}
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
            <ModalInputContainer display={showAutoSort}>
              <ModalInputPair>
                <ModalInputLabel>X Grid</ModalInputLabel>
                <ModalInput
                  value={gridX}
                  type={"number"}
                  onChange={(e) => setGridX(e.target.value)}
                  ref={gridXReference}
                />
              </ModalInputPair>
              <ModalInputPair>
                <ModalInputLabel>Y Grid</ModalInputLabel>
                <ModalInput
                  value={gridY}
                  type={"number"}
                  onChange={(e) => setGridY(e.target.value)}
                  ref={gridYReference}
                />
              </ModalInputPair>
              <ModalInputPair>
                <ModalInputLabel>Interval</ModalInputLabel>
                <ModalInput
                  value={gridInterval}
                  type={"number"}
                  onChange={(e) => setGridInterval(e.target.value)}
                  ref={gridIntervalReference}
                />
              </ModalInputPair>
            </ModalInputContainer>
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

const ModalBodyStatus = React.memo(styled.div`
  border-bottom: rgba(90, 90, 90, 0.3) 1px solid;
  margin-bottom: 5px;
`)

const StatusRow = React.memo(styled.div`
  display: flex;
  margin-bottom: 10px;
`)

const StatusLabel = React.memo(styled.div`
  width: 15%;
`)

const StatusValue = React.memo(styled.div`
  width: 85%;
  text-align: right;
`)