import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar } from "@fortawesome/free-solid-svg-icons";
export default function FeedBack() {
  return (
    <section className="py-24">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        <div className="grid lg:grid-cols-2 grid-cols-1">
          <div className="lg:mb-0 mb-10">
            <div className="group w-full h-full">
              <div className="relative h-full">
                <img
                  src="/feedback.png"
                  alt="Ticket Information"
                  className="w-full h-full lg:rounded-l-2xl rounded-2xl bg-blend-multiply bg-cyan-700 object-cover"
                />
                <h1 className="font-manrope text-white text-4xl font-bold leading-10 absolute top-11 left-11">
                  Tra cứu vé
                </h1>
              </div>
            </div>
          </div>

          <div className="bg-gray-50 p-5 lg:p-11 lg:rounded-r-2xl rounded-2xl">
            <h2 className="text-cyan-600 font-manrope text-4xl font-semibold leading-10 mb-11">
              Đánh giá và góp ý
            </h2>
            <input
              type="text"
              className="w-full h-12 text-gray-600 placeholder-gray-400 shadow-sm bg-transparent text-lg font-normal leading-7 rounded-full border border-gray-200 focus:outline-none pl-4 mb-10"
              placeholder="Mã chuyến đi"
            />
            <textarea
              type="text"
              className="w-full h-12 text-gray-600 placeholder-gray-400 shadow-sm bg-transparent text-lg font-normal leading-7 rounded-full border border-gray-200 focus:outline-none pl-4 mb-10 row-span-full"
              placeholder="Nội dung cần góp ý đánh giá trong chuyến đi đó"
            ></textarea>
            <input className="" />
            <FontAwesomeIcon
              icon={faStar}
              style={{ color: "#ffb02f" }}
              className="mr-2"
            />
            <FontAwesomeIcon
              icon={faStar}
              style={{ color: "#ffb02f" }}
              className="mr-2"
            />
            <FontAwesomeIcon
              icon={faStar}
              style={{ color: "#ffb02f" }}
              className="mr-2"
            />
            <FontAwesomeIcon
              icon={faStar}
              style={{ color: "#ffb02f" }}
              className="mr-2"
            />
            <FontAwesomeIcon
              icon={faStar}
              style={{ color: "#ffb02f" }}
              className="mr-2"
            />
            <button className="w-full h-12 text-white text-base font-semibold leading-6 rounded-full transition-all duration-700 hover:bg-cyan-800 bg-cyan-600 shadow-sm">
              Xác nhận đánh giá
            </button>
          </div>
        </div>
      </div>
    </section>
  );
}
