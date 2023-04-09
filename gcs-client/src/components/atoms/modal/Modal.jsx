import styled from "styled-components";
import {Media} from "../../../styles/media";
import React from "react";
import {FontWeight} from "../../../styles/font";
import HeaderButton from "../Button/HeaderButton";
import Colors from "../../../styles/colors";

export const Modal = React.memo(styled.div`
  position: absolute;
  border-radius: 0.5em;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.7);
  background: rgba(0, 0, 0, 0.7);
  color: white;
  z-index: 5;

  @media ${Media.sm} {
    max-width: 25vh;
  }
`)

export const ModalHeader = React.memo(styled.div`
  display: flex;
  justify-content: space-between;
`)

export const ModalContainer = React.memo(styled.div`
  padding: 1em;
`)

export const ModalHeaderName = React.memo(styled.div`
  font-weight: ${FontWeight.bold};
`)


export const ModalBody = React.memo(styled.div`
  margin-top: 0.5em;
`)

export const ModalButton = React.memo(styled(HeaderButton)`
  padding: 0 1em;
  :hover {
    box-shadow: 0 0 0 3px rgba(0, 0, 0, 0.2);
    border-radius: 3px;
    background: rgba(0, 0, 0, 0.2);
  }
  
  :active{
    color: ${Colors.textTertiary};
  }
`)

export const ModalInputPair = React.memo(styled.div`
  display: flex;
  width: 100%;
  justify-content: space-around;
`)


export const ModalInputContainer = React.memo(styled.div`
  display: flex;
  flex-wrap: wrap;
  transition: height 0.3s ease;
  height: ${props => props.display ? 'auto' : '0px'};
  overflow: hidden;
`)

export const ModalInputLabel = React.memo(styled.label`
  margin-right: 2px;
`)

export const ModalInput = React.memo(styled.input`
  
`)



export default Modal;