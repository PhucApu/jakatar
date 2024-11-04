import { Routes, Route } from 'react-router-dom';

import AdminDashboard from '../pages/admin/AdminDashboard';

function AdminRoute() {
  return (
        <Routes>
          <Route path='/' element={<AdminDashboard />} />
        </Routes>
  );
}

export default AdminRoute;