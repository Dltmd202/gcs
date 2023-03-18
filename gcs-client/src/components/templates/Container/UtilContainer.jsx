import React from 'react';
import styled from "styled-components";
import {Media} from "../../../styles/media";


const UtilContainer = ({
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

UtilContainer.defaultProps = defaultProps;

const AppContainer = styled.div`
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 320px;
  max-width: 768px;
  margin: 0 auto;

  @media ${Media.sm} {
    padding: 0 15px;
  }
  @media ${Media.md} {
    padding: 0 40px;
  }
  @media ${Media.lg} {
    padding: 0 40px;
  }
`;

const ContentContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
  padding-top: 60px;

  @media ${Media.sm} {
    padding-bottom: 64px;
  }
  @media ${Media.md} {
    padding-bottom: 100px;
  }
  @media ${Media.lg} {
    padding-bottom: 100px;
  }
`;

export default React.memo(UtilContainer);
