import { Button } from 'flowbite-react';
import { useState, useEffect } from 'react';

type SeatStatus = 'booked' | 'selected' | 'empty';

interface Seat {
  id: number;
  status: SeatStatus;
  fullId: string; // Store the full seat identifier (e.g., 1_A18)
}

interface SeatPickerProps {
  onSelectionChange: (selectedSeats: string[], totalAmount: number) => void;
  seatPrice: number;
  emptySeats: string[]; // Accept emptySeats as prop
}

export default function SeatPicker({
  onSelectionChange,
  seatPrice,
  emptySeats,
}: SeatPickerProps) {
  const [seats, setSeats] = useState<Seat[]>([]);

  // Initialize seats when emptySeats changes
  useEffect(() => {
    const updatedSeats = Array.from({ length: 40 }, (_, i) => {
      const seatId = `1_A${(i + 1).toString().padStart(2, '0')}`; // Full seat ID (e.g., 1_A01)
      return {
        id: i,
        fullId: seatId, // Store full ID for processing
        status: emptySeats.includes(seatId) ? 'empty' : 'booked', // Set status based on emptySeats
      };
    });
    setSeats(updatedSeats);
  }, [emptySeats]);

  const handleSeatClick = (id: number) => {
    setSeats((prevSeats) => {
      const updatedSeats = prevSeats.map((seat) =>
        seat.id === id
          ? {
              ...seat,
              status:
                seat.status === 'empty'
                  ? 'selected'
                  : seat.status === 'selected'
                  ? 'empty'
                  : seat.status,
            }
          : seat
      );

      const selectedSeats = updatedSeats.filter((seat) => seat.status === 'selected');
      const selectedSeatIds = selectedSeats.map((seat) => seat.fullId); // Use fullId for checkout
      const totalAmount = selectedSeats.length * seatPrice;

      onSelectionChange(selectedSeatIds, totalAmount); // Pass full seat IDs to parent

      return updatedSeats;
    });
  };

  const getSeatColor = (status: SeatStatus) => {
    switch (status) {
      case 'booked':
        return 'failure';
      case 'selected':
        return 'blue';
      case 'empty':
      default:
        return 'gray';
    }
  };

  return (
    <div className="flex gap-6">
      <div className="grid grid-cols-4 gap-2">
        {seats.map((seat) => (
          <Button
            key={seat.id}
            size="sm"
            color={`${getSeatColor(seat.status)}`}
            onClick={() => handleSeatClick(seat.id)}
            disabled={seat.status === 'booked'}
          >
            {seat.fullId.replace('1_', '')} {/* Display seat without '1_' prefix */}
          </Button>
        ))}
      </div>
    </div>
  );
}
