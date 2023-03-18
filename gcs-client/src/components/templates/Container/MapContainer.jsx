import React from 'react';
import styled from "styled-components";
const ApplicationContainer = ({
  children,
  ...props
}) => {
  return (
    <AppContainer>
      <ContentContainer {...props}>{children}</ContentContainer>
    </AppContainer>
  );
};

const defaultProps = {
  navBar: false,
};

ApplicationContainer.defaultProps = defaultProps;

const AppContainer = styled.div`
  height: 100%;
  width: 100%;
`;

const ContentContainer = styled.div`
  width: 100%;
  height: 100%;
`;

export default React.memo(ApplicationContainer);
