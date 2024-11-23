import { useEffect, useState, ChangeEvent } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, Select, TextInput } from 'flowbite-react';
import { ToastContainer } from 'react-toastify';
import { HiPlus } from 'react-icons/hi';

import type { TableColumn } from '@type/common/TableColumn';
import type { Ticket } from '@type/model/Ticket';

import {
  getTickets,
  createTicket,
  updateTicket,
  deleteTicket,
} from '../../api/services/admin/ticketService';

export default function Ticket() {
  const [data, setData] = useState<Ticket[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isEditMode, setIsEditMode] = useState<boolean>(false);
  const [formData, setFormData] = useState<Partial<Ticket>>({});

  const columns: TableColumn<Ticket>[] = [
    { name: 'ID Vé', selector: (row) => row.ticketId, sortable: true },
    { name: 'ID Tài khoản', selector: (row) => row.accountEnity_Id, sortable: true },
    { name: 'ID Xe buýt', selector: (row) => row.busEntity_Id, sortable: true },
    { name: 'ID Tuyến', selector: (row) => row.busRoutesEntity_Id, sortable: true },
    { name: 'Số ghế', selector: (row) => row.seatNumber, sortable: true },
    { name: 'Ngày khởi hành', selector: (row) => row.departureDate, sortable: true },
    { name: 'Số điện thoại', selector: (row) => row.phoneNumber, sortable: true },
    { name: 'Trạng thái', selector: (row) => row.status, sortable: true },
    { name: 'Giá tiền', selector: (row) => row.price, sortable: true },
    { name: 'Tình trạng', selector: (row) => row.isDelete, sortable: true },
  ];

  useEffect(() => {
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

    fetchTickets();
  }, []);

  const handleOpenModal = (item: Ticket | null = null) => {
    if (item) {
      setIsEditMode(true);
      setFormData(item);
    } else {
      setIsEditMode(false);
      setFormData({
        accountEnity_Id: '',
        busEntity_Id: 0,
        busRoutesEntity_Id: 0,
        paymentEntity_Id: 0,
        discountEntity_Id: 0,
        seatNumber: '',
        departureDate: '',
        price: 0,
        phoneNumber: '',
        status: '',
        isDelete: false,
      });
    }
    setOpenModal(true);
  };

  const handleSave = () => {
    if (isEditMode) {
      console.log('Updating item:', formData);
      // Update logic here
    } else {
      console.log('Adding new item:', formData);
      // Add logic here
    }
    setOpenModal(false);
  };

  const handleChange = (e: ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className='p-4 mx-auto'>
      <h1 className='uppercase font-semibold text-2xl tracking-wide mb-4'>Quản lý vé</h1>
      <Button onClick={() => handleOpenModal()} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm vé
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm vé'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
            <div className='space-y-2'>
              <label htmlFor='accountEnity_Id'>ID Tài khoản</label>
              <TextInput
                name='accountEnity_Id'
                value={formData.accountEnity_Id || ''}
                onChange={handleChange}
                placeholder='Nhập ID tài khoản'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='busEntity_Id'>ID Xe buýt</label>
              <TextInput
                name='busEntity_Id'
                type='number'
                value={formData.busEntity_Id || ''}
                onChange={handleChange}
                placeholder='Nhập ID xe buýt'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='busRoutesEntity_Id'>ID Tuyến</label>
              <TextInput
                name='busRoutesEntity_Id'
                type='number'
                value={formData.busRoutesEntity_Id || ''}
                onChange={handleChange}
                placeholder='Nhập ID tuyến'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='seatNumber'>Số ghế</label>
              <TextInput
                name='seatNumber'
                value={formData.seatNumber || ''}
                onChange={handleChange}
                placeholder='Nhập số ghế'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='departureDate'>Ngày khởi hành</label>
              <TextInput
                name='departureDate'
                type='date'
                value={formData.departureDate || ''}
                onChange={handleChange}
                placeholder='Chọn ngày khởi hành'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='price'>Giá tiền</label>
              <TextInput
                name='price'
                type='number'
                value={formData.price || ''}
                onChange={handleChange}
                placeholder='Nhập giá tiền'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='phoneNumber'>Số điện thoại</label>
              <TextInput
                name='phoneNumber'
                value={formData.phoneNumber || ''}
                onChange={handleChange}
                placeholder='Nhập số điện thoại'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='status'>Trạng thái</label>
              <TextInput
                name='status'
                value={formData.status || ''}
                onChange={handleChange}
                placeholder='Nhập trạng thái'
              />
            </div>
            <div className='space-y-2'>
              <label>Tình trạng</label>
              <Select
                name='isDelete'
                value={formData.isDelete ? '1' : '0'}
                onChange={handleChange}
              >
                <option value='0'>Không hoạt động</option>
                <option value='1'>Hoạt động</option>
              </Select>
            </div>
          </div>
          <HR className='my-4' />
          <div className='flex flex-row-reverse gap-2'>
            <Button onClick={handleSave}>{isEditMode ? 'Cập nhật' : 'Thêm'}</Button>
            <Button color='gray' onClick={() => setOpenModal(false)}>
              Hủy
            </Button>
          </div>
        </Modal.Body>
      </Modal>
      <ToastContainer />
    </div>
  );
}
