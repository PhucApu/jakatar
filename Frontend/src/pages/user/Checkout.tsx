import { Label, TextInput } from 'flowbite-react';
import SeatPicker from '../../components/user/SeatPicker';
import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { getSeatList } from '../../api/services/user/customerService';

export default function Checkout() {
  const location = useLocation();

  const searchParams = new URLSearchParams(location.search);
  const seatPrice = +searchParams.get('price');
  const departureLocation = searchParams.get('departure');
  const destinationLocation = searchParams.get('destination');
  const departureDate = searchParams.get('date');
  const departureTime = searchParams.get('time');
  const scheduleId = searchParams.get('scheduleId');

  const [selectedSeats, setSelectedSeats] = useState<string[]>([]);
  const [totalAmount, setTotalAmount] = useState<number>(0);
  const [emptySeats, setEmptySeats] = useState<string[]>([]);

  useEffect(() => {
    if (departureLocation && scheduleId) {
      const fetchSeats = async () => {
        try {
          const data = await getSeatList({ departureDate, scheduleId });
          console.log(data.data);
          const listSeatEmpty = Object.keys(data.data.listSeatEmpty[0]).filter(
            (key) => {data.data.listSeatEmpty[0][key]}
          );
          setEmptySeats(listSeatEmpty);
          console.log(emptySeats)
        } catch (error) {
          console.error('Error fetching seat data:', error);
        }
      };
      fetchSeats();
    }
  }, [departureLocation, scheduleId]);

  const handleSelectionChange = (seats: string[], total: number) => {
    setSelectedSeats(seats);
    setTotalAmount(total);
  };

  function formatTime(timeString: string): string {
    const [hours, minutes] = timeString.split(':');
    const formattedHours = parseInt(hours, 10);
    return `${formattedHours}g ${minutes}p`;
  }

  return (
    <div className='lg:grid lg:grid-cols-10 gap-8 px-8 mx-auto'>
      <div className='lg:col-span-7'>
        <section className='bg-white border border-gray-200 rounded-lg shadow-sm py-4 px-16 mt-4'>
          <h2 className='text-xl text-center font-semibold'>Chọn ghế</h2>
          <div className='flex gap-x-16 py-4'>
            <SeatPicker onSelectionChange={handleSelectionChange} emptySeats={emptySeats} seatPrice={seatPrice} />
            <div>
              <div className='flex items-center gap-x-2'>
                <div className='w-4 h-4 bg-white bg-opacity-50 border border-gray-200'></div>
                <span>Còn trống</span>
              </div>
              <div className='flex items-center gap-x-2'>
                <div className='w-4 h-4 bg-blue-700 border border-gray-200'></div>
                <span>Đang chọn</span>
              </div>
              <div className='flex items-center gap-x-2'>
                <div className='w-4 h-4 bg-red-700 border border-gray-200'></div>
                <span>Đã bán</span>
              </div>
            </div>
          </div>
        </section>
        <section className='bg-white border border-gray-200 rounded-lg shadow-sm py-8 px-16 mt-4'>
          <h2 className='text-xl text-center font-semibold'>Thông tin khách hàng</h2>
          <div className='flex gap-x-8 py-8'>
            <form className='flex basis-[50%] max-w-md flex-col gap-4'>
              <div>
                <div className='mb-2 block'>
                  <Label htmlFor='name' value='Họ và tên *' />
                </div>
                <TextInput id='name' type='text' placeholder='Họ và tên' required />
              </div>
              <div>
                <div className='mb-2 block'>
                  <Label htmlFor='phone' value='Số điện thoại *' />
                </div>
                <TextInput id='phone' type='phone' placeholder='Số điện thoại' required />
              </div>
              <div>
                <div className='mb-2 block'>
                  <Label htmlFor='email' value='Email *' />
                </div>
                <TextInput id='email' type='email' placeholder='Email' required />
              </div>
            </form>
            <div className='basis-[50%] flex flex-col gap-4'>
              <h3 className='text-red-500 text-xl text-center uppercase'>
                Điều khoản và lưu ý
              </h3>
              <p>
                (*) Quý khách vui lòng có mặt tại bến xuất phát của xe trước ít nhất 30 phút giờ
                xe khởi hành, mang theo thông báo đã thanh toán vé thành công có chứa mã vé được
                gửi từ hệ thống FUTA BUS LINE. Vui lòng liên hệ Trung tâm tổng đài 1900 6067 để
                được hỗ trợ.
              </p>
              <p>
                (*) Nếu quý khách có nhu cầu trung chuyển, vui lòng liên hệ Tổng đài trung
                chuyển 1900 6918 trước khi đặt vé. Chúng tôi không đón/trung chuyển tại những
                điểm xe trung chuyển không thể tới được.
              </p>
            </div>
          </div>
        </section>
        <section className='bg-white border border-gray-200 rounded-lg shadow-sm py-2 px-16 mt-4'>
          <h2 className='text-xl text-center font-semibold'>Thanh toán</h2>
        </section>
      </div>
      <div className='lg:col-span-3 max-h-min bg-white border border-gray-200 flex flex-col gap-y-2 rounded-lg shadow-sm py-4 px-16 mt-4'>
        <h2 className='text-xl text-center font-semibold'>Thông tin chuyến xe</h2>
        <div className='flex items-center justify-between'>
          <p className='font-semibold text-gray-600'>Từ</p>
          <p>{departureLocation}</p>
        </div>
        <div className='flex items-center justify-between'>
          <p className='font-semibold text-gray-600'>Đến</p>
          <p>{destinationLocation}</p>
        </div>
        <div className='flex items-center justify-between'>
          <p className='font-semibold text-gray-600'>Giờ khởi hành</p>
          <p>{formatTime(departureTime!)}</p>
        </div>
        <div className='flex items-center justify-between'>
          <p className='font-semibold text-gray-600'>Số lượng ghế</p>
          <p>{selectedSeats.length}</p>
        </div>
        <div className='flex items-center justify-between'>
          <p className='font-semibold text-gray-600'>Danh sách ghế</p>
          <p className='max-w-32 text-right'>{selectedSeats.join(', ')}</p>
        </div>
        <div className='flex items-center justify-between'>
          <p className='font-semibold text-gray-600'>Tổng cộng</p>
          <p className='font-bold text-cyan-600'>{totalAmount.toLocaleString()}đ</p>
        </div>
      </div>
    </div>
  );
}
