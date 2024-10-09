import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Booking from './pages/Booking';
import AllRoute from './pages/AllRoute';
import Contact from './pages/Contact';
import Ticket from './pages/Ticket';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <div className='bg-gray-100 min-h-screen'>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/dat-ve' element={<Booking />} />
          <Route path='/dang-nhap' element={<Login />} />
          <Route path='/dang-ky' element={<SignUp />} />
          <Route path='/lich-trinh' element={<AllRoute />} />
          <Route path='/tra-cuu' element={<Ticket />} />
          <Route path='/lien-he' element={<Contact />} />
          {/* <Route path='/post/:id' element={<PostDetail />} />
        <Route element={<ProtectedRoute />}>
          <Route path='/setting' element={<Setting />} />
          <Route path='/new-post' element={<NewPost />} />
        </Route>
        <Route path='/user/:id' element={<User />} />
        <Route path='/signin' element={<SignIn />} />
        <Route path='/signup' element={<SignUp />} /> */}
        </Routes>
      </div>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
