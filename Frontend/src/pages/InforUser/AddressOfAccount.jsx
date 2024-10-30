import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPen, faTrashCan } from "@fortawesome/free-solid-svg-icons";
import MenuInfoUser from "../../components/MenuInfoUser";
export default function AddressOfAccount() {
  return (
    <section className="flex justify-center items-center p-6 mt-10">
      <div className="flex w-full max-w-5xl">
        {/* Left Side: Menu */}
        <div className="w-1/4">
          <MenuInfoUser />
        </div>

        {/* Right Side: Main Content */}
        <div className="w-3/4 pl-8">
          <div className="flex bg-teal-100 p-4 rounded-2xl mb-7 justify-between">
            <div className="flex flex-col">
              <h1 className="font-bold">Địa chỉ của bạn</h1>
              <h2>
                Địa chỉ của bạn sẽ được sử dụng để nhập nhanh điểm đón - trả tận
                nơi
              </h2>
            </div>
            <div className="bg-teal-600 hover:bg-teal-800 rounded-2xl">
              <button
                className="m-4 text-white"
                // onClick={() => navigate("/")}
              >
                Thêm địa chỉ mới
              </button>
            </div>
          </div>
          <div className="">
            {/* address1 */}
            <div className="flex bg-teal-100 p-4 rounded-2xl mb-7 justify-between">
              <div className="flex flex-col m-4">
                <h1 className="font-bold">TP HCM</h1>
                <h2>Loại địa chỉ</h2>
                <h2>Địa chỉ</h2>
              </div>
              <div className="flex ">
                <div className=" bg-teal-600 hover:bg-teal-800 rounded-2xl  ">
                  <a href="/" className="">
                    <FontAwesomeIcon
                      icon={faPen}
                      //   style={{ color: "#186477" }}
                    />
                    Sửa
                  </a>
                </div>
                <div className=" bg-teal-600 hover:bg-teal-800 rounded-2xl ">
                  <a href="/" className="">
                    <FontAwesomeIcon
                      icon={faTrashCan}
                      //   style={{ color: "#186477" }}
                    />
                    Xóa
                  </a>
                </div>
              </div>
            </div>
            {/* address2 */}
          </div>
        </div>
      </div>
    </section>
  );
}
