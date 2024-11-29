import { useState } from 'react';
import { getStatisticsTickets, getStatisticsPenaltyTickets } from '../../api/services/admin/statictisService';
import { Line } from 'react-chartjs-2';
import 'chart.js/auto';

const AdminDashboard = () => {
  const [dateA, setDateA] = useState(''); // Giá trị ban đầu là rỗng
  const [dateB, setDateB] = useState('');
  const [ticketStats, setTicketStats] = useState(null);
  const [penaltyStats, setPenaltyStats] = useState(null);
  const [loading, setLoading] = useState(false);

  const fetchTicketStatistics = async () => {
    setLoading(true);
    try {
      // Gọi API với dateA và dateB
      const data = await getStatisticsTickets(dateA, dateB);
      setTicketStats(data);
      console.log(">>> statistics ticket: ", data)
    } catch (error) {
      console.error('Error fetching ticket statistics:', error);
    } finally {
      setLoading(false);
    }
  };

  const fetchPenaltyStatistics = async () => {
    setLoading(true);
    try {
      const data = await getStatisticsPenaltyTickets(dateA, dateB);
      setPenaltyStats(data);
      console.log(">>> statistics ticket penaty: ", data)
    } catch (error) {
      console.error('Error fetching penalty ticket statistics:', error);
    } finally {
      setLoading(false);
    }
  };

  // Cấu hình dữ liệu biểu đồ
  const chartData = {
    labels: ticketStats?.dates || [],
    datasets: [
      {
        label: 'Tổng số vé',
        data: ticketStats?.ticketCounts || [],
        backgroundColor: 'rgba(54, 162, 235, 0.5)',
        borderColor: 'rgba(54, 162, 235, 1)',
        borderWidth: 1,
      },
      {
        label: 'Tổng số vé phạt',
        data: penaltyStats?.penaltyCounts || [],
        backgroundColor: 'rgba(255, 99, 132, 0.5)',
        borderColor: 'rgba(255, 99, 132, 1)',
        borderWidth: 1,
      },
    ],
  };

  return (
    <div className="p-4 mx-auto">
      <h1 className="uppercase font-semibold text-2xl tracking-wide mb-4">
        Thống kê
      </h1>

      {/* Bộ lọc ngày */}
      <div className="space-y-6 mb-6">
        <div className="space-y-2">
          <label htmlFor="dateA" className="block">Ngày bắt đầu</label>
          <input
            type="datetime-local"
            id="dateA"
            value={dateA}
            onChange={(e) => setDateA(e.target.value)}
            className="border rounded p-2 w-full"
          />
        </div>
        <div className="space-y-2">
          <label htmlFor="dateB" className="block">Ngày kết thúc</label>
          <input
            type="datetime-local"
            id="dateB"
            value={dateB}
            onChange={(e) => setDateB(e.target.value)}
            className="border rounded p-2 w-full"
          />
        </div>
        <button
          onClick={fetchTicketStatistics}
          className="bg-blue-500 text-white p-2 rounded mr-4"
        >
          Lấy thống kê vé
        </button>
        <button
          onClick={fetchPenaltyStatistics}
          className="bg-red-500 text-white p-2 rounded"
        >
          Lấy thống kê vé phạt
        </button>
      </div>

      {/* Loading state */}
      {loading && <p>Đang tải dữ liệu...</p>}

      {/* Hiển thị thống kê */}
      <div className="space-y-6">
        <h2 className="text-xl font-bold">Kết quả thống kê</h2>
        <div>
          <h3 className="text-lg font-semibold">Tổng số vé:</h3>
          <p>{ticketStats?.totalTickets || 'Chưa có dữ liệu'}</p>
        </div>
        <div>
          <h3 className="text-lg font-semibold">Tổng số vé phạt:</h3>
          <p>{penaltyStats?.totalPenaltyTickets || 'Chưa có dữ liệu'}</p>
        </div>
      </div>

      {/* Biểu đồ */}
      <div className="mt-6">
        <h2 className="text-xl font-bold">Biểu đồ thống kê</h2>
        {ticketStats && penaltyStats ? (
          <Line data={chartData} />
        ) : (
          <p>Chưa có dữ liệu để hiển thị biểu đồ.</p>
        )}
      </div>
    </div>
  );
};

export default AdminDashboard;
