import { useEffect, useState, ChangeEvent } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, Select, TextInput, Textarea } from 'flowbite-react';
import { ToastContainer } from 'react-toastify';
import { HiPlus } from 'react-icons/hi';

import type { TableColumn } from '@type/common/TableColumn';
import type { PenaltyTicket } from '@type/model/PenaltyTicket';

import { getPenaltyTickets, createPenaltyTicket, updatePenaltyTicket, deletePenaltyTicket } from '../../api/services/admin/penaltyTicketService';

export default function PenaltyTicket() {
  const [data, setData] = useState<PenaltyTicket[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isEditMode, setIsEditMode] = useState<boolean>(false);
  const [formData, setFormData] = useState<Partial<PenaltyTicket>>({});

  const columns: TableColumn<PenaltyTicket>[] = [
    { name: 'ID Biên bản', selector: (row) => row.penaltyTicketId, sortable: true },
    { name: 'ID Xe buýt', selector: (row) => row.busEntity_Id, sortable: true },
    { name: 'ID Nhân viên', selector: (row) => row.employeeEntity_Id, sortable: true },
    { name: 'Ngày xử phạt', selector: (row) => row.penaltyDay, sortable: true },
    { name: 'Mô tả', selector: (row) => row.description, sortable: false },
    { name: 'Trách nhiệm', selector: (row) => (row.responsibility ? 'Có' : 'Không'), sortable: true },
    { name: 'Giá tiền', selector: (row) => row.price, sortable: true },
    { name: 'Tình trạng', selector: (row) => row.isDelete, sortable: true },
  ];

  useEffect(() => {
    const fetchPenaltyTickets = async () => {
      try {
        const penaltyTickets = await getPenaltyTickets();
        setData(penaltyTickets);
      } catch (error: any) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchPenaltyTickets();
  }, []);

  const handleOpenModal = (item: PenaltyTicket | null = null) => {
    if (item) {
      setIsEditMode(true);
      setFormData(item);
    } else {
      setIsEditMode(false);
      setFormData({
        busEntity_Id: 0,
        employeeEntity_Id: 0,
        penaltyDay: '',
        description: '',
        responsibility: false,
        price: 0,
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
      [name]: name === 'responsibility' || name === 'isDelete' ? value === '1' : value,
    }));
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className='p-4 mx-auto'>
      <h1 className='uppercase font-semibold text-2xl tracking-wide mb-4'>
        Quản lý biên bản xử phạt
      </h1>
      <Button onClick={() => handleOpenModal()} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm biên bản
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm biên bản'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
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
              <label htmlFor='employeeEntity_Id'>ID Nhân viên</label>
              <TextInput
                name='employeeEntity_Id'
                type='number'
                value={formData.employeeEntity_Id || ''}
                onChange={handleChange}
                placeholder='Nhập ID nhân viên'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='penaltyDay'>Ngày xử phạt</label>
              <TextInput
                name='penaltyDay'
                type='date'
                value={formData.penaltyDay || ''}
                onChange={handleChange}
                placeholder='Chọn ngày xử phạt'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='description'>Mô tả</label>
              <Textarea
                name='description'
                value={formData.description || ''}
                onChange={handleChange}
                placeholder='Nhập mô tả vi phạm'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='responsibility'>Trách nhiệm</label>
              <Select
                name='responsibility'
                value={formData.responsibility ? '1' : '0'}
                onChange={handleChange}
              >
                <option value='0'>Không</option>
                <option value='1'>Có</option>
              </Select>
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