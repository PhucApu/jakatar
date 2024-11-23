import { useEffect, useState, ChangeEvent } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, Select, TextInput } from 'flowbite-react';
import { ToastContainer } from 'react-toastify';
import { HiPlus } from 'react-icons/hi';

import type { TableColumn } from '@type/common/TableColumn';
import type { Discount } from '@type/model/Discount';

import { getDiscounts, createDiscount, updateDiscount, deleteDiscount } from '../../api/services/admin/discountService';

export default function Discount() {
  const [data, setData] = useState<Discount[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isEditMode, setIsEditMode] = useState<boolean>(false);
  const [formData, setFormData] = useState<Partial<Discount>>({});

  const columns: TableColumn<Discount>[] = [
    { name: 'ID Giảm giá', selector: (row) => row.discountId, sortable: true },
    { name: 'Phần trăm giảm giá (%)', selector: (row) => row.discountPercentage, sortable: true },
    { name: 'Ngày bắt đầu', selector: (row) => row.validFrom, sortable: true },
    { name: 'Ngày kết thúc', selector: (row) => row.validUntil, sortable: true },
    { name: 'Số tiền giảm', selector: (row) => row.amount, sortable: true },
    { name: 'Tình trạng', selector: (row) => row.isDelete, sortable: true },
  ];

  useEffect(() => {
    const fetchDiscounts = async () => {
      try {
        const discounts = await getDiscounts();
        setData(discounts);
      } catch (error: any) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchDiscounts();
  }, []);

  const handleOpenModal = (item: Discount | null = null) => {
    if (item) {
      setIsEditMode(true);
      setFormData(item);
    } else {
      setIsEditMode(false);
      setFormData({
        discountPercentage: 0,
        validFrom: '',
        validUntil: '',
        amount: 0,
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
        Quản lý giảm giá
      </h1>
      <Button onClick={() => handleOpenModal()} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm giảm giá
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm giảm giá'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
            <div className='space-y-2'>
              <label htmlFor='discountPercentage'>Phần trăm giảm giá (%)</label>
              <TextInput
                name='discountPercentage'
                type='number'
                value={formData.discountPercentage || ''}
                onChange={handleChange}
                placeholder='Nhập phần trăm giảm giá'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='validFrom'>Ngày bắt đầu</label>
              <TextInput
                name='validFrom'
                type='date'
                value={formData.validFrom || ''}
                onChange={handleChange}
                placeholder='Chọn ngày bắt đầu'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='validUntil'>Ngày kết thúc</label>
              <TextInput
                name='validUntil'
                type='date'
                value={formData.validUntil || ''}
                onChange={handleChange}
                placeholder='Chọn ngày kết thúc'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='amount'>Số tiền giảm</label>
              <TextInput
                name='amount'
                type='number'
                value={formData.amount || ''}
                onChange={handleChange}
                placeholder='Nhập số tiền giảm'
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