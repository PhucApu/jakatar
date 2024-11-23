import { useEffect, useState, ChangeEvent } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, Select, TextInput } from 'flowbite-react';
import { ToastContainer } from 'react-toastify';
import { HiPlus } from 'react-icons/hi';

import type { TableColumn } from '@type/common/TableColumn';
import type { BusRoute } from '@type/model/BusRoute';

import { getBusRoutes, createBusRoute, updateBusRoute, deleteBusRoute } from '../../api/services/admin/busRouteService';

export default function TripRoute() {
  const [data, setData] = useState<BusRoute[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isEditMode, setIsEditMode] = useState<boolean>(false);
  const [formData, setFormData] = useState<Partial<BusRoute>>({});

  const columns: TableColumn<BusRoute>[] = [
    { name: 'ID Tuyến', selector: (row) => row.routesId, sortable: true },
    { name: 'Điểm khởi hành', selector: (row) => row.departureLocation, sortable: true },
    { name: 'Điểm đến', selector: (row) => row.destinationLocation, sortable: true },
    { name: 'Khoảng cách (km)', selector: (row) => row.distanceKilometer, sortable: true },
    { name: 'Giá vé', selector: (row) => row.price, sortable: true },
    { name: 'Tình trạng', selector: (row) => row.isDelete, sortable: true },
  ];

  useEffect(() => {
    const fetchBusRoutes = async () => {
      try {
        const busRoutes = await getBusRoutes();
        setData(busRoutes);
      } catch (error: any) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchBusRoutes();
  }, []);

  const handleOpenModal = (item: BusRoute | null = null) => {
    if (item) {
      setIsEditMode(true);
      setFormData(item);
    } else {
      setIsEditMode(false);
      setFormData({
        departureLocation: '',
        destinationLocation: '',
        distanceKilometer: 0,
        departureTime: '',
        arivalTime: '',
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

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className='p-4 mx-auto'>
      <h1 className='uppercase font-semibold text-2xl tracking-wide mb-4'>
        Quản lý tuyến xe buýt
      </h1>
      <Button onClick={() => handleOpenModal()} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm tuyến
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm tuyến'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
            <div className='space-y-2'>
              <label htmlFor='departureLocation'>Điểm khởi hành</label>
              <TextInput
                name='departureLocation'
                value={formData.departureLocation || ''}
                onChange={handleChange}
                placeholder='Nhập điểm khởi hành'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='destinationLocation'>Điểm đến</label>
              <TextInput
                name='destinationLocation'
                value={formData.destinationLocation || ''}
                onChange={handleChange}
                placeholder='Nhập điểm đến'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='distanceKilometer'>Khoảng cách (km)</label>
              <TextInput
                name='distanceKilometer'
                type='number'
                value={formData.distanceKilometer || ''}
                onChange={handleChange}
                placeholder='Nhập khoảng cách'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='departureTime'>Thời gian khởi hành</label>
              <TextInput
                name='departureTime'
                type='time'
                value={formData.departureTime || ''}
                onChange={handleChange}
                placeholder='Nhập thời gian khởi hành'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='arivalTime'>Thời gian đến</label>
              <TextInput
                name='arivalTime'
                type='time'
                value={formData.arivalTime || ''}
                onChange={handleChange}
                placeholder='Nhập thời gian đến'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='price'>Giá vé</label>
              <TextInput
                name='price'
                type='number'
                value={formData.price || ''}
                onChange={handleChange}
                placeholder='Nhập giá vé'
              />
            </div>
            <div className='space-y-2'>
              <label>Tình trạng</label>
              <Select
                value={formData.isDelete ? '1' : '0'}
                onChange={(e) =>
                  setFormData((prev) => ({
                    ...prev,
                    isDelete: e.target.value === '1',
                  }))
                }
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