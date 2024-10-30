import MenuInfoUser from "../../components/MenuInfoUser";
export default function InforAccount() {
  return (
    <section className="flex justify-center items-center mt-10 p-6">
      <div className="flex w-full max-w-5xl">
        {/* Left Side: Menu */}
        <div className="w-1/4">
          <MenuInfoUser />
        </div>

        {/* Right Side: Main Content */}
        <div className="w-3/4 pl-8 flex-col">
          <div className="flex-col mb-5">
            <h2 className="font-bold text-2xl">Thông tin tài khoản</h2>
            <h3>Quản lý thông tin hồ sơ để bảo mật tài khoản</h3>
          </div>
          <div className="flex justify-center items-center border rounded-2xl">
            <div className="flex flex-col avater w-1/2 justify-between items-center mr-6">
              <img
                src="../avatar_apu.png"
                alt="anh-dai-dien"
                className="w-44 h-44 rounded-full mb-8 -my-20"
              />
              <button className="bg-gray-400 hover:bg-gray-600 rounded-2xl p-2">
                Upload file
              </button>
            </div>
            <div className="flex flex-col information w-1/2 p-4 gap-10 mt-5">
              <div className="flex">
                Tên đăng nhập:
                <input type="username" />
              </div>
              <div className="flex">
                Họ và tên: <input type="name" />
              </div>
              <div className="flex">
                Số điện thoại: <input type="number" />
              </div>
              <div className="flex">
                Email: <input type="email" />
              </div>
              <div>
                <button className="bg-teal-600 text-white hover:bg-teal-800 rounded-2xl p-4 my-4 px-8">
                  Cập nhật
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
