import { useEffect, useState, ChangeEvent } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, Select, TextInput } from 'flowbite-react';
import { ToastContainer } from 'react-toastify';
import { HiPlus } from 'react-icons/hi';

import type { TableColumn } from '@type/common/TableColumn';
import type { Bus } from '@type/model/Bus';

import { getBuses, createBus, updateBus, deleteBus } from '../../api/services/admin/busService';

export default function Bus() {
  const [data, setData] = useState<Bus[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isEditMode, setIsEditMode] = useState<boolean>(false);
  const [formData, setFormData] = useState<Partial<Bus>>({});

  const columns: TableColumn<Bus>[] = [
    { name: 'Mã buýt', selector: (row) => row.busId, sortable: true },
    { name: 'Biển kiểm soát', selector: (row) => row.busNumber, sortable: true },
    { name: 'Sức chứa', selector: (row) => row.capacity, sortable: true },
    { name: 'Hãng xe', selector: (row) => row.brand, sortable: true },
    { name: 'Tình trạng', selector: (row) => row.isDelete, sortable: true },
  ];

  useEffect(() => {
    const fetchBuses = async () => {
      try {
        const buses = await getBuses();
        setData(buses);
      } catch (error: any) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchBuses();
  }, []);

  const handleOpenModal = (item: Bus | null = null) => {
    if (item) {
      setIsEditMode(true);
      setFormData(item);
    } else {
      setIsEditMode(false);
      setFormData({ busNumber: '', capacity: 0, brand: '', isDelete: false });
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

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className='p-4 mx-auto'>
      <h1 className='uppercase font-semibold text-2xl tracking-wide mb-4'>
        Quản lý buýt
      </h1>
      <Button onClick={() => handleOpenModal()} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm buýt
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm buýt'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
            <div className='space-y-2'>
              <label htmlFor='busNumber'>Biển kiểm soát</label>
              <TextInput
                name='busNumber'
                value={formData.busNumber || ''}
                onChange={handleChange}
                placeholder='Nhập biển kiểm soát'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='capacity'>Sức chứa</label>
              <TextInput
                name='capacity'
                type='number'
                value={formData.capacity || ''}
                onChange={handleChange}
                placeholder='Nhập sức chứa'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='brand'>Hãng xe</label>
              <TextInput
                name='brand'
                type='number'
                value={formData.brand || ''}
                onChange={handleChange}
                placeholder='Nhập sức chứa'
              />
            </div>
            <div className='space-y-2'>
              <label>Tình trạng</label>
              <Select value={formData.isDelete ? '1' : '0'}>
                <option value='0'>Không hoạt động</option>
                <option value='1'>Hoạt động</option>
              </Select>
            </div>
            <div>
            <label htmlFor='brand'>Sức chứa</label>
              <TextInput
                name='brand'
                type='number'
                value={formData.brand || ''}
                onChange={handleChange}
                placeholder='Nhập sức chứa'
              />
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
