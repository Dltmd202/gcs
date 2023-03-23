import styled from "styled-components";
import React from "react";
import Colors from "../../../styles/colors";

const DetailInfo = ({
                      agentObject,
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

  return (
    <AgentDetailInfoContainer color={color}>

    </AgentDetailInfoContainer>
  )
}

const AgentDetailInfoContainer = React.memo(styled.div`
  display: flex;
  padding: 0.5em;
  background: ${props => props.color};
`)