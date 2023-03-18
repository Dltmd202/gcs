/* eslint-disable react/react-in-jsx-scope */
import { Navigate, Outlet } from 'react-router-dom';
import Swal from "sweetalert2";
import React from "react";

const PublicRoute = ({
                       restricted = false,
}) => {
  const user = localStorage.getItem('token');

  user &&
    restricted &&
  Swal.fire({
    icon: 'warning',
    text: '잘못된 접근입니다.',
    showConfirmButton: false,
    timer: 1500,
  });

  return user && restricted ? <Navigate to="/load" /> : <Outlet />;
};

export default PublicRoute;
