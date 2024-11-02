// import Paper from "@mui/material/Paper";
// import MenuItem from "@mui/material/MenuItem";
// import MenuList from "@mui/material/MenuList";
// import Stack from "@mui/material/Stack";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faCreditCard,
  faUser,
  faClockRotateLeft,
  faMapLocation,
  faKey,
  faRightFromBracket,
} from "@fortawesome/free-solid-svg-icons";
export default function MenuInfoUser() {
  return (
    // <Stack direction="row" spacing={2}>
    //   <Paper>
    //     <MenuList>
    //       <MenuItem>AnhBa Pay</MenuItem>
    //       <MenuItem>Thông tin tài khoản</MenuItem>
    //       <MenuItem>Lịch sử mua vé</MenuItem>
    //       <MenuItem>Địa chỉ của bạn</MenuItem>
    //       <MenuItem>Đặt lại mật khẩu</MenuItem>
    //       <MenuItem>Đăng xuất</MenuItem>
    //     </MenuList>
    //   </Paper>
    // </Stack>
    <div className="col-span-12 hidden rounded-2xl border sm:col-span-3 lg:block h-full p-6 justify-between items-center">
      <div className="py-1 scale-125 flex flex-col gap-8 mt-10">
        <a
          href="/anhba-pay"
          className="flex items-center p-2 hover:bg-gray-100"
        >
          <FontAwesomeIcon
            icon={faCreditCard}
            style={{ color: "#186477" }}
            className="mr-2"
          />
          AnhBa Pay
        </a>
        <a
          href="/thong-tin-chung"
          className="flex items-center p-2 hover:bg-gray-100"
        >
          <FontAwesomeIcon
            icon={faUser}
            style={{ color: "#4d5b66" }}
            className="mr-2"
          />
          Thông tin tài khoản
        </a>
        <a
          href="/lich-su-mua-ve"
          className="flex items-center p-2 hover:bg-gray-100"
        >
          <FontAwesomeIcon
            icon={faClockRotateLeft}
            style={{ color: "#74C0FC" }}
            className="mr-2"
          />
          Lịch sử mua vé
        </a>
        <a href="/dia-chi" className="flex items-center p-2 hover:bg-gray-100">
          <FontAwesomeIcon
            icon={faMapLocation}
            style={{ color: "#63E6BE" }}
            className="mr-2"
          />
          Địa chỉ của bạn
        </a>
        <a
          href="/dat-lai-mat-khau"
          className="flex items-center p-2 hover:bg-gray-100"
        >
          <FontAwesomeIcon
            icon={faKey}
            style={{ color: "#FFA726" }}
            className="mr-2"
          />
          Đặt lại mật khẩu
        </a>
        <a href="/" className="flex items-center p-2 hover:bg-gray-100 mb-7">
          <FontAwesomeIcon
            icon={faRightFromBracket}
            style={{ color: "#fa003e" }}
            className="mr-2"
          />
          Đăng xuất
        </a>
      </div>
    </div>
  );
}
