import { Navigate } from 'react-router-dom';

function ProtectedRoute({ children, isAllowed }) {
  if (!isAllowed) {
    // Redirect to a login page or unauthorized page
    return <Navigate to="/dang-nhap" replace />;
  }
  return children;
}

export default ProtectedRoute;
