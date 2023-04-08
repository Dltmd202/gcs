import styled from "styled-components";
import React from "react";
import Colors from "../../../styles/colors";

const Logger = ({
                  time,
                  category,
                  content,
                  children,
                  ...props
}) => {
  return(
    <LoggerContainer {...props}>
      <Log>
        <LoggerCategory>
          [{category}]
        </LoggerCategory>
        <LoggerBody>
          {content}
        </LoggerBody>
      </Log>
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}
      {/*<Log>*/}
      {/*  <LoggerCategory>*/}
      {/*    [{category}]*/}
      {/*  </LoggerCategory>*/}
      {/*  <LoggerBody>*/}
      {/*    {content}*/}
      {/*  </LoggerBody>*/}
      {/*</Log>*/}

    </LoggerContainer>
  )
}

const LoggerContainer = React.memo(styled.div`
  position: relative;
  top: -5vh;
  left: 50%;
  width: 70%;
  height: 20%;
  overflow: scroll;
  transform: translate(-50%, -100%);
  background: rgba(0, 0, 0, 0.6);
  color: white;
  text-align: center;
  padding: 1em 1.5em;
  border-radius: 10px;
`)

const LoggerComponent = React.memo(styled.div`
  text-align: center;
`)

const Log = React.memo(styled.div`
  padding: 2px 0;
  margin: 0 1px;
  display: flex;
  width: 100%;
`)

const LoggerTimestamp = React.memo(styled(LoggerComponent)`
  
`)

export const LoggerCategory = React.memo(styled(LoggerComponent)`
`)

export const LoggerBody = React.memo(styled(LoggerComponent)`

`)

export default React.memo(Logger);