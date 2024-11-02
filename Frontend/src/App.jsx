import { BrowserRouter, Routes, Route } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Home from "./pages/Home";
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import Booking from "./pages/Booking";
import AllRoute from "./pages/AllRoute";
import Contact from "./pages/Contact";
import Ticket from "./pages/Ticket";
import FeedBack from "./pages/FeedBack";
import Checkout from "./pages/Checkout";
import Aboutus from "./pages/Aboutus";
import AnhBaPay from "./pages/InforUser/AnhBaPay";
import InforAccount from "./pages/InforUser/InforAccount";
import HictoryByTicket from "./pages/InforUser/HictoryByTicket";
import ResetPassWord from "./pages/InforUser/ResetPassWord";
import AddressOfAccount from "./pages/InforUser/AddressOfAccount";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <div className="bg-gray-100 min-h-screen">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/chon-ve" element={<Booking />} />
          <Route path="/dat-ve" element={<Checkout />} />
          <Route path="/dang-nhap" element={<Login />} />
          <Route path="/dang-ky" element={<SignUp />} />
          <Route path="/lich-trinh" element={<AllRoute />} />
          <Route path="/tra-cuu" element={<Ticket />} />
          <Route path="/danh-gia-gop-y" element={<FeedBack />} />
          <Route path="/lien-he" element={<Contact />} />
          <Route path="/ve-chung-toi" element={<Aboutus />} />
          <Route path="/anhba-pay" element={<AnhBaPay />} />
          <Route path="/thong-tin-chung" element={<InforAccount />} />
          <Route path="/lich-su-mua-ve" element={<HictoryByTicket />} />
          <Route path="/dat-lai-mat-khau" element={<ResetPassWord />} />
          <Route path="/dia-chi" element={<AddressOfAccount />} />
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
