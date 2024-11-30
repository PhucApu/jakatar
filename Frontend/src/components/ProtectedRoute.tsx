import React from 'react';
import { Navigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/store';

interface PrivateRouteProps {
  children: React.ReactNode;
}

const ProtectedRoute : React.FC<PrivateRouteProps> = ({ children }) => {
  const user = useSelector((state: RootState) => state.user);

  if(!user) {
    return <Navigate to="/dang-nhap" replace />;
  }

  return children;
}

export default ProtectedRoute;
