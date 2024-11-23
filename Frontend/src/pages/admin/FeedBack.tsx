import { useEffect, useState, ChangeEvent } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, Select, TextInput, Textarea } from 'flowbite-react';
import { ToastContainer } from 'react-toastify';
import { HiPlus } from 'react-icons/hi';

import type { TableColumn } from '@type/common/TableColumn';
import type { Feedback } from '@type/model/Feedback';

import {
  getFeedbacks,
  createFeedback,
  updateFeedback,
  deleteFeedback,
} from '../../api/services/admin/feedbackService';

export default function Feedback() {
  const [data, setData] = useState<Feedback[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [isEditMode, setIsEditMode] = useState<boolean>(false);
  const [formData, setFormData] = useState<Partial<Feedback>>({});

  const columns: TableColumn<Feedback>[] = [
    { name: 'ID Phản hồi', selector: (row) => row.feedbackId, sortable: true },
    { name: 'Tên tài khoản', selector: (row) => row.accountEnity_userName, sortable: true },
    { name: 'ID Vé', selector: (row) => row.ticketEntity_Id, sortable: true },
    { name: 'Nội dung', selector: (row) => row.content, sortable: false },
    { name: 'Đánh giá', selector: (row) => row.rating, sortable: true },
    { name: 'Ngày bình luận', selector: (row) => row.dateComment, sortable: true },
    { name: 'Tình trạng', selector: (row) => row.isDelete, sortable: true },
  ];

  useEffect(() => {
    const fetchFeedbacks = async () => {
      try {
        const feedbacks = await getFeedbacks();
        setData(feedbacks);
      } catch (error: any) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchFeedbacks();
  }, []);

  const handleOpenModal = (item: Feedback | null = null) => {
    if (item) {
      setIsEditMode(true);
      setFormData(item);
    } else {
      setIsEditMode(false);
      setFormData({
        accountEnity_userName: '',
        ticketEntity_Id: 0,
        content: '',
        rating: 0,
        dateComment: '',
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

  const handleChange = (
    e: ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: name === 'rating' || name === 'ticketEntity_Id' ? Number(value) : value,
    }));
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className='p-4 mx-auto'>
      <h1 className='uppercase font-semibold text-2xl tracking-wide mb-4'>Quản lý phản hồi</h1>
      <Button onClick={() => handleOpenModal()} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm phản hồi
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm phản hồi'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
            <div className='space-y-2'>
              <label htmlFor='accountEnity_userName'>Tên tài khoản</label>
              <TextInput
                name='accountEnity_userName'
                value={formData.accountEnity_userName || ''}
                onChange={handleChange}
                placeholder='Nhập tên tài khoản'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='ticketEntity_Id'>ID Vé</label>
              <TextInput
                name='ticketEntity_Id'
                type='number'
                value={formData.ticketEntity_Id || ''}
                onChange={handleChange}
                placeholder='Nhập ID vé'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='content'>Nội dung</label>
              <Textarea
                name='content'
                value={formData.content || ''}
                onChange={handleChange}
                placeholder='Nhập nội dung phản hồi'
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='rating'>Đánh giá</label>
              <TextInput
                name='rating'
                type='number'
                value={formData.rating || ''}
                onChange={handleChange}
                placeholder='Nhập điểm đánh giá (1-5)'
                min={1}
                max={5}
              />
            </div>
            <div className='space-y-2'>
              <label htmlFor='dateComment'>Ngày bình luận</label>
              <TextInput
                name='dateComment'
                type='date'
                value={formData.dateComment || ''}
                onChange={handleChange}
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
