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

const FilterMenu = React.memo(({value, onChange, optionList}) => {
  return (
    <AgentSelector
      value={value}
      onChange={(e) => onChange(e.target.value)}
    >
      {Object.values(optionList).map((op, idx) => (
        <AgentOption key={idx} value={op.id}>
          {op.name}
        </AgentOption>
      ))}
    </AgentSelector>
  )
})

const EditableParamValue = ({
                              filterKey,
                              paramKey,
                              initText
}) => {
  const [text, setText] = useState(initText);
  const inputRef = useRef(null);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    if (isEditing) {
      inputRef.current.focus();
    }
  }, [isEditing]);

  useEffect(() => {
    setText(initText);
  }, [initText])

  const handleTextClick = () => {
    setIsEditing(true);
  };

  const handleBlur = () => {
    setIsEditing(false)
    if(filterKey === '0') paramApi.globalSetParam(paramKey, text);
    else {
      console.log(filterKey)
      console.log(paramKey)
      console.log(text)
      paramApi.setParam(filterKey, paramKey, text);
    }
  };

  const handleChange = (e) => {
    setText(e.target.value);
  };

  return (
    <div onClick={handleTextClick}>
      {isEditing ? (
        <input
          type="text"
          ref={inputRef}
          value={text}
          onChange={handleChange}
          onBlur={handleBlur}
        />
      ) : (
        <span>{text}</span>
      )}
    </div>
  );
}

const GcsParameterModal = ({
                             showScenario,
                             paramKeyList
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
      paramApi.droneShowParamList(filterType);
  }, [filterType])


  const getParamValue = (key) => {
    const val = context.agents[filterType].param[key];
    return !isNaN(val) ? val : "-";
  }

  return (
    showScenario &&
      <ParameterModal>
        <ParameterModalContainer>
          <ModalHeader>
            <ModalHeaderName>
              Parameter
            </ModalHeaderName>
            {!filterLoading && (
              <FilterMenu
                value={filterType}
                onChange={setFilterType}
                optionList={agentFilter}
              />
            )}

          </ModalHeader>
          <ParameterModalBody>
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
          </ParameterModalBody>
        </ParameterModalContainer>

      </ParameterModal>
  )
}

export default React.memo(GcsParameterModal);

const ParameterModal = React.memo(styled(Modal)`
  width: 100vh;
  height: 75vh;
  top: 6vh;
`)

const AgentSelector = React.memo(styled.select`

`)

const AgentOption = React.memo(styled.option`

`)

const ParameterModalContainer = React.memo(styled(ModalContainer)`
  height: 100%;
`)

const ParameterModalHeader = React.memo(styled(ModalHeader)`
  
`)

const ParameterModalBody = React.memo(styled(ModalBody)`
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