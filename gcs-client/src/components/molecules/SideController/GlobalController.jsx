import styled from "styled-components";
import React from "react";
import Colors from "../../../styles/colors";

const GlobalController = ({
                            name,
                            children,
                            ...props
}) => {
  return (
    <GlobalControllerContainer
      {...props}
    >
      <GlobalControllerHead color={Colors.black}>
        <GcsHeadName>{name}</GcsHeadName>
      </GlobalControllerHead>
      <GlobalControllerBody>
        {children}
      </GlobalControllerBody>
    </GlobalControllerContainer>
  )
}

const GlobalControllerHead = React.memo(styled.div`
  display: flex;
  padding: 0.5em;
  background: ${props => props.color};
`)

const GcsHeadName = React.memo(styled.p`
  margin: 0;
  text-align: left;
  color: white;
`)

const GlobalControllerContainer = React.memo(styled.div`
  height: 30%;
`)

const GlobalControllerBody = React.memo(styled.table`
  display: flex;
  display: -webkit-box;
  display: -ms-flexbox;
  overflow-x: auto;
  overflow-y: hidden;
  
  padding: 0.3em;
`)




export default React.memo(GlobalController);