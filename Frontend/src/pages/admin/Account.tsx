import { useEffect, useState, ChangeEvent } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, Select, TextInput } from 'flowbite-react';
import { ToastContainer } from 'react-toastify';
import { HiPlus } from 'react-icons/hi';

import type { TableColumn } from '@type/common/TableColumn';
import type { User } from '@type/model/User';

import {
  createAccount,
  getAccounts,
  updateAccount,
  deleteAccount,
  hideAccount,
} from '../../api/services/admin/accountService';

export default function Account() {
  const [data, setData] = useState<User[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isEditMode, setIsEditMode] = useState<boolean>(false);
  const [formData, setFormData] = useState<Partial<User>>({});

  const columns: TableColumn<User>[] = [
    { name: 'Tài khoản', selector: (row) => row.userName, sortable: true },
    { name: 'SĐT', selector: (row) => row.phoneNumber, sortable: true },
    { name: 'Email', selector: (row) => row.email, sortable: true },
    { name: 'Quyền hạn', selector: (row) => row.role, sortable: true },
    { name: 'Tình trạng', selector: (row) => row.isBlock, sortable: true },
  ];

  useEffect(() => {
    const fetchUseres = async () => {
      try {
        const accounts = await getAccounts();
        setData(accounts);
      } catch (error: any) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchUseres();
  }, []);

  const handleOpenModal = (item: User | null = null) => {
    if (item) {
      setIsEditMode(true);
      setFormData(item);
    } else {
      setIsEditMode(false);
      setFormData({ userName: '', phoneNumber: '', email: '', role: '', isBlock: false });
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
      <h1 className='uppercase font-semibold text-2xl tracking-wide mb-4'>Quản lý tài khoản</h1>
      <Button onClick={() => handleOpenModal()} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm tài khoản
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm tài khoản'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
            <div className='space-y-2'>
              <label htmlFor='username'>Tên tài khoản</label>
              <TextInput
                name='username'
                value={formData.userName || ''}
                disabled={formData.userName ? true : false}
                onChange={handleChange}
                placeholder='Nhập tên tài khoản'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='username'>Họ và tên</label>
              <TextInput
                name='username'
                value={formData.userName || ''}
                disabled={formData.userName ? true : false}
                onChange={handleChange}
                placeholder='Nhập họ và tên'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='phoneNumber'>Số điện thoại</label>
              <TextInput
                name='phoneNumber'
                type='number'
                value={formData.phoneNumber || ''}
                onChange={handleChange}
                placeholder='Nhập SĐT'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='email'>Email</label>
              <TextInput
                name='email'
                type='text'
                value={formData.email || ''}
                disabled={formData.email ? true : false}
                onChange={handleChange}
                placeholder='Nhập email'
              />
            </div>
            <div className='space-y-2'>
              <label>Quyền hạn</label>
              <Select>
                <option value='0'>Không hoạt động</option>
                <option value='1'>Hoạt động</option>
              </Select>
            </div>
            <div className='space-y-2'>
              <label>Tình trạng</label>
              <Select>
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
