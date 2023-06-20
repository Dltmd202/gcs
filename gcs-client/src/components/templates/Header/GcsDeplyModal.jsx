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

const GcsDeployModal = ({showAutoSort}) => {
  const [gridX, setGridX] = useState(5);
  const [gridY, setGridY] = useState(5);
  const [offSetX, setOffsetX] = useState(0);
  const [offSetY, setOffsetY] = useState(0);
  const [rotation, setRotation] = useState(0);
  const [offSetHead, setOffSetHead] = useState(0);
  const [gridInterval, setGridInterval] = useState(3);
  const [readyToStart, setReadyToStart] = useState(false);
  const [context, setContext] = useState();

  const gridXReference = useRef();
  const rotationReference = useRef();
  const gridIntervalReference = useRef();
  const {
    loading: contextLoading,
    data: realTimeContext,
    error: contextError
  } = useSelector((state) => state.context);

  useEffect(() => {
    const timer = setTimeout(() => {
      setContext(realTimeContext)
      console.log(realTimeContext);
    }, 5000);

    return () => clearTimeout(timer);
  }, [realTimeContext])


  const handleTakeOffButton = useCallback((id, sysid, x, y) => {
    deployApi.takeOff(sysid, x, y, -1.5, radianToDegree(offSetHead));
  }, [offSetHead]);

  const handleMoveButton = useCallback((id, sysid) => {
    deployApi.move(
      sysid,
      getLocalDestX(id, sysid),
      getLocalDestY(id, sysid),
      -1.5,
      radianToDegree(offSetHead)
    );
  }, [offSetHead]);

  const handleRebootButton = (id, sysid) => {
    const result = window.confirm(`SYSID - ${sysid} 을(를) Reboot 하겠습니까?`)
    if (result) {
      agentApi.reboot(sysid);
    }
  }

  const handleLandButton = (id, sysid) => {
    deployApi.land(sysid);
  }

  const preset = () => {

    setContext(realTimeContext);
    if(!contextLoading && !contextError){
      context.agents && Object.values(context.agents).map((agent, i) => {
        if(agent.id === 1){
          setOffsetX(agent.rtk.x);
          setOffsetY(agent.rtk.y);
          setOffSetHead(agent.angle.yaw);
        }
      });
    }

    setReadyToStart(!readyToStart);
  }

  const getRTKDestinationX = useCallback((id, sysid) => {
    const x = ((id - 1) % gridX) * gridInterval;
    const y = -1 * (Math.floor((id - 1) / gridX)) * gridInterval;
    if(rotation === 0) return offSetX + x;
    return offSetX + (x * Math.cos(rotation) - y * Math.sin(rotation));
  }, [gridX, gridInterval, rotation, offSetX]);

  const getRTKDestinationY = useCallback((id, sysid) => {
    const x = ((id - 1) % gridX) * gridInterval;
    const y = -1 * (Math.floor((id - 1) / gridX)) * gridInterval;
    if(rotation === 0) return offSetY - y;
  }, [gridX, gridInterval, rotation, offSetY]);

  const getLocalDestX = (id, sysid) => {
    const rtkDestX = getRTKDestinationX(id);
    const rtkCurrentX = context.agents[sysid].rtk.x;

    return context.agents[sysid].ned.x + (rtkDestX - rtkCurrentX);
  }

  const getLocalDestY = (id, sysid) => {
    const rtkDestY = getRTKDestinationY(id);
    const rtkCurrentY = context.agents[sysid].rtk.y;

    return context.agents[sysid].ned.y + (rtkDestY - rtkCurrentY);
  }


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
                      // .sort(([key1, agent1], [key2, agent2]) => agent1.id - agent2.id)
                      .map(([key, agent], id) => (
                      <DeployStatusTbodyTr key={id} fixed={(agent.status & agentStatusMask[9].mask) !== 0}>
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
                              dest={getRTKDestinationX(agent.id, agent.sysid).toFixed(2)}
                              current={agent.rtk.x.toFixed(2)} />
                          </CurrentStatusContainer>
                        </DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>
                          <CurrentStatusContainer>
                            <div>
                              {agent.rtk.y.toFixed(2)}
                            </div>
                            <DestinationErrorRange
                              dest={getRTKDestinationY(agent.id, agent.sysid).toFixed(2)}
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
                              {getRTKDestinationX(agent.id, agent.sysid).toFixed(2)}
                            </div>
                            <DestinationErrorRange
                              dest={0}
                              current={0} />
                          </CurrentStatusContainer>
                        </DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>
                          <div>
                            {getRTKDestinationY(agent.id, agent.sysid).toFixed(2)}
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
                              dest={getLocalDestX(agent.id, agent.sysid).toFixed(2)}
                              current={agent.ned.x.toFixed(2)} />
                          </CurrentStatusContainer>
                        </DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>
                          <CurrentStatusContainer>
                            <div>
                              {agent.ned.y.toFixed(2)}
                            </div>
                            <DestinationErrorRange
                              dest={getLocalDestY(agent.id, agent.sysid).toFixed(2)}
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
                              {getLocalDestX(agent.id, agent.sysid).toFixed(2)}
                            </div>
                            <DestinationErrorRange
                              dest={0}
                              current={0} />
                          </CurrentStatusContainer>
                        </DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>
                          <div>
                            {getLocalDestY(agent.id, agent.sysid).toFixed(2)}
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
                          <button onClick={() => handleRebootButton(agent.id, agent.sysid)}>Reboot</button>
                        </DeployStatusTbodyTd>
                        <DeployStatusTbodyTd close={true}>
                          <button onClick={() => handleTakeOffButton(agent.id, agent.sysid, agent.ned.x, agent.ned.y)}>takeoff</button>
                        </DeployStatusTbodyTd>
                        <DeployStatusTbodyTd close={true}>
                          <button onClick={() => handleMoveButton(agent.id, agent.sysid)}>move</button>
                        </DeployStatusTbodyTd>
                        <DeployStatusTbodyTd close={true}>
                          <button onClick={() => handleLandButton(agent.id, agent.sysid)}>land</button>
                        </DeployStatusTbodyTd>
                      </DeployStatusTbodyTr>
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