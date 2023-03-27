import {TreeItem} from "@mui/lab";
import React from "react";
import styled from "styled-components";
import {FontSize} from "../../../styles/font";

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
        <StyledContainer>
          <AttributeName>
            {labelText}
          </AttributeName>
          <AttributeValue>
            {labelInfo}
          </AttributeValue>
        </StyledContainer>
      }
      style={{
        '--tree-view-color': color,
        '--tree-view-bg-color': bgColor,
      }}
      {...other}
    />
  )
}

const StyledContainer = React.memo(styled.div`
  display: flex;
  justify-content: space-between;
  font-size: ${FontSize.small};
`)

const AttributeName = React.memo(styled.div`

`)

const AttributeValue = React.memo(styled.div`

`)


export default StyledTreeItem;