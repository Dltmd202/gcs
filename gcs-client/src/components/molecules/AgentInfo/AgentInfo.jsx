import styled from "styled-components";
import React, {useState} from "react";
import Colors from "../../../styles/colors";
import Button from "../../atoms/Button/Button";
import {FontSize, FontWeight} from "../../../styles/font";
import {Media} from "../../../styles/media";
import agentApi from "../../../api/agent";
import {TreeItem, TreeView} from "@mui/lab";
import {ExpandMoreSharp} from "@mui/icons-material";
import {ChevronRight} from "@mui/icons-material";
import StyledTreeItem from "../../atoms/ThreeView/StyledTreeItem";
import radianToDegree from "../../../module/coordinate/RadToDgreeConverter";
import {agentStatusMask} from "../../../module/coordinate/agentStatus";

const AgentInfo = ({
                     agentObject,
                     focus,
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

  let containerColor = Colors.textPrimary;
  if ((status & agentStatusMask[25].mask) !== 0) {
    containerColor = Colors.warning;
  } else if ((status & agentStatusMask[26].mask) !== 0) {
    containerColor = Colors.orange;
  } else if ((status & agentStatusMask[9].mask) === 0){
    containerColor = Colors.red;
  }

  const handleFocus = () => {
    if(active)
      focus(sysid);
  }

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
      active={active}
      color={color}
      textColor={containerColor}
      onClick={handleFocus}
      {...props}
    >
      <AgentBodyContainer>
        <TreeView
          aria-label="file system navigator"
          defaultCollapseIcon={<ExpandMoreSharp />}
          defaultExpandIcon={<ChevronRight />}
          sx={{width: "90%", padding: 0, "font-size": FontSize.micro}}
        >
          <StyledTreeItem nodeId={"1"} labelText={
              <AgentHeadName>{sysid}</AgentHeadName>
          } labelInfo={
            <AgentHeadBatteryContainer>
              {battery.toString().padStart(2, '0')}%
            </AgentHeadBatteryContainer>
          } sx={{width: "100%"}}>
            {active && (
              <>
                <StyledTreeItem nodeId={"2"} labelText={"Configuration"}>
                  <StyledTreeItem
                    nodeId={"100"} labelText={"ID"}
                    labelInfo={agentObject.id}
                  />
                  <StyledTreeItem
                    nodeId={"3"} labelText={"IP"}
                    labelInfo={agentObject.ip}
                  />
                  <StyledTreeItem
                    nodeId={"4"} labelText={"MODE"}
                    labelInfo={agentObject.mode}
                  />
                  <StyledTreeItem
                    nodeId={"5"} labelText={"VEHICLE"}
                    labelInfo={agentObject.vehicle}
                  />
                </StyledTreeItem>
                <StyledTreeItem nodeId={"6"} labelText="Status">
                  <StyledTreeItem
                    nodeId={"7"}
                    labelText={"NED Coordinate"}
                  >
                    <StyledTreeItem
                      nodeId={"8"} labelText={"X"}
                      labelInfo={agentObject.ned.x.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"9"} labelText={"Y"}
                      labelInfo={agentObject.ned.y.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"10"} labelText={"Z"}
                      labelInfo={agentObject.ned.z.toFixed(2)}
                    />
                  </StyledTreeItem>

                  <StyledTreeItem
                    nodeId={"11"}
                    labelText={"LLH Coordinate"}
                  >
                    <StyledTreeItem
                      nodeId={"12"} labelText={"Latitude"}
                      labelInfo={agentObject.llh.lat.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"13"} labelText={"Longitude"}
                      labelInfo={agentObject.llh.lng.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"14"} labelText={"Altitude"}
                      labelInfo={agentObject.llh.alt.toFixed(2)}
                    />
                  </StyledTreeItem>

                  <StyledTreeItem
                    nodeId={"15"}
                    labelText={"Velocity"}
                  >
                    <StyledTreeItem
                      nodeId={"16"} labelText={"VX"}
                      labelInfo={agentObject.velocity.vx.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"17"} labelText={"VY"}
                      labelInfo={agentObject.velocity.vy.toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"18"} labelText={"VZ"}
                      labelInfo={agentObject.velocity.vz.toFixed(2)}
                    />
                  </StyledTreeItem>

                  <StyledTreeItem nodeId={"19"} labelText={"Angle"}>
                    <StyledTreeItem
                      nodeId={"20"} labelText={"ROLL"}
                      labelInfo={radianToDegree(agentObject.angle.roll).toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"21"} labelText={"PITCH"}
                      labelInfo={radianToDegree(agentObject.angle.pitch).toFixed(2)}
                    />
                    <StyledTreeItem
                      nodeId={"22"} labelText={"YAW"}
                      labelInfo={radianToDegree(agentObject.angle.yaw).toFixed(2)}
                    />
                  </StyledTreeItem>
                </StyledTreeItem>
              </>
            )}
          </StyledTreeItem>
        </TreeView>
      </AgentBodyContainer>
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
  background: #2e3c43;
  color: ${props => props.textColor};
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 0.3em;
  border: 1px solid ${props => props.active ? props.color: Colors.backgroundDisabled};
  margin: 0.8em;
  padding: 0.5em 1em;
  background-color: ${props => props.active ? 
          Colors.backgroundPointed : Colors.backgroundDisabled};
  font-size: ${FontSize.small};
  
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


export default React.memo(AgentInfo);