import styled from "styled-components";
import React, {useState} from "react";
import Colors from "../../../styles/colors";
import Button from "../../atoms/Button/Button";
import {FontSize, FontWeight} from "../../../styles/font";
import {Media} from "../../../styles/media";
import agentApi from "../../../api/agent";
import {TreeView} from "@mui/lab";
import {ExpandMoreSharp} from "@mui/icons-material";
import {ChevronRight} from "@mui/icons-material";
import StyledTreeItem from "../../atoms/ThreeView/StyledTreeItem";

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


  return (
    <AgentInfoContainer
      active={active}
      color={color}
      {...props}
    >
      <AgentHeadContainer>
        <AgentHeadName>SYSID - {sysid}</AgentHeadName>
        <AgentHeadBatteryContainer>
          {battery.toString().padStart(2, '0')}%
        </AgentHeadBatteryContainer>
      </AgentHeadContainer>
      {active && (
        <AgentBodyContainer>
          <TreeView
            aria-label="file system navigator"
            defaultCollapseIcon={<ExpandMoreSharp />}
            defaultExpandIcon={<ChevronRight />}
            sx={{maxWidth: 200, padding: 0}}
          >
            <StyledTreeItem nodeId={"1"} labelText={"Configuration"}>
              <StyledTreeItem
                nodeId={"2"} labelText={"IP"}
                labelInfo={agentObject.ip}
              />
              <StyledTreeItem
                nodeId={"3"} labelText={"MODE"}
                labelInfo={agentObject.mode}
              />
              <StyledTreeItem
                nodeId={"4"} labelText={"VEHICLE"}
                labelInfo={agentObject.vehicle}
              />
            </StyledTreeItem>

            <StyledTreeItem nodeId={"5"} labelText="Status">
              <StyledTreeItem
                nodeId={"6"}
                labelText={"NED Coordinate"}
              >
                <StyledTreeItem
                  nodeId={"7"} labelText={"X"}
                  labelInfo={agentObject.ned.x}
                />
                <StyledTreeItem
                  nodeId={"8"} labelText={"Y"}
                  labelInfo={agentObject.ned.y}
                />
                <StyledTreeItem
                  nodeId={"9"} labelText={"Z"}
                  labelInfo={agentObject.ned.z}
                />
              </StyledTreeItem>

              {/*<StyledTreeItem*/}
              {/*  nodeId={10}*/}
              {/*  labelText={"LLH Coordinate"}*/}
              {/*>*/}
              {/*  <StyledTreeItem*/}
              {/*    nodeId={11} labelText={"Latitude"}*/}
              {/*    labelInfo={agentObject.llh.lat}*/}
              {/*  />*/}
              {/*  <StyledTreeItem*/}
              {/*    nodeId={12} labelText={"Longitude"}*/}
              {/*    labelInfo={agentObject.llh.lng}*/}
              {/*  />*/}
              {/*  <StyledTreeItem*/}
              {/*    nodeId={13} labelText={"Altitude"}*/}
              {/*    labelInfo={agentObject.llh.alt}*/}
              {/*  />*/}
              {/*</StyledTreeItem>*/}

              <StyledTreeItem
                nodeId={"14"}
                labelText={"Velocity"}
              >
                <StyledTreeItem
                  nodeId={"15"} labelText={"VX"}
                  labelInfo={agentObject.velocity.vx}
                />
                <StyledTreeItem
                  nodeId={"16"} labelText={"VY"}
                  labelInfo={agentObject.velocity.vy}
                />
                <StyledTreeItem
                  nodeId={"17"} labelText={"VZ"}
                  labelInfo={agentObject.velocity.vz}
                />
              </StyledTreeItem>

              <StyledTreeItem
                nodeId={"18"}
                labelText={"Angle"}
              >
                <StyledTreeItem
                  nodeId={"19"} labelText={"ROLL"}
                  labelInfo={agentObject.angle.roll}
                />
                <StyledTreeItem
                  nodeId={"20"} labelText={"PITCH"}
                  labelInfo={agentObject.angle.pitch}
                />
                <StyledTreeItem
                  nodeId={"21"} labelText={"YAW"}
                  labelInfo={agentObject.angle.yaw}
                />
              </StyledTreeItem>
            </StyledTreeItem>
          </TreeView>
        </AgentBodyContainer>
      )}
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

const AgentInfoContainer = React.memo(styled.div`
  background: #2e3c43;
  color: ${props => props.active ? Colors.textPrimary: Colors.textQuaternary};
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 0.3em;
  border: 1px solid ${props => props.active ? props.color: Colors.backgroundDisabled};
  margin: 0.8em;
  padding: 0.5em 1em;
  background-color: ${props => props.active ? 
          Colors.backgroundPointed : Colors.backgroundDisabled};
  font-size: ${FontSize.small}
  
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
  
`)

const AgentHeadName = React.memo(styled.p`
  margin: 0;
  text-align: left;
`)

const AgentHeadBatteryContainer = React.memo(styled.div`
  
`)


export default React.memo(AgentInfo);