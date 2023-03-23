/* eslint-disable no-alert */
/* eslint-disable no-unused-expressions */
/* eslint-disable no-unused-vars */
/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable consistent-return */
import React from 'react';
import styled from 'styled-components';
import { NavLink, useNavigate } from 'react-router-dom';

const Container = styled.div`
  box-sizing: border-box;
  height: 5%;
  display: flex;
  padding: 1%;
  align-items: center;

  .active span {
    font-weight: 500;
  }
`;

const ButtonBox = styled.div`
  position: absolute;
  right: 30px;
  top: 15px;
`;

const Header2 = ({children, ...props}) => {
  const navigate = useNavigate();
  const logout = () => {
    alert('정말 로그아웃 하시겠어요?');
    localStorage.removeItem("token");
    navigate('/');
  };

  return (
    <Container {...props}>
      {children}
    </Container>
  );
};

export default Header2;
