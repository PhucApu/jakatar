import { useEffect, useState } from "react";
import { toPng } from "html-to-image";
import { saveAs } from "file-saver";
import type { Ticket } from '@type/model/Ticket';
import { getTickets} from '../../api/services/admin/ticketService';

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
    <>
      <div
        id="ticket"
        style={{
          border: "1px solid #ccc",
          padding: "16px",
          width: "300px",
          background: "#f9f9f9",
          textAlign: "center",
        }}
      >
        <h2>Bus Ticket</h2>
        <p>Ticket Number: 12345ABC</p>
        <p>Passenger Name: John Doe</p>
        <p>From: Ho Chi Minh City</p>
        <p>To: Hanoi</p>
        <p>Departure Time: 2024-12-06 08:00 AM</p>
        <p>Seat Number: B12</p>
        <p>Price: 350,000 VND</p>
      </div>
      <button onClick={handleExport}>Export Ticket as Image</button>
    </>
  );
};

export default ExportTicket;
