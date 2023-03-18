/* eslint-disable react/react-in-jsx-scope */
import { Navigate, Outlet } from 'react-router-dom';
import React from "react";
import Swal from "sweetalert2";

const ProtectRoute = ({
                       restricted = false,
                     }) => {
  const role = localStorage.getItem('role');
  const token = localStorage.getItem('token');

  if(!token || role === 'USER'){
    Swal.fire({
      icon: 'warning',
      text: '잘못된 접근입니다.',
      showConfirmButton: false,
      timer: 1500,
    });
    localStorage.clear();
    return <Navigate to={"/gcs/three"}/>
  }

  return <Outlet />;
};

export default ProtectRoute;
