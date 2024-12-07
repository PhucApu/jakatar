import { useEffect, useState } from "react";
import { toPng } from "html-to-image";
import { saveAs } from "file-saver";
import type { Ticket } from '@type/model/Ticket';
import { getTickets } from '../../api/services/admin/ticketService';

const ExportTicket: React.FC = () => {
  const [data, setData] = useState<Ticket[]>([]);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(true);

  // Lấy dữ liệu ticket từ API
  const fetchTickets = async () => {
    try {
      const tickets = await getTickets();
      setData(tickets);
    } catch (error: any) {
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTickets();
  }, []);

  const handleExport = () => {
    const ticketElement = document.getElementById("ticket");
    if (ticketElement) {
      toPng(ticketElement).then((data) => {
        const blob = dataURLToBlob(data);
        saveAs(blob, "BusTicket.png");
      });
    }
  };

  const dataURLToBlob = (dataURL: string) => {
    const byteString = atob(dataURL.split(",")[1]);
    const mimeString = dataURL.split(",")[0].split(":")[1].split(";")[0];
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ab], { type: mimeString });
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 -m-32">
      <div
        id="ticket"
        className="border border-gray-300 bg-gradient-to-r from-blue-500 to-blue-700 text-white rounded-xl shadow-md p-6 w-96 text-center"
      >
        <h2 className="text-2xl font-bold mb-4">Vé xe AnhBaBus</h2>
        <p className="text-lg font-medium mb-2">
          <strong>Mã vé:</strong> 12345ABC
        </p>
        <p className="text-lg font-medium mb-2">
          <strong>Passenger Name:</strong> John Doe
        </p>
        <p className="text-lg font-medium mb-2">
          <strong>From:</strong> Ho Chi Minh City
        </p>
        <p className="text-lg font-medium mb-2">
          <strong>To:</strong> Hanoi
        </p>
        <p className="text-lg font-medium mb-2">
          <strong>Departure Time:</strong> 2024-12-06 08:00 AM
        </p>
        <p className="text-lg font-medium mb-2">
          <strong>Seat Number:</strong> B12
        </p>
        <p className="text-xl font-bold mt-4">
          Price: <span className="text-yellow-300">350,000 VND</span>
        </p>
      </div>
      <div className="flex gap-4">
      <button
        className="mt-6 font-semibold py-2 px-4 rounded-lg shadow-lg transition-transform transform hover:scale-105"
      >
        <a href="/dat-ve">Quay lại</a>
      </button>
      <button
        onClick={handleExport}
        className="mt-6 bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded-lg shadow-lg transition-transform transform hover:scale-105"
      >
        Xuất vé
      </button>
      </div>
    </div>
  );
};

export default ExportTicket;
