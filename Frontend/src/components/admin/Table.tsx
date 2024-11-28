import { useState } from 'react';
import DataTable from 'react-data-table-component';
import PropTypes from 'prop-types';
import { Dropdown, TextInput } from 'flowbite-react';

Table.propTypes = {
  columns: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      selector: PropTypes.func,
      sortable: PropTypes.bool,
    })
  ).isRequired,
  rows: PropTypes.arrayOf(PropTypes.object).isRequired,
  searchableFields: PropTypes.arrayOf(PropTypes.string),
  onEdit: PropTypes.func,
  onDelete: PropTypes.func,
};

export default function Table({ columns, rows, searchableFields = [], onEdit, onDelete }) {
  const [data, setData] = useState(rows);
  const [search, setSearch] = useState('');

  const handleSearch = (e) => {
    const value = e.target.value.toLowerCase();
    setSearch(value);

    // Ensure rows are defined and not empty before filtering
    if (!rows || rows.length === 0) return;

    const fieldsToSearch =
      searchableFields.length > 0 ? searchableFields : Object.keys(rows[0] || {});

    const filteredData = rows.filter((row) =>
      fieldsToSearch.some((field) => {
        const fieldValue = row[field] && row[field].toString().toLowerCase();
        return fieldValue && fieldValue.includes(value);
      })
    );

    setData(filteredData);
  };

  const actionColumn = {
    name: 'Actions',
    cell: (row) => (
      <Dropdown size='sm' inline label='Chỉnh sửa' dismissOnClick={false}>
        <Dropdown.Item onClick={() => onEdit(row)}>Cập nhật</Dropdown.Item>
        <Dropdown.Item onClick={() => onDelete(row)}>Xóa</Dropdown.Item>
      </Dropdown>
    ),
    ignoreRowClick: true,
    allowoverflow: true,
  };

  return (
    <>
      <div className='flex flex-row-reverse'>
        <TextInput
          type='search'
          value={search}
          onChange={handleSearch}
          placeholder='Tìm kiếm...'
          className='mb-4'
        />
      </div>
      <div>
        <DataTable
          columns={[...columns, actionColumn]}
          data={data}
          searchableFields={['name', 'email']}
          fixedHeader
          pagination
          striped
        />
      </div>
    </>
  );
}
