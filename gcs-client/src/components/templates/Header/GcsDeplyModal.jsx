import agentApi from "../../../api/agent";
import React, {useEffect, useRef, useState} from "react";
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

const GcsDeployModal = ({showAutoSort}) => {
  const [gridX, setGridX] = useState(5);
  const [gridY, setGridY] = useState(5);
  const [offSetX, setOffsetX] = useState(0);
  const [offSetY, setOffsetY] = useState(0);
  const [gridInterval, setGridInterval] = useState(3);
  const [readyToStart, setReadyToStart] = useState(false);
  const [idIndex, setIdIndex] = useState({});


  const gridXReference = useRef();
  const gridYReference = useRef();
  const gridIntervalReference = useRef();
  const {
    loading: contextLoading,
    data: context,
    error: contextError
  } = useSelector((state) => state.context);

  useEffect(() => {
    const idx = {};
    Object.values(context.agents).map((agent, i) => {
      idx[agent.id] = agent;
    });
    setIdIndex(idx);
  }, [Object.keys(context?.agents).length])


  useEffect(() => {
    const agent = context
  }, [context])

  const handleTakeOffButton = (id, sysid) => {
    agentApi.globalDisarm();
    console.log("fuck1")

    setTimeout(() => {
      agentApi.offboard(sysid);
      console.log("fuck2")

      setTimeout(() => {
        agentApi.globalDestination(0, 0, -1);
        console.log("fuck3")

        setTimeout(() => {
          agentApi.arm(sysid);
          console.log("fuck4")
        }, 1000);
      }, 1000);
    }, 1000);
  }

  const handleMoveButton = (id, sysid) => {
    agentApi.globalDestination(0, 0, -1);
  }

  const handleLandButton = (id, sysid) => {
    agentApi.land(sysid);
    agentApi.disarm(sysid);
  }


  const preset = () => {
    setReadyToStart(!readyToStart);

    if(!contextLoading && !contextError){
      context.agents && Object.values(context.agents).map((agent, i) => {
        if(agent.id === 1){
          setOffsetX(agent.rtk.x);
          setOffsetY(agent.rtk.y);
        }
      });
    }
  }

  const getRTKDestinationX = (id, sysid) => {
    return offSetX + (gridInterval * ((id - 1) % gridX));
  }

  const getRTKDestinationY = (id, sysid) => {
    return offSetY + (gridInterval * (Math.floor((id - 1) / gridX)));
  }

  const getLocalDestX = (id, sysid) => {
    const rtkDestX = getRTKDestinationX(id);
    const rtkCurrentX = context.agents[sysid].rtk.x;

    console.log(`localDestition X -[${id}]: rtkDestX: ${rtkDestX}, rtkCurrentX: ${rtkCurrentX} -> ${context.agents[sysid].ned.x + (rtkDestX - rtkCurrentX)}`)
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
                <ModalInputLabel>Y Grid</ModalInputLabel>
                <ModalInput
                  value={gridY}
                  type={"number"}
                  onChange={(e) => setGridY(e.target.value)}
                  ref={gridYReference}
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
                    <DeployStatusTheadTd colSpan={4}>RTK</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={4}>LOCAL</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={3} rowSpan={3}>Control</DeployStatusTheadTd>
                  </DeployStatusTheadTr>
                  <DeployStatusTheadTr>
                    <DeployStatusTheadTd colSpan={2}>src</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={2}>dest</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={2}>src</DeployStatusTheadTd>
                    <DeployStatusTheadTd colSpan={2}>dest</DeployStatusTheadTd>
                  </DeployStatusTheadTr>
                  <DeployStatusTheadTr>
                    <DeployStatusTheadTd>X</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Y</DeployStatusTheadTd>
                    <DeployStatusTheadTd>X</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Y</DeployStatusTheadTd>
                    <DeployStatusTheadTd>X</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Y</DeployStatusTheadTd>
                    <DeployStatusTheadTd>X</DeployStatusTheadTd>
                    <DeployStatusTheadTd>Y</DeployStatusTheadTd>
                  </DeployStatusTheadTr>
                </DeployStatusThead>
                <DeployStatusTbody>
                  {!contextLoading &&
                    Object.entries(context.agents)
                      .sort(([key1, agent1], [key2, agent2]) => agent1.id - agent2.id)
                      .map(([key, agent], id) => (
                      <DeployStatusTbodyTr key={id} fixed={(agent.status & agentStatusMask[9].mask) !== 0}>
                        <DeployStatusTbodyTd>{agent.id}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{agent.sysid}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{agent.rtk.x.toFixed(2)}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{agent.rtk.y.toFixed(2)}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{getRTKDestinationX(agent.id, agent.sysid).toFixed(2)}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{getRTKDestinationY(agent.id, agent.sysid).toFixed(2)}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{agent.ned.x.toFixed(2)}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{agent.ned.y.toFixed(2)}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{getLocalDestX(agent.id, agent.sysid).toFixed(2)}</DeployStatusTbodyTd>
                        <DeployStatusTbodyTd>{getLocalDestY(agent.id, agent.sysid).toFixed(2)}</DeployStatusTbodyTd>
                        {agent.id !== 1 && (
                          <>
                            <DeployStatusTbodyTd close={true}>
                              <button onClick={() => handleTakeOffButton(agent.id, agent.sysid)}>takeoff</button>
                            </DeployStatusTbodyTd>
                            <DeployStatusTbodyTd close={true}>
                              <button onClick={() => handleMoveButton(agent.id, agent.sysid)}>move</button>
                            </DeployStatusTbodyTd>
                            <DeployStatusTbodyTd close={true}>
                              <button onClick={() => handleLandButton(agent.id, agent.sysid)}>land</button>
                            </DeployStatusTbodyTd>
                          </>
                        )}
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
  width: 100vh;
  min-height: 10vh;
  max-height: 75vh;
  top: 6vh;
`)

const StyledGcsOrderContainer = React.memo(styled.div`
  display: flex;
  height: 100%;
  align-items: center;
`)

const DeployrModalBody = React.memo(styled(ModalBody)`
  height: 90%;
  overflow: scroll;
`)


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