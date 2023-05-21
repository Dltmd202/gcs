import styled from "styled-components";
import React, {useState} from "react";
import Colors from "../../../styles/colors";
import Button from "../../atoms/Button/Button";
import {FontSize, FontWeight} from "../../../styles/font";
import {Media} from "../../../styles/media";
import agentApi from "../../../api/agent";
import {TreeItem, TreeView} from "@mui/lab";
import StyledTreeItem from "../../atoms/ThreeView/StyledTreeItem";
import {agentStatusMask, agentStatusMasking} from "../../../module/coordinate/agentStatus";
import HeaderButton from "../../atoms/Button/HeaderButton";

const AgentDetailInfo = ({
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
    angle,
    status
  } = agentObject;

  const [drop, isDrop] = useState(true);

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

  const defualtExpaneded = ["1", "2", "3", "4", "5", "6", "7", "23", "25"]

  const parseStatusDetail = (status) => {
    const parsedStatus = [];

    for (const mask of agentStatusMask) {
      if((mask.mask & status) !== 0){
        parsedStatus.push(mask.name);
      }
    }

    return parsedStatus;
  }

  const handleReboot = (sysid) => {
    agentApi.reboot(sysid);
  }

  const handleLand = (sysid) => {
    agentApi.land(sysid);
  }


  return (
    <AgentInfoContainer
      active={active}
      color={color}
      {...props}
    >
      <AgentBodyContainer>
        <TreeView
          aria-label="file system navigator"
          defaultCollapseIcon={null}
          defaultExpandIcon={null}
          defaultExpanded={defualtExpaneded}
          sx={{width: "90%", padding: 0, "font-size": FontSize.micro}}
        >
          <StyledTreeItem
            nodeId={"1"} labelText={
              <AgentHeadName>{sysid}</AgentHeadName>
          } labelInfo={
            <AgentHeadBatteryContainer>
              {battery.toString().padStart(2, '0')}%
            </AgentHeadBatteryContainer>
          } sx={{width: "100%"}} defaultExpanded>
            {active && (
              <>
                <StyledTreeItem nodeId={"2"} labelText={"Configuration"} defaultExpanded>
                  <StyledTreeItem
                    nodeId={"100"} labelText={"ID"}
                    labelInfo={agentObject.id}
                  />
                  <StyledTreeItem
                    nodeId={"8"} labelText={"IP"}
                    labelInfo={agentObject.ip}
                  />
                  <StyledTreeItem
                    nodeId={"9"} labelText={"MODE"}
                    labelInfo={agentObject.mode}
                  />
                  <StyledTreeItem
                    nodeId={"10"} labelText={"VEHICLE"}
                    labelInfo={agentObject.vehicle}
                  />
                </StyledTreeItem>
                <StyledTreeItem nodeId={"3"} labelText="Status" defaultExpanded={true}>
                  <StyledTreeItem
                    nodeId={"26"}
                    labelText={"Tow"}
                    labelInfo={agentObject.tow}
                  />
                  <StyledTreeItem
                    nodeId={"4"}
                    labelText={"Local NED"}
                  >
                    <StyledTreeItem
                      nodeId={"11"} labelText={"X"}
                      labelInfo={agentObject.ned.x.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"12"} labelText={"Y"}
                      labelInfo={agentObject.ned.y.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"13"} labelText={"Z"}
                      labelInfo={agentObject.ned.z.toFixed(2)}
                    />
                  </StyledTreeItem>

                  <StyledTreeItem
                    nodeId={"25"}
                    labelText={"RTK NED"}
                  >
                    <StyledTreeItem
                      nodeId={"26"} labelText={"N"}
                      labelInfo={agentObject.rtk.x.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"27"} labelText={"E"}
                      labelInfo={agentObject.rtk.y.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"28"} labelText={"D"}
                      labelInfo={agentObject.rtk.z.toFixed(2)}
                    />
                  </StyledTreeItem>



                  <StyledTreeItem
                    nodeId={"5"}
                    labelText={"LLH Coordinate"}
                  >
                    <StyledTreeItem
                      nodeId={"14"} labelText={"Latitude"}
                      labelInfo={agentObject.llh.lat.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"15"} labelText={"Longitude"}
                      labelInfo={agentObject.llh.lng.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"16"} labelText={"Altitude"}
                      labelInfo={agentObject.llh.alt.toFixed(2)}
                    />
                  </StyledTreeItem>
                  <StyledTreeItem nodeId={"7"} labelText={"Angle"}>
                    <StyledTreeItem
                      nodeId={"20"} labelText={"ROLL"}
                      labelInfo={agentObject.angle.roll.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"21"} labelText={"PITCH"}
                      labelInfo={agentObject.angle.pitch.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"22"} labelText={"YAW"}
                      labelInfo={agentObject.angle.yaw.toFixed(2)}
                    />
                  </StyledTreeItem>
                  <StyledTreeItem
                    nodeId={"23"} labelText={"Status"}
                    labelInfo={agentObject.status}
                  >
                    <StyledTreeItem nodeId={"24"} labelText={
                        parseStatusDetail(agentObject.status).map((st, i) => (
                          <PlanStatus key={st}>{st}</PlanStatus>
                        ))}
                    />
                  </StyledTreeItem>
                </StyledTreeItem>
              </>
            )}
          </StyledTreeItem>
        </TreeView>
      </AgentBodyContainer>
      <EmergencyControl>
        <EmergencyButton onClick={() => handleReboot(sysid)}>
          reboot
        </EmergencyButton>
        <EmergencyButton onClick={() => handleLand(sysid)}>
          land
        </EmergencyButton>
        <EmergencyButton onClick={() => agentApi.disarm(sysid)}>
          disarm
        </EmergencyButton>
        <EmergencyButton onClick={() => agentApi.destination(sysid, 0, 0, 0)}>
          ground
        </EmergencyButton>
      </EmergencyControl>
    </AgentInfoContainer>
  )
}

const AgentAttributeCol = ({attribute, val}) => {
  return (
    <div style={{display: 'flex'}}>
      <div>
        {attribute}
      </div>
      <div>
        {val}
      </div>
    </div>
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

const PlanStatus = React.memo(styled.div`
  text-align: left;
`)

const AgentInfoContainer = React.memo(styled.div`
  background: #2e3c43;
  color: ${props => props.active ? Colors.textPrimary: Colors.textQuaternary};
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 0.3em;
  border: 1px solid ${props => props.active ? props.color: Colors.backgroundDisabled};
  margin: 0.8em;
  padding: 0.5em 1em 1em 0;
  background-color: ${props => props.active ? 
          Colors.backgroundPointed : Colors.backgroundDisabled};
  font-size: ${FontSize.small};
  height: 45%;
  overflow: scroll;
  
  :hover{
    cursor: pointer;
  }

  :active{
    background: ${props => props.active ?
            Colors.backgroundHover : Colors.backgroundDisabled};
  }
`)



const AgentHeadContainer = React.memo(styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 0.5em;
  width: 100%;
  
`)

const AgentHeadName = React.memo(styled.p`
  margin: 0;
  text-align: left;
`)

const AgentHeadBatteryContainer = React.memo(styled.div`
  
`)

export const EmergencyButton = React.memo(styled(HeaderButton)`
  display: flex;
  align-items: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};
  border: #ff5151 1px solid;
  padding: 0 5px;
  margin-right: 5px;

  :hover {
    background: rgba(255, 255, 255, 0.2);
  }
`)

const EmergencyControl = React.memo(styled.div`
  display: flex;
  padding: 10px;
`)


export default React.memo(AgentDetailInfo);