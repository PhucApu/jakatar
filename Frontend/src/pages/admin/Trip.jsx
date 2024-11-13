import { useEffect, useState } from 'react';
import Table from '../../components/admin/Table';
import { Button, HR, Modal, TextInput } from 'flowbite-react';
import { HiPlus } from 'react-icons/hi';

export default function Trip() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [openModal, setOpenModal] = useState(false);
  const [isEditMode, setIsEditMode] = useState(false);
  const [formData, setFormData] = useState({});

  const columns = [
    { name: 'Name', selector: (row) => row.name, sortable: true },
    { name: 'Position', selector: (row) => row.position, sortable: true }, // Fixed typo
    { name: 'Office', selector: (row) => row.office, sortable: true },
    { name: 'Extn', selector: (row) => row.extn, sortable: true },
    { name: 'Start Date', selector: (row) => row.start_date, sortable: true },
    { name: 'Salary', selector: (row) => row.salary, sortable: true },
  ];

  useEffect(() => {
    // Fetch data from the JSON file in the public folder
    fetch('/data/data.json')
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        setData(data.data);
        setLoading(false);
      })
      .catch((error) => {
        setError(error.message);
        setLoading(false);
      });
  }, []);

  const handleOpenModal = (item = null) => {
    if (item) {
      // Set to edit mode if item is provided
      setIsEditMode(true);
      setFormData(item); // Populate form with item data
    } else {
      // Set to add mode
      setIsEditMode(false);
      setFormData({ name: '', description: '' }); // Clear the form
    }
    setOpenModal(true);
  };

  const handleSave = () => {
    if (isEditMode) {
      console.log('Updating item:', formData);
      // Call the update function here
    } else {
      console.log('Adding new item:', formData);
      // Call the add function here
    }
    setOpenModal(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className='p-4 mx-auto'>
      <h1 className='uppercase font-semibold text-2xl tracking-wide mb-4'>
        Quản lý lịch trình
      </h1>
      <Button onClick={() => setOpenModal(true)} size='sm'>
        <HiPlus className='mr-2 h-5 w-5' />
        Thêm lịch trình
      </Button>
      <Table rows={data} columns={columns} onEdit={handleOpenModal} />
      {/* Form thêm hoặc sửa dữ liệu */}
      <Modal show={openModal} onClose={() => setOpenModal(false)}>
        <Modal.Header>{isEditMode ? 'Cập nhật' : 'Thêm lịch  trình'}</Modal.Header>
        <Modal.Body>
          <div className='space-y-6'>
            <TextInput
              label='Name'
              name='name'
              value={formData.name || ''}
              onChange={handleChange}
              placeholder='Enter name'
            />
            <TextInput
              label='Description'
              name='description'
              value={formData.description || ''}
              onChange={handleChange}
              placeholder='Enter description'
            />
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
    </div>
  );
}
