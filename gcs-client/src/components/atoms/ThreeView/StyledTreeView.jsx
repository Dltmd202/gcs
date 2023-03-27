import {TreeView} from "@mui/lab";
import {ChevronRight, ExpandMoreSharp} from "@mui/icons-material";
import React from "react";
import styled from "styled-components";
import {FontSize} from "../../../styles/font";

const StyledTreeView = ({
                          label,
                          children,
                          ...props
}) => {
  return (
    <TreeView
      aria-label={
        <CategoryContainer>
          {label}
        </CategoryContainer>
      }
      defaultCollapseIcon={<ExpandMoreSharp />}
      defaultExpandIcon={<ChevronRight />}
      sx={{maxWidth: 200, padding: 0, "font-size": FontSize.micro}}
    >
      {children}
    </TreeView>
  )
}

const CategoryContainer = React.memo(styled.div`
  font-size: ${FontSize.micro};
`)


export default StyledTreeView;