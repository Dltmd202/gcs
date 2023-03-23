import styled from "styled-components";
import React from "react";

const Logger = ({
                  time,
                  category,
                  content,
                  ...props
}) => {
  return(
    <LoggerContainer>
      <LoggerTimestamp>

      </LoggerTimestamp>
      <LoggerCategory>
        [{category}]
      </LoggerCategory>
      <LoggerBody>
        {content}
      </LoggerBody>
    </LoggerContainer>
  )
}

const LoggerContainer = React.memo(styled.div`
  position: relative;
  top: -6vh;
  left: 50%;
  width: 50%;
  transform: translate(-50%, 0);
  background: rgba(0, 0, 0, 0.6);
  color: white;
  text-align: center;
  padding: 0.5em 1em;
  display: flex;
  border-radius: 10px;
`)

const LoggerComponent = React.memo(styled.div`
  text-align: center;
`)

const LoggerTimestamp = React.memo(styled(LoggerComponent)`
  
`)

const LoggerCategory = React.memo(styled(LoggerComponent)`
  
`)

const LoggerBody = React.memo(styled(LoggerComponent)`

`)

export default Logger;