import styled from "styled-components";
import React from "react";
import Colors from "../../../styles/colors";

const ProgressBar = ({
                       color,
                       progress
}) => {
  return (
    <ProgressContainer>
      <ProgressFiller color={color} progress={progress}>
        <ProgressLabel>
          {`${progress}%`}
        </ProgressLabel>
      </ProgressFiller>
    </ProgressContainer>
  )
}

const ProgressContainer = React.memo(styled.div`
  height: 20px;
  width: 100%;
  background-color: ${Colors.textSecondary};
  border-radius: 1em;
`)

const ProgressFiller = React.memo(styled.div`
  height: 100%;
  width: ${props => props.progress};
  background-color: ${props => props.color};
  border-radius: inherit;
  text-align: right;
`)

const ProgressLabel = React.memo(styled.span`
  padding: 5px;
  color: white;
`)

export default React.memo(ProgressBar);