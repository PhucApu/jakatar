import { Button, MegaMenu, Navbar } from "flowbite-react";
import { useLocation } from "react-router-dom";

export default function Header() {
  const location = useLocation();

  return (
    <MegaMenu className="bg-gray-100">
      <div className="mx-auto flex w-full flex-wrap items-center justify-between p-4 md:space-x-8">
        <Navbar.Brand href="/">
          <img alt="" src="/favicon.svg" className="mr-3 h-6 sm:h-9" />
          <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">
            AnhBa Bus
          </span>
        </Navbar.Brand>
        <div className="order-2 hidden items-center md:flex">
          <a
            href="/dang-nhap"
            className="mr-1 rounded-lg px-4 py-2 text-sm font-medium text-gray-800 hover:bg-gray-50 focus:outline-none focus:ring-4 focus:ring-gray-300 dark:text-white dark:hover:bg-gray-700 dark:focus:ring-gray-800 md:mr-2 md:px-5 md:py-2.5"
          >
            Đăng nhập
          </a>
          <Button href="/dang-ky">Đăng ký</Button>
        </div>
        <Navbar.Toggle />
        <Navbar.Collapse>
          <Navbar.Link
            href="/"
            className={`${
              location.pathname === "/"
                ? "text-teal-600 overline underline-offset-8 decoration-2"
                : ""
            }`}
          >
            Trang chủ
          </Navbar.Link>
          <Navbar.Link
            href="/lich-trinh"
            className={`${
              location.pathname === "/lich-trinh"
                ? "text-teal-600 overline underline-offset-8 decoration-2"
                : ""
            }`}
          >
            Lịch trình
          </Navbar.Link>
          <Navbar.Link
            href="/tra-cuu"
            className={`${
              location.pathname === "/tra-cuu"
                ? "text-teal-600 overline underline-offset-8 decoration-2"
                : ""
            }`}
          >
            Tra cứu vé
          </Navbar.Link>
          <Navbar.Link
            href="/lien-he"
            className={`${
              location.pathname === "/lien-he"
                ? "text-teal-600 overline underline-offset-8 decoration-2"
                : ""
            }`}
          >
            Liên hệ
          </Navbar.Link>
          <Navbar.Link
            href="/ve-chung-toi"
            className={`${
              location.pathname === "/ve-chung-toi"
                ? "text-teal-600 overline underline-offset-8 decoration-2"
                : ""
            }`}
          >
            Về chúng tôi
          </Navbar.Link>
        </Navbar.Collapse>
      </div>
    </MegaMenu>
  );
}
