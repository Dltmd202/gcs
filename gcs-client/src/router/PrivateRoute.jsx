/* eslint-disable react/react-in-jsx-scope */
import { Navigate, Outlet } from 'react-router-dom';
import React from "react";
import Swal from "sweetalert2";

const PrivateRouter = ({
                       restricted = false,
                     }) => {
  const token = localStorage.getItem('token');

  if(!token)
    Swal.fire({
      icon: 'warning',
      text: '잘못된 접근입니다.',
      showConfirmButton: false,
      timer: 1500,
    });

  return !token ? <Navigate to="/" /> : <Outlet />;
};

export default PrivateRouter;
