import styled from "styled-components";
import {Media} from "../../../styles/media";

const StyledSidebarContainer = styled.div`
  float: left;
  text-align: center;
  box-sizing: border-box;
  width: 20%;
  height: 100%;
  min-width: 300px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  resize: horizontal;

  @media ${Media.sm} {
    width: 32px;
  }
  @media ${Media.md} {
    width: 56px;
  }
  @media ${Media.lg} {
    width: 56px;
  }
`;


const Sidebar = ({children, ...props}) => {

  return (
    <StyledSidebarContainer {...props}>
      {children}
    </StyledSidebarContainer>
  );
};

export default Sidebar;
