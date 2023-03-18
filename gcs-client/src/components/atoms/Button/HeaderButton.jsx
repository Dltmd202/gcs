import styled from "styled-components";
import {FontSize} from "../../../styles/font";

const HeaderButton = styled.div`
  display: flex;
  align-items: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};
  
  :hover {
    background: rgba(255, 255, 255, 0.2);
  }
`

export default HeaderButton;