import styled from "styled-components";
import React from "react";


const AgentAttributeLeaf = ({
                              category,
                              content,
                              ...props
}) => {
  return (
    <AgentAttributeLeafContainer {...props}>
      <AgentAttributeLeafCategory>
        {category}
      </AgentAttributeLeafCategory>
      <AgentAttributeLeafContent>
        {content}
      </AgentAttributeLeafContent>
    </AgentAttributeLeafContainer>
  )
}

const AgentAttributeLeafContainer = React.memo(styled.div`

`)

const AgentAttributeLeafCategory = React.memo(styled.div`

`)

const AgentAttributeLeafContent = React.memo(styled.div`

`)

export default AgentAttributeLeaf;