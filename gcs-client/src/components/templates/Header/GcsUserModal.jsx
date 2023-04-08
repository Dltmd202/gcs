import React from "react";
import styled from "styled-components";
import Modal, {ModalBody, ModalContainer, ModalHeader} from "../../atoms/modal/Modal";
import {useNavigate} from "react-router-dom";
import contextApi from "../../../api/context";
import HeaderButton from "../../atoms/Button/HeaderButton";
import {FontSize} from "../../../styles/font";
import {useSelector} from "react-redux";

const GcsUserModal = ({showUser}) => {
  const {loading, data: user} = useSelector((state) => state.user);
  const navigate = useNavigate();

  const logoutButton = () => {
    localStorage.removeItem('token');
    navigate('/')
  }

  const reloadButton = () => {
    contextApi.cleanContext();
    navigate('/load')
  }

  return (
    showUser &&
    <GcsUserModalContainer>
      <ModalContainer>
        <UserModalHeader>
          <UserModalHeaderName>
            {user.name}
          </UserModalHeaderName>
        </UserModalHeader>
        <UserModalBody>
          <UserButton onClick={logoutButton}>
            <UserButtonContent>
              logout
            </UserButtonContent>
          </UserButton>
          <UserButton onClick={reloadButton}>
            <UserButtonContent>
              reload
            </UserButtonContent>
          </UserButton>
        </UserModalBody>
      </ModalContainer>
    </GcsUserModalContainer>
  )
}


const GcsUserModalContainer = React.memo(styled(Modal)`
  width: 15vh;
  min-height: 5vh;
  top: 6vh;
  right: 3vh;
`)

const UserButton = React.memo(styled(HeaderButton)`
  display: flex;
  align-items: center;
  border-radius: 0.3em;
  height: 2.5em;
  font-size: ${FontSize.small};
  maring-top: 0;
  text-align: right;
  flex-direction: row-reverse;
  
  :hover {
    background: rgba(255, 255, 255, 0.2);
  }
`)

const UserModalBody = React.memo(styled(ModalBody)`
`)


const UserButtonContent = React.memo(styled.div`
  
`)

const UserModalHeaderName = React.memo(styled.div`
  text-align: right;
`)

const UserModalHeader = React.memo(styled(ModalHeader)`
  justify-content: space-between;
  flex-direction: column-reverse;
`)



export default React.memo(GcsUserModal);