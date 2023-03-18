import styled from "styled-components";
import Button from "../../atoms/Button/Button";

const ContextSelectorItem = ({
  contextFileObject,
  ...props
}) => {
  const { contextId, filename, uploadedAt } = contextFileObject;

  return (
    <Button {...props}>
      <StyledContextFileContainer>
          <p>{filename}</p>
          <p>{uploadedAt}</p>
      </StyledContextFileContainer>
    </Button>
  )
}

const StyledContextFileContainer = styled.div`
  
`;


export default ContextSelectorItem;