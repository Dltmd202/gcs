import {useSelector} from "react-redux";
import agentApi from "../../../api/agent";
import React, {useEffect, useRef, useState} from "react";
import styled from "styled-components";
import Modal, {
  ModalBody,
  ModalContainer,
  ModalHeader,
  ModalHeaderName, ModalInput,
  ModalInputContainer, ModalInputLabel, ModalInputPair
} from "../../atoms/modal/Modal";
import paramApi from "../../../api/param";

const GcsCalibrationModal = ({
                               showCalibration,
}) => {
  const {
    loading: contextLoading,
    data: context,
    error: contextError
  } = useSelector((state) => state.context);
  const [agentFilter, setAgentFilter] = useState({"0": {id: "0", name: "all" }});
  const [filterType, setFilterType] = useState("0");
  const [filterLoading, setFilterLoading] = useState(false);

  useEffect(() => {
    setFilterLoading(true);
    Object.values(context.agents)?.map((agent, i) => {
      setAgentFilter(agentFilter => ({
        ...agentFilter,
        [agent.sysid]: {id: agent.sysid, name: agent.sysid}
      }));
    });

    setFilterLoading(false);
  }, [context])

  useEffect(() => {
    if(filterType !== '0' && isNaN(context.agents[filterType]?.param['BAT1_CAPACITY']))
      paramApi.queryDroneShowParamList(filterType);
  }, [filterType])


  const getParamValue = (key) => {
    return context.agents[filterType].param[key];
  }

  return (
    showCalibration &&
      <CalibrationModal>
        <CalibrationModalContainer>
          <ModalHeader>
            <ModalHeaderName>
              Parameter
            </ModalHeaderName>
          </ModalHeader>

          <CalibrationModalBody>
            <ModalStatusTable>
            {paramKeyList.map((param, i) => (
              <ModalStatusTableRow key={i}>
                <ModalStatusTableName>
                  {param.value}
                </ModalStatusTableName>
                <ModalStatusTableValue>
                  <EditableParamValue
                    filterKey={filterType}
                    paramKey={param.value}
                    initText={filterType === '0' ? param.defaultValue : getParamValue(param.value)}
                  />
                </ModalStatusTableValue>
                <ModalStatusTableInfo>
                  {param.info}
                </ModalStatusTableInfo>
              </ModalStatusTableRow>
              ))}
            </ModalStatusTable>
          </CalibrationModalBody>
        </CalibrationModalContainer>

      </CalibrationModal>
  )
}

export default React.memo(GcsCalibrationModal);

const CalibrationModal = React.memo(styled(Modal)`
  width: 100vh;
  height: 75vh;
  top: 6vh;
`)

const AgentSelector = React.memo(styled.select`

`)

const AgentOption = React.memo(styled.option`

`)

const CalibrationModalContainer = React.memo(styled(ModalContainer)`
  height: 100%;
`)

const CalibrationModalHeader = React.memo(styled(ModalHeader)`
  
`)

const CalibrationModalBody = React.memo(styled(ModalBody)`
  height: 90%;
  overflow: scroll;
`)

const ModalStatusTable = React.memo(styled.table`
  width:100%;
`)

const ModalStatusTableRow = React.memo(styled.tr`
  line-height: 1.5;
  width: 100%;
`)

const ModalStatusTableName = React.memo(styled.td`
  width: 30%;
`)

const ModalStatusTableValue = React.memo(styled.td`
  width: 30%;
`)
const ModalStatusTableInfo = React.memo(styled.td`
  font-size: x-small;
  width: 40%;
`)