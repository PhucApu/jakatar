import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path='/' element={<Home />} />
        {/* <Route path='/post/:id' element={<PostDetail />} />
        <Route element={<ProtectedRoute />}>
          <Route path='/setting' element={<Setting />} />
          <Route path='/new-post' element={<NewPost />} />
        </Route>
        <Route path='/user/:id' element={<User />} />
        <Route path='/signin' element={<SignIn />} />
        <Route path='/signup' element={<SignUp />} /> */}
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
