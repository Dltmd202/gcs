import agentApi from "../../../api/agent";
import React, {useCallback, useEffect, useRef, useState} from "react";
import Modal, {
  ModalBody,
  ModalContainer,
  ModalHeader,
  ModalHeaderName,
  ModalInput,
  ModalInputContainer,
  ModalInputLabel,
  ModalInputPair
} from "../../atoms/modal/Modal";
import styled from "styled-components";
import { useSelector } from "react-redux";
import { agentStatusMask } from "../../../module/coordinate/agentStatus";
import { FontSize } from "../../../styles/font";
import radianToDegree from "../../../module/coordinate/RadToDgreeConverter";
import deployApi from "../../../api/deploy";

const DeployAgentAreEqual = (prev, next) => {
  let res = prev.agent.ned.x === next.agent.ned.x;
  res = res && prev.agent.ned.y === next.agent.ned.y;
  res = res && prev.agent.rtk.x === next.agent.rtk.x;
  res = res && prev.agent.rtk.y === next.agent.rtk.y;
  return res;
}

const DeployAgentStatus = React.memo(({
                             agent,
                             rotation,
                             offsetHead,
                             gridX,
                             gridInterval,
                             offSetX,
                             offSetY
}) => {
  const [rtkDestX, setRTKDestX] = useState(0);
  const [rtkDestY, setRTKDestY] = useState(0);
  const [localDestX, setLocalDestX] = useState(0);
  const [localDestY, setLocalDestY] = useState(0);


  useEffect(() => {
    const x = ((agent.id - 1) % gridX) * gridInterval;
    const y = -1 * (Math.floor((agent.id - 1) / gridX)) * gridInterval;
    if(rotation === 0) {
      setRTKDestX(offSetX + x);
      setRTKDestY(offSetY - y);
    } else {
      setRTKDestX(offSetX + (x * Math.cos(rotation) - y * Math.sin(rotation)));
      setRTKDestY(offSetY + (x * Math.sin(rotation) + y * Math.cos(rotation)));
    }
  }, [gridX, gridInterval, rotation, offSetX, offSetY]);

  useEffect(() => {
    setLocalDestX(agent.ned.x + rtkDestX - agent.rtk.x);
    setLocalDestY(agent.ned.y + rtkDestY - agent.rtk.y);
  }, [agent.ned]);


  const handleTakeOffButton = useCallback(() => {
    deployApi.takeOff(
      agent.sysid,
      agent.ned.x,
      agent.ned.y,
      -1.5,
      radianToDegree(offsetHead)
    );
  }, [agent.ned]);


  const handleMoveButton = useCallback(() => {
    deployApi.move(
      agent.sysid,
      localDestX,
      localDestY,
      -1.5,
      radianToDegree(offsetHead)
    );
  }, [localDestX, localDestY, offsetHead]);


  const handleRebootButton = useCallback(() => {
    const result = window.confirm(`SYSID - ${agent.sysid} 을(를) Reboot 하겠습니까?`)
    if (result) {
      agentApi.reboot(agent.sysid);
    }
  }, [agent.sysid]);

  const handleLandButton = useCallback(() => {
    deployApi.land(agent.sysid);
  }, [agent.sysid]);

  return (
    <DeployStatusTbodyTr key={agent.id} fixed={(agent.status & agentStatusMask[9].mask) !== 0}>
      <DeployStatusTbodyTd>{agent.id}</DeployStatusTbodyTd>
      <DeployStatusTbodyTd>{agent.sysid}</DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        {radianToDegree(agent.angle.yaw).toFixed(2)}
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <CurrentStatusContainer>
          <div>
            {agent.rtk.x.toFixed(2)}
          </div>
          <DestinationErrorRange
            dest={rtkDestX.toFixed(2)}
            current={agent.rtk.x.toFixed(2)} />
        </CurrentStatusContainer>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <CurrentStatusContainer>
          <div>
            {agent.rtk.y.toFixed(2)}
          </div>
          <DestinationErrorRange
            dest={rtkDestY.toFixed(2)}
            current={agent.rtk.y.toFixed(2)} />
        </CurrentStatusContainer>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <CurrentStatusContainer>
          <div>
            {agent.rtk.z.toFixed(2)}
          </div>
          <DestinationErrorRange
            dest={0}
            current={0} />
        </CurrentStatusContainer>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <CurrentStatusContainer>
          <div>
            {rtkDestX.toFixed(2)}
          </div>
          <DestinationErrorRange
            dest={0}
            current={0} />
        </CurrentStatusContainer>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <div>
          {rtkDestY.toFixed(2)}
        </div>
        <DestinationErrorRange
          dest={0}
          current={0} />
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <div>
          -
        </div>
        <DestinationErrorRange
          dest={0}
          current={0} />
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <CurrentStatusContainer>
          <div>
            {agent.ned.x.toFixed(2)}
          </div>
          <DestinationErrorRange
            dest={localDestX.toFixed(2)}
            current={agent.ned.x.toFixed(2)} />
        </CurrentStatusContainer>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <CurrentStatusContainer>
          <div>
            {agent.ned.y.toFixed(2)}
          </div>
          <DestinationErrorRange
            dest={localDestY.toFixed(2)}
            current={agent.ned.y.toFixed(2)} />
        </CurrentStatusContainer>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <CurrentStatusContainer>
          <div>
            {agent.ned.z.toFixed(2)}
          </div>
          <DestinationErrorRange
            dest={0}
            current={0} />
        </CurrentStatusContainer>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <CurrentStatusContainer>
          <div>
            {localDestX.toFixed(2)}
          </div>
          <DestinationErrorRange
            dest={0}
            current={0} />
        </CurrentStatusContainer>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <div>
          {localDestY.toFixed(2)}
        </div>
        <DestinationErrorRange
          dest={0}
          current={0} />
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd>
        <div>
          -
        </div>
        <DestinationErrorRange
          dest={0}
          current={0} />
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd close={true}>
        <button onClick={handleRebootButton}>Reboot</button>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd close={true}>
        <button onClick={handleTakeOffButton}>takeoff</button>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd close={true}>
        <button onClick={handleMoveButton}>move</button>
      </DeployStatusTbodyTd>
      <DeployStatusTbodyTd close={true}>
        <button onClick={handleLandButton}>land</button>
      </DeployStatusTbodyTd>
    </DeployStatusTbodyTr>
  )
}, DeployAgentAreEqual);

const GcsDeployModal = ({showAutoSort}) => {
  const [gridX, setGridX] = useState(5);
  const [gridY, setGridY] = useState(5);
  const [offSetX, setOffsetX] = useState(0);
  const [offSetY, setOffsetY] = useState(0);
  const [rotation, setRotation] = useState(0);
  const [offsetHead, setOffsetHead] = useState(0);
  const [gridInterval, setGridInterval] = useState(3);
  const [readyToStart, setReadyToStart] = useState(false);

  const gridXReference = useRef();
  const rotationReference = useRef();
  const gridIntervalReference = useRef();
  const {
    loading: contextLoading,
    data: context,
    error: contextError
  } = useSelector((state) => state.context);

  const preset = () => {
    if(!contextLoading && !contextError){
      context.agents && Object.values(context.agents).map((agent, i) => {
        if(agent.id === 1){
          setOffsetX(agent.rtk.x);
          setOffsetY(agent.rtk.y);
          setOffsetHead(agent.angle.yaw);
        }
      });
    }

    setReadyToStart(!readyToStart);
  };

  return (
    showAutoSort &&
    <GcsDeployModalContainer>
      <ModalContainer>
        <ModalHeader>
          <ModalHeaderName>
            Deploy
          </ModalHeaderName>
        </ModalHeader>
        <DeployrModalBody>
          <ModalBodySetting>
            <DeployModalInputContainer display={true}>
              <DeployModalInputPair>
                <ModalInputLabel>X Grid</ModalInputLabel>
                <ModalInput
                  value={gridX}
                  type={"number"}
                  onChange={(e) => setGridX(e.target.value)}
                  ref={gridXReference}
                  disabled={readyToStart}
                  disable={readyToStart}
                />
              </DeployModalInputPair>
              <DeployModalInputPair>
                <ModalInputLabel>Rotation</ModalInputLabel>
                <ModalInput
                  value={rotation}
                  type={"number"}
                  onChange={(e) => setRotation(e.target.value)}
                  ref={rotationReference}
                  disabled={readyToStart}
                  disable={readyToStart}
                />
              </DeployModalInputPair>
              <DeployModalInputPair>
                <ModalInputLabel>Interval</ModalInputLabel>
                <ModalInput
                  value={gridInterval}
                  type={"number"}
                  onChange={(e) => setGridInterval(e.target.value)}
                  ref={gridIntervalReference}
                  disabled={readyToStart}
                  disable={readyToStart}
                />
              </DeployModalInputPair>
            </DeployModalInputContainer>
            <button
              onClick={preset}>
              {readyToStart ? "Set": "Reset"}
            </button>
          </ModalBodySetting>
          {readyToStart && (
            <ModalBodyContent>
              <DeployStatusTable>
                <DeployStatusThead>
                  <DeployStatusTheadTr>
                    <DeployStatusTheadTd rowSpan={3}>id</DeployStatusTheadTd>
                    <DeployStatusTheadTd rowSpan={3}>sysid</DeployStatusTheadTd>
                    <DeployStatusTheadTd rowSpan={3}>head</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={6}>RTK</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={6}>LOCAL</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={4} rowSpan={3}>Control</DeployStatusTheadTd>
                  </DeployStatusTheadTr>
                  <DeployStatusTheadTr>
                    <DeployStatusTheadTd colSpan={3}>src</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={3}>dest</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={3}>src</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={3}>dest</DeployStatusTheadTd>
                  </DeployStatusTheadTr>
                  <DeployStatusTheadTr>
                    <DeployStatusTheadTd>X</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Y</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Z</DeployStatusTheadTd>
                    <DeployStatusTheadTd>X</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Y</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Z</DeployStatusTheadTd>
                    <DeployStatusTheadTd>X</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Y</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Z</DeployStatusTheadTd>
                    <DeployStatusTheadTd>X</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Y</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Z</DeployStatusTheadTd>
                  </DeployStatusTheadTr>
                </DeployStatusThead>
                <DeployStatusTbody>
                  { context.agents &&
                    Object.entries(context.agents)
                      .sort(([key1, agent1], [key2, agent2]) => agent1.id - agent2.id)
                      .map(([key, agent], id) => (
                        <DeployAgentStatus
                          agent={agent}
                          rotation={rotation}
                          offsetHead={offsetHead}
                          gridX={gridX}
                          gridInterval={gridInterval}
                          offSetX={offSetX}
                          offSetY={offSetY}
                        />
                      ))
                  }
                </DeployStatusTbody>
              </DeployStatusTable>
            </ModalBodyContent>

          )}
        </DeployrModalBody>
      </ModalContainer>
    </GcsDeployModalContainer>
  )
}

const DestinationErrorRange = ({dest, current}) => {
  if(dest === current) return (
    <DestinationErrorRangeContainer isPositive={dest - current >= 0} isWhite={true}>
      -
    </DestinationErrorRangeContainer>
  )
  return (
    <DestinationErrorRangeContainer isPositive={dest - current >= 0} isWhite={false}>
      {dest - current >= 0 ? "+" : "-"}
      {Math.abs(dest - current).toFixed(2)}
    </DestinationErrorRangeContainer>
  )
}

export default React.memo(GcsDeployModal);

const DeployStatusTable = React.memo(styled.table`

`)

const DeployStatusThead = React.memo(styled.thead`
  text-align: center;
`)

const DeployStatusTheadTd = React.memo(styled.td`
  text-align: center;
`)

const DeployStatusTheadTr = React.memo(styled.tr`

`)

const DeployStatusTbody = React.memo(styled.tbody`

`)

const DeployStatusTbodyTr = styled.tr`
  color: ${props => props.fixed ? "#ffffff": "#ff0000"}
`

const DeployStatusTbodyTd = styled.td`
  padding-left: ${props => props.close ? "0": "10px"};
  padding-right: ${props => props.close ?  "0": "10px"};
  text-align: center;
`


const DeployModalInputContainer = React.memo(styled(ModalInputContainer)`
  width: 50%;
  padding: 0 10px;
`)

const ModalBodySetting = React.memo(styled.div`
  margin-bottom: 5px;
  display: flex;
`)

const ModalBodyContent = React.memo(styled.div`
  border-top: rgba(90, 90, 90, 0.3) 1px solid;
`)

const DeployModalInputPair = React.memo(styled(ModalInputPair)`
  justify-content: space-around;
  width: 100%;
`)

const DeployModalInput = React.memo(styled(ModalInput)`
  background-color: ${props => props.disable ? "#ffffff": 'rgba(0, 0, 0, 0.7)'};
`)

const ModalHeadSub = React.memo(styled.div`
  
`)

const GcsDeployModalContainer = React.memo(styled(Modal)`
  width: 130vh;
  height: 50vh;
  top: 6vh;
  overflow: scroll;
`)

const DeployrModalBody = React.memo(styled(ModalBody)`
  height: 90%;
`)

const CurrentStatusContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
`


const DestinationErrorRangeContainer = styled.div`
  color: ${props => props.isWhite ? "white ! important" : "white"};
  color: ${props => props.isPositive ? "#ff5151": "#00aaff"};
  font-size: ${FontSize.micro};
`


const DeployGcsOrderButton = React.memo(styled.div`
  display: flex;
  align-items: center;
  text-align: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};
  color: white !important;

  :hover {
    background: rgba(255, 255, 255, 0.2);
  }
`)