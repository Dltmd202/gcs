import styled from "styled-components";
import Colors from "../../../styles/colors";
import PropTypes from "prop-types";
import {Media} from "../../../styles/media";
import {FontSize} from "../../../styles/font";

const Container = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 20px;
`;


export const Input = styled.input`
  display: block;
  padding: 0 16px;
  width: 100%;
  color: ${Colors.textPrimary};
  border: 2px solid ${Colors.textTertiary};
  border-radius: 8px;
  background-color: transparent;

  @media ${Media.sm} {
    height: 46px;
    font-size: ${FontSize.medium};
  }
  @media ${Media.md} {
    height: 56px;
    font-size: ${FontSize.large};
  }
  @media ${Media.lg} {
    height: 56px;
    font-size: ${FontSize.large};
  }

  &:focus {
    border: 2px solid ${Colors.textQuaternary};
    outline: 2px solid ${Colors.point};
  }
`;


const InputName = styled.span`
  font-weight: 700;
  font-size: 1.2rem;
  display: inline-block;
  width: 140px;
`;

const Inputs = ({
                  inputType,
                  inputWidth,
                  inputHeight,
                  inputContent,
                  inputPlaceholder,
                  inputChange,
                  inputName,
                  inputValue,
                }) => {
  return (
    <>
      <Container>
        <InputName>{inputContent}</InputName>
        <Input
          name={inputName}
          type={inputType}
          width={inputWidth}
          height={inputHeight}
          placeholder={inputPlaceholder}
          onChange={inputChange}
          value={inputValue}
        />
      </Container>
    </>
  );
};

Inputs.propTypes = {
  inputName: PropTypes.string.isRequired,
  inputType: PropTypes.string,
  inputWidth: PropTypes.string,
  inputHeight: PropTypes.string,
  inputContent: PropTypes.string,
  inputPlaceholder: PropTypes.string,
  inputChange: PropTypes.func.isRequired,
  inputValue: PropTypes.any,
};

Inputs.defaultProps = {
  inputType: 'text',
  inputWidth: '450px',
  inputHeight: '45px',
  inputContent: 'None',
  inputPlaceholder: 'None',
  inputValue: '',
};

export default Inputs;

const InputNumber = styled.input`
  font-family: 'Pretendard Variable';
  font-size: inherit;
  box-sizing: border-box;
  padding: 8px;
  margin-right: 10px;
  border: 1.5px solid ${Colors.black};
  width: 70px;
  height: 45px;
  &::placeholder {
    font-family: 'Pretendard Variable';
    font-weight: 300;
  }
`;

const InputText = styled.span`
  font-size: 1.2rem;
  font-weight: 400;
  width: 30px;
`;
export const InputNum = ({
                           inputContent,
                           inputChange,
                           inputName,
                           inputValue,
                         }) => {
  return (
    <Container>
      <InputName>{inputContent}</InputName>
      <InputNumber
        onChange={inputChange}
        name={inputName}
        value={inputValue}
        type="number"
        min="0"
        max="2"
      />
      <InputText>차</InputText>
    </Container>
  );
};

InputNum.propTypes = {
  inputContent: PropTypes.string,
  inputChange: PropTypes.func.isRequired,
  inputName: PropTypes.string.isRequired,
  inputValue: PropTypes.number.isRequired,
};

InputNum.defaultProps = {
  inputContent: 'None',
};
