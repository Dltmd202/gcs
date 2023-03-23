import styled from "styled-components";
import radianToDegree from "../../../module/coordinate/RadToDgreeConverter";
import React, {useState} from "react";
import Colors from "../../../styles/colors";
import Button from "../../atoms/Button/Button";
import {FontSize, FontWeight} from "../../../styles/font";
import {Media} from "../../../styles/media";
import agentApi from "../../../api/agent";

const AgentInfo = ({
                     agentObject,
                     focused,
                     style,
                     ...props
}) => {
  const {
    active,
    sysid,
    ned,
    color,
    battery,
    complementaryColor,
    angle
  } = agentObject;

  const [drop, isDrop] = useState(false);

  const handleDropDown = () => {
    isDrop(!drop);
  }

  const handleTakeOff = () => {
    agentApi.takeOff(sysid, 255);
  }

  const handleArm = () => {
    agentApi.arm(sysid);
  }

  const handleDisArm = () => {
    agentApi.disarm(sysid);
  }


  return (
    <AgentInfoContainer
      color={complementaryColor}
      {...props}
    >
      <AgentHeadContainer>
        <AgentHeadName color={active ? color : Colors.grey}>SYSID - {sysid}</AgentHeadName>
        <AgentHeadBatteryContainer>
          {battery.toString().padStart(2, '0')}%
        </AgentHeadBatteryContainer>
      </AgentHeadContainer>
      {active && (
        <AgentBodyContainer>
          <AgentInfoTable
            onClick={handleDropDown}
          >
            <AgentInfoThead>
              <AgentInfoTr>
                <AgentInfoTd>POS - X</AgentInfoTd>
                <AgentInfoTd>POS - Y</AgentInfoTd>
                <AgentInfoTd>POS - Z</AgentInfoTd>
                {drop && (
                  <>
                    <AgentInfoTd>ROLL</AgentInfoTd>
                    <AgentInfoTd>PITCH</AgentInfoTd>
                    <AgentInfoTd>YAW</AgentInfoTd>
                  </>
                )}
              </AgentInfoTr>
            </AgentInfoThead>
            <AgentInfoTBody>
              <AgentInfoTr>
                <AgentInfoTd>{ned.x.toFixed(6)}</AgentInfoTd>
                <AgentInfoTd>{ned.y.toFixed(6)}</AgentInfoTd>
                <AgentInfoTd>{ned.z.toFixed(6)}</AgentInfoTd>
                {drop && (
                  <>
                    <AgentInfoTd>{radianToDegree(angle.roll).toFixed(6)}°</AgentInfoTd>
                    <AgentInfoTd>{radianToDegree(angle.pitch).toFixed(6)}°</AgentInfoTd>
                    <AgentInfoTd>{radianToDegree(angle.yaw).toFixed(6)}°</AgentInfoTd>
                  </>
                )}
              </AgentInfoTr>
            </AgentInfoTBody>
          </AgentInfoTable>
        </AgentBodyContainer>
      )}
    </AgentInfoContainer>
  )
}

const AgentBodyContainer = React.memo(styled.div`
`)

const AgentOrderContainer = React.memo(styled.div`
  padding: 0.5em 0;
`)


const AgentOrderButton = React.memo(styled(Button)`
  font-weight: ${FontWeight.regular};
  font-size: ${FontSize.small};
  border-radius: 5px;
  margin: 0.2em 0;
  
  
  @media ${Media.sm} {
    min-height: 30px;
    font-size: ${FontSize.small};
  }
  @media ${Media.md} {
    min-height: 30px;
    font-size: ${FontSize.medium};
  }
  @media ${Media.lg} {
    min-height: 30px;
    font-size: ${FontSize.medium};
  }
`)

const AgentInfoContainer = React.memo(styled.div`
  background: ${Colors.background};
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 0.3em;
  margin: 0.8em;
  padding: 0.5em 1em;

  // :active{
  //   cursor: pointer;
  //   background: ${Colors.backgroundMenu};
  // }
`)



const AgentHeadContainer = React.memo(styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5em;
  
`)

const AgentHeadName = React.memo(styled.p`
  margin: 0;
  color: ${props => props.color};
  text-align: left;
`)

const AgentHeadBatteryContainer = React.memo(styled.div`
  
`)

const AgentInfoTable = React.memo(styled.table`
  display: flex;
  overflow-x: auto;
  overflow-y: hidden;

`)

const AgentInfoThead = React.memo(styled.thead`
  display: flex;
  margin-right: 1.5em;
  text-align: right;
  min-width: 20%;
`)

const AgentInfoTBody = React.memo(styled.tbody`
  display: flex;
`)

const AgentInfoTr = React.memo(styled.tr`
  font-size: small;
  text-align: left;
`)

const AgentInfoTh = React.memo(styled.th`
  display: block;
`)

const AgentInfoTd = styled.td`
  display: block;
  padding: 0.2em;
`



export default React.memo(AgentInfo);