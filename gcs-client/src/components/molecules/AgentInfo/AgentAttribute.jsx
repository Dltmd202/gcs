import styled from "styled-components";
import React from "react";

const AgentAttribute = ({
                          attribute,
                          depth,
                          children,
                          ...props
}) => {
  return (
    <AgentAttributeContainer {...props}>
      <AgentAttributeCategory>
        {attribute}
      </AgentAttributeCategory>
      <AgentAttributeBody>
        {children}
      </AgentAttributeBody>
    </AgentAttributeContainer>
  )
}

const AgentAttributeContainer = React.memo(styled.div`
  display: flex;
  justify-content: space-between;
`)

const AgentAttributeCategory = React.memo(styled.div`

`)

const AgentAttributeBody = React.memo(styled.div`

`)

export default AgentAttribute;