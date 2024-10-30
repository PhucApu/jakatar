import MenuInfoUser from "../../components/MenuInfoUser";
export default function ResetPassWord() {
  return (
    <section className="flex justify-center items-center p-6 mt-10">
      <div className="flex w-full max-w-5xl">
        {/* Left Side: Menu */}
        <div className="w-1/4">
          <MenuInfoUser />
        </div>

        {/* Right Side: Main Content */}
        <div className="w-3/4 pl-8">
          <div className="flex flex-col mb-7 justify-between">
            <h1 className="font-bold">Đặt lại mật khẩu</h1>
            <h2>
              Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người
              khác
            </h2>
          </div>

          <div className="flex flex-col gap-4">
            {/* Filter Options */}
            <div className="flex flex-col justify-center items-center">
              <form className="flex flex-col border rounded-2xl justify-center items-center m-4 p-10">
                <div className="mb-5">
                  <h1>(+84) 9233 244 91</h1>
                </div>
                <div className="flex flex-col mb-5">
                  <label htmlFor="">Nhập mật khẩu cũ</label>
                  <input type="password" />
                </div>
                <div className="flex flex-col mb-5">
                  <label htmlFor="">Mật khẩu mới</label>
                  <input type="password" />
                </div>
                <div className="flex flex-col mb-5">
                  <label htmlFor="">Nhập lại mật khẩu mới</label>
                  <input type="password" />
                </div>
                <div>
                  <button className="bg-teal-600 hover:bg-teal-800 rounded-2xl m-4 p-4 text-white">
                    Xác nhận đổi mật khẩu
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
