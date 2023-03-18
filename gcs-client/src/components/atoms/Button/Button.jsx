import React from 'react';
import styled from 'styled-components';
import Colors from "../../../styles/colors";
import {FontSize, FontWeight} from "../../../styles/font";
import {Media} from "../../../styles/media";

const StyledButton = styled.button`
  width: 100%;
  ${({ colorType }) => `
    background-color: ${
  colorType === 'blue' ? Colors.point : Colors.backgroundButton
};
    color: ${colorType === 'blue' ? Colors.textQuaternary : Colors.textPrimary};
  `}
  border-radius: 16px;
  border: none;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
  font-weight: ${FontWeight.bold};
  cursor: pointer;
  @media (hover: hover) {
    :hover {
      color: ${Colors.textPrimary};
      background-color: ${Colors.pointLight};
    }
  }
  :active {
    ${({ colorType }) => `
      color: ${
  colorType === 'blue' ? Colors.textPrimary : Colors.textQuaternary
};
    background-color: ${
  colorType === 'blue' ? Colors.backgroundButton : Colors.point
};`}
  }
  @media ${Media.sm} {
    min-height: 48px;
    font-size: ${FontSize.small};
  }
  @media ${Media.md} {
    min-height: 56px;
    font-size: ${FontSize.medium};
  }
  @media ${Media.lg} {
    min-height: 35px;
    font-size: ${FontSize.medium};
  }
`;

const DivBtn = styled.div`
  width: 100%;
  ${({ colorType }) => `
    background-color: ${
          colorType === 'blue' ? Colors.point : Colors.backgroundButton
  };
    color: ${colorType === 'blue' ? Colors.textQuaternary : Colors.textPrimary};
  `}
  border-radius: 16px;
  border: none;
  box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
  font-weight: ${FontWeight.bold};
  cursor: pointer;
  align-items: center;
  display: flex;
  justify-content: center;
  @media (hover: hover) {
    :hover {
      color: ${Colors.textPrimary};
      background-color: ${Colors.pointLight};
    }
  }
  :active {
    ${({ colorType }) => `
      color: ${
            colorType === 'blue' ? Colors.textPrimary : Colors.textQuaternary
    };
    background-color: ${
            colorType === 'blue' ? Colors.backgroundButton : Colors.point
    };`}
  }
  @media ${Media.sm} {

    font-size: ${FontSize.small};
  }
  @media ${Media.md} {
    height: 56px;
    font-size: ${FontSize.medium};
  }
  @media ${Media.lg} {
    height: 56px;
    font-size: ${FontSize.medium};
  }
`;

const Button = ({children, colorType, ...props}) => {
  return (
    <StyledButton
      colorType={colorType}
      {...props}
    >
      {children}
    </StyledButton>
  );
};

export const DivButton = ({children, colorType, ...props}) => {
  return (
    <DivBtn colorType={colorType} {...props}>
      {children}
    </DivBtn>
  );
};

export default Button;