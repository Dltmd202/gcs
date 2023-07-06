import styled from "styled-components";
import React, {useEffect, useState} from "react";
import Colors from "../../../styles/colors";
import Button from "../../atoms/Button/Button";
import {FontSize, FontWeight} from "../../../styles/font";
import {Media} from "../../../styles/media";
import {TreeView} from "@mui/lab";
import {ExpandMoreSharp} from "@mui/icons-material";
import {ChevronRight} from "@mui/icons-material";
import StyledTreeItem from "../../atoms/ThreeView/StyledTreeItem";
import {agentStatusMask} from "../../../module/coordinate/agentStatus";

const areEqual = (prev, next) => {
  if (prev.agentObject.status !== next.agentObject.status) return false;
  if (prev.focused !== next.focused) return false;
  if (prev.agentObject.battery !== next.agentObject.battery) return false;
  return true;
}

const AgentInfo = ({
                     agentObject,
                     focus,
                     focused,
                     style,
                     setFlyable,
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
    status,
  } = agentObject;

  const [drop, isDrop] = useState(true);
  const [containerColor, setContainerColor] = useState(Colors.textPrimary);
  const [statusColor, setStatusColor] = useState(Colors.textPrimary);

  useEffect(() => {
    setContainerColor("#2e3c43");
    if((status & agentStatusMask[9].mask) === 0) {
      setStatusColor(Colors.red);
      setContainerColor(Colors.red);
    }
    else if((status & agentStatusMask[26].mask) !== 0){
      setStatusColor(Colors.orange);
      setContainerColor(Colors.orange);
    }
    else if((status & agentStatusMask[25].mask) !== 0) {
      setStatusColor(Colors.warning);
      setContainerColor(Colors.warning);
    }
    else {
      setStatusColor(Colors.textPrimary);
    }
    if(focused){
      setStatusColor("#000000");
      setContainerColor("#ffffff")
    }
  }, [status, focused])


  const handleFocus = () => {
    if(active)
      focus(sysid);
  }


  return (
    <AgentInfoContainer
      active={active}
      color={statusColor}
      containerColor={containerColor}
      focused={focused}
      textColor={statusColor}
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
  color: ${props => props.textColor};
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
  border-radius: 0.3em;
  border: 1px solid ${props => props.active ? props.color: Colors.backgroundDisabled};
  margin: 0.8em;
  padding: 0.5em 1em;
  background-color: ${props => props.focused ? 
          "#ffffff" : Colors.backgroundPointed};
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


export default React.memo(AgentInfo, areEqual);