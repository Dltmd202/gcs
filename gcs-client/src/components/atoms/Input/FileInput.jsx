import PropTypes from "prop-types";
import styled from "styled-components";
import {DivButton} from "../Button/Button";

const InputLabel = styled.label`
  width: 100%;
`

const InputFile = styled.input`
  border: 0;
  height: 0;
  width: 0;
`;

export const FileInput = ({
                            children,
                            inputType,
                            inputWidth,
                            inputHeight,
                            inputChange,
                            inputEnter,
                            inputName,
                            inputValue,
                            accept,
                            id,
                            Content
                           }) => {
  return (
    <>
      <InputLabel htmlFor={id}>
        <DivButton
          Content={Content}
          Width='100%'
          Height='60px'>
          {children}
        </DivButton>
      </InputLabel>
      <InputFile
        id={id}
        type={inputType}
        width={inputWidth}
        height={inputHeight}
        onChange={inputChange}
        onKeyUp={inputEnter}
        name={inputName}
        value={inputValue}
        accept={accept}
      />
    </>
  );
};

FileInput.propTypes = {
  inputType: PropTypes.string,
  inputWidth: PropTypes.string,
  inputHeight: PropTypes.string,
  inputPlaceholder: PropTypes.string,
  inputChange: PropTypes.any.isRequired,
  inputEnter: PropTypes.func,
  inputName: PropTypes.string.isRequired,
  inputValue: PropTypes.any,
  accept: PropTypes.string
};

FileInput.defaultProps = {
  inputType: 'file',
  inputWidth: '100%',
  inputHeight: '45px',
  inputPlaceholder: 'None',
  inputValue: '',
  accept: '.conf',
  inputEnter: () => {},
};


export default FileInput;