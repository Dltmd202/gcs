import {TreeItem} from "@mui/lab";
import React from "react";
import styled from "styled-components";

const StyledTreeItem = ({
                          bgColor,
                          color,
                          labelIcon: LabelIcon,
                          labelInfo,
                          labelText,
                          ...other
}) => {
  return (
    <TreeItem
      label={
        <AgentAttributeContainer>
          <AgentAttributeName>
            {labelText}
          </AgentAttributeName>
          <AgentAttributeValue>
            {labelInfo}
          </AgentAttributeValue>
        </AgentAttributeContainer>
      }
      style={{
        '--tree-view-color': color,
        '--tree-view-bg-color': bgColor,
      }}
      {...other}
    />
  )
}

const AgentAttributeContainer = styled.div`
  display: flex;
  justify-content: space-between;
`

const AgentAttributeName = styled.div`

`

const AgentAttributeValue = styled.div`

`


export default StyledTreeItem;