import {useSelector} from "react-redux";
import React, {useEffect, useRef, useState} from "react";
import styled from "styled-components";
import Modal, {
  ModalBody,
  ModalContainer,
  ModalHeader,
  ModalHeaderName,
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
  const [loading, setLoading] = useState(false);
  const [currentParam, setCurrentParam] = useState(null);

  useEffect(() => {
    setLoading(true);
    Object.values(context.agents)?.map((agent, i) => {
      setAgentFilter(agentFilter => ({
        ...agentFilter,
        [agent.sysid]: {id: agent.sysid, name: agent.sysid}
      }));
    });

    setLoading(false);
  }, [contextLoading])


  useEffect(() => {
    const handleParameterSet = async () => {
      console.log("fuck")
      const param = await paramApi.getDroneShowParamList(filterType);
      if(param.status === 200){
        console.log(param.data.response);
        setCurrentParam(param.data.response.params);
      }
    }

    setLoading(true);
    if(filterType !== '0'){
      paramApi.queryDroneShowParamList(filterType);
      handleParameterSet();
    }
    setLoading(false);

  }, [filterType])


  const getParamValue = (key) => {
    if(currentParam === null) return "-";
    if(! key in currentParam) return "-";
    return currentParam[key];
  }

  return (
    showScenario &&
      <ParameterModal>
        <ParameterModalContainer>
          <ModalHeader>
            <ModalHeaderName>
              Parameter
            </ModalHeaderName>
            {!loading && (
              <FilterMenu
                value={filterType}
                onChange={setFilterType}
                optionList={agentFilter}
              />
            )}

          </ModalHeader>
          <ParameterModalBody>
            <ModalStatusTable>
            {loading || paramKeyList.map((param, i) => (
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

const ParameterModal = styled(Modal)`
  width: 100vh;
  height: 75vh;
  top: 6vh;
`

const AgentSelector = styled.select`

`

const AgentOption = styled.option`

`

const ParameterModalContainer = styled(ModalContainer)`
  height: 100%;
`

const ParameterModalHeader = styled(ModalHeader)`
  
`

const ParameterModalBody = styled(ModalBody)`
  height: 90%;
  overflow: scroll;
`

const ModalStatusTable = styled.table`
  width:100%;
`

const ModalStatusTableRow = styled.tr`
  line-height: 1.5;
  width: 100%;
`

const ModalStatusTableName = styled.td`
  width: 30%;
`

const ModalStatusTableValue = styled.td`
  width: 30%;
`

const ModalStatusTableInfo = styled.td`
  font-size: x-small;
  width: 40%;
`