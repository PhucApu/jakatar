import { useEffect, useState, ChangeEvent } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, Select, TextInput } from 'flowbite-react';
import { ToastContainer } from 'react-toastify';
import { HiPlus } from 'react-icons/hi';

import type { TableColumn } from '@type/common/TableColumn';
import type { Employee } from '@type/model/Employee';

import { getEmployees, createEmployee, updateEmployee, deleteEmployee } from '../../api/services/admin/employeeService';

export default function Employee() {
  const [data, setData] = useState<Employee[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isEditMode, setIsEditMode] = useState<boolean>(false);
  const [formData, setFormData] = useState<Partial<Employee>>({});

  const columns: TableColumn<Employee>[] = [
    { name: 'ID Nhân viên', selector: (row) => row.driverId, sortable: true },
    { name: 'Tên nhân viên', selector: (row) => row.driverName, sortable: true },
    { name: 'Là tài xế', selector: (row) => (row.isDriver ? 'Có' : 'Không'), sortable: true },
    { name: 'Số giấy phép lái xe', selector: (row) => row.licenseNumber, sortable: true },
    { name: 'Số điện thoại', selector: (row) => row.phoneNumber, sortable: true },
    { name: 'Tình trạng', selector: (row) => row.isDelete, sortable: true },
  ];

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const employees = await getEmployees();
        setData(employees);
      } catch (error: any) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchEmployees();
  }, []);

  const handleOpenModal = (item: Employee | null = null) => {
    if (item) {
      setIsEditMode(true);
      setFormData(item);
    } else {
      setIsEditMode(false);
      setFormData({
        isDriver: false,
        driverName: '',
        licenseNumber: '',
        phoneNumber: '',
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
      [name]: name === 'isDriver' || name === 'isDelete' ? value === '1' : value,
    }));
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className='p-4 mx-auto'>
      <h1 className='uppercase font-semibold text-2xl tracking-wide mb-4'>
        Quản lý nhân viên
      </h1>
      <Button onClick={() => handleOpenModal()} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm nhân viên
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm nhân viên'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
            <div className='space-y-2'>
              <label htmlFor='driverName'>Tên nhân viên</label>
              <TextInput
                name='driverName'
                value={formData.driverName || ''}
                onChange={handleChange}
                placeholder='Nhập tên nhân viên'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='isDriver'>Là tài xế</label>
              <Select
                name='isDriver'
                value={formData.isDriver ? '1' : '0'}
                onChange={handleChange}
              >
                <option value='0'>Không</option>
                <option value='1'>Có</option>
              </Select>
            </div>
            <div className='space-y-2'>
              <label htmlFor='licenseNumber'>Số giấy phép lái xe</label>
              <TextInput
                name='licenseNumber'
                value={formData.licenseNumber || ''}
                onChange={handleChange}
                placeholder='Nhập số giấy phép lái xe'
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