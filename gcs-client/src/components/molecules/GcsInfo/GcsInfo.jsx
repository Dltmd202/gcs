import styled from "styled-components";
import React from "react";
import GlobalController from "./GlobalController";
import ReactSwipe from 'react-swipe';

const GcsInfo = ({
                   position,
                   onCenterChange,
                   ...props
}) => {
  return (
    <GlobalController name={'ANTENNA-INFORMATION'}>
      <Controller>
        <ParameterContainer>
          <ParameterRow>
            <ParameterCol>latitude</ParameterCol>
            <ParameterCol>
              <ParameterInput
                type={"number"}
                name={"lat"}
                min={0}
                max={180}
                step={0.000001}
                onChange={onCenterChange}
                value={position.lat}
              />
            </ParameterCol>
          </ParameterRow>
          <ParameterRow>
            <ParameterCol>longitude</ParameterCol>
            <ParameterCol>
              <ParameterInput
                type={"number"}
                min={0}
                max={180}
                step={0.000001}
                onChange={onCenterChange}
                name={"lng"}
                value={position.lng}
              />
            </ParameterCol>
          </ParameterRow>
          <ParameterRow>
            <ParameterCol>altitude</ParameterCol>
            <ParameterCol>
              <ParameterInput
                type={"number"}
                min={0}
                max={180}
                step={0.000001}
                onChange={onCenterChange}
                name={"alt"}
                value={position.alt}
              />
            </ParameterCol>
          </ParameterRow>
        </ParameterContainer>
        <ExecuteContainer>

        </ExecuteContainer>
      </Controller>
    </GlobalController>
  )
}

const Controller = React.memo(styled.div`
  display: flex;
  display: -webkit-box;
  display: -ms-flexbox;
  overflow-x: auto;
  overflow-y: hidden;
  
  padding: 0.3em;
  width: 100%;
`)

const ParameterContainer = React.memo(styled.div`
  width: 100%;
`)

const ParameterRow = React.memo(styled.div`
  display: flex;
  justify-content: space-between;
  padding: 0.25em 1em;
`)


const ParameterCol = React.memo(styled.div`
  display: block;
  padding: 0.2em;
`)

const ParameterInput = React.memo(styled.input`
  
`)

const ExecuteContainer = React.memo(styled.div`
  width: 100%;
`)


const gcsEqual = (prev, next) => {
  if(prev.position.lat !== next.position.lat)
    return false;
  if(prev.position.lng !== next.position.lng)
    return false;
  if(prev.position.alt !== next.position.alt)
    return false;
  return true;
}


export default React.memo(GcsInfo, gcsEqual);