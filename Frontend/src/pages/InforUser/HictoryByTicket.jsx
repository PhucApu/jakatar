import MenuInfoUser from "../../components/MenuInfoUser";
import Paper from "@mui/material/Paper";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TablePagination from "@mui/material/TablePagination";
import TableRow from "@mui/material/TableRow";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const columns = [
  { id: "code", label: "Mã vé", minWidth: 100 },
  { id: "numberof", label: "Số vé", minWidth: 100 },
  { id: "router", label: "Tuyến đường", minWidth: 170 },
  { id: "date", label: "Ngày đi", minWidth: 170 },
  {
    id: "money",
    label: "Số tiền",
    minWidth: 100,
    format: (value) =>
      value.toLocaleString("vi-VN", { style: "currency", currency: "VND" }),
  },
  { id: "payment", label: "Thanh toán", minWidth: 100 },
  { id: "status", label: "Trạng thái", minWidth: 100 },
  { id: "action", label: "Thao tác", minWidth: 100 },
];

// Cập nhật hàm tạo dữ liệu
function createData(
  code,
  numberof,
  router,
  date,
  money,
  payment,
  status,
  action
) {
  return { code, numberof, router, date, money, payment, status, action };
}

// Cập nhật giá trị thanh toán trong mảng rows
const rows = [
  createData(
    "V001",
    2,
    "Hà Nội - Hải Phòng",
    "2024-10-30",
    300000,
    "ZaloPay",
    "Đã thanh toán",
    "Xem"
  ),
  createData(
    "V002",
    1,
    "Hà Nội - Đà Nẵng",
    "2024-10-31",
    800000,
    "Chuyển khoản",
    "Đã thanh toán",
    "Xem"
  ),
  createData(
    "V003",
    3,
    "Hà Nội - TP.HCM",
    "2024-11-01",
    1500000,
    "AnhBaPay",
    "Chưa thanh toán",
    "Xem"
  ),
];
export default function HictoryByTicket() {
  const navigate = useNavigate();
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };
  return (
    <section className="flex justify-center items-center p-6 mt-10">
      <div className="flex w-full max-w-5xl">
        {/* Left Side: Menu */}
        <div className="w-1/4">
          <MenuInfoUser />
        </div>

        {/* Right Side: Main Content */}
        <div className="w-3/4 pl-8">
          <div className="flex bg-teal-100 p-4 rounded-2xl mb-7 justify-between">
            <div>
              <h1 className="font-bold">Lịch sử mua vé</h1>
              <h2>Theo dõi và quản lý quá trình lịch sử mua vé của bạn</h2>
            </div>
            <div className="bg-teal-600 hover:bg-teal-800 rounded-2xl">
              <button
                className="m-4 px-7 text-white"
                onClick={() => navigate("/")}
              >
                Đặt vé
              </button>
            </div>
          </div>

          <div className="flex flex-col gap-4">
            <h1 className="font-bold">Lịch sử giao dịch</h1>

            {/* Filter Options */}
            <div className="flex border rounded-2xl m-2 justify-between">
              <div className="w-1/5 p-4">
                {" "}
                Mã vé
                <input type="" className="w-full border rounded px-4 py-1" />
              </div>
              <div className="w-1/5 p-4 ">
                {" "}
                Thời gian
                <input
                  type="date"
                  className="w-full border rounded px-5 py-1"
                />
              </div>
              <div className="w-1/5 p-4 ">
                {" "}
                Tuyến đường
                <input type="" className="w-full border rounded px-4 py-1" />
              </div>
              <div className="w-1/5 p-4 ">
                {" "}
                Trạng thái
                <input type="" className="w-full border rounded px-4 py-1" />
              </div>
              <div className="w-1/5 p-4 ">
                <button className="bg-teal-600 text-white hover:bg-teal-800 px-4 py-2 rounded-2xl mt-3">
                  Tìm
                </button>
              </div>
            </div>

            {/* Transaction Table */}
            <div className="">
              <Paper sx={{ width: "100%", overflow: "hidden" }}>
                <TableContainer sx={{ maxHeight: 440 }}>
                  <Table stickyHeader aria-label="sticky table">
                    <TableHead>
                      <TableRow>
                        {columns.map((column) => (
                          <TableCell
                            key={column.id}
                            align={column.align || "left"}
                            style={{ minWidth: column.minWidth }}
                          >
                            {column.label}
                          </TableCell>
                        ))}
                      </TableRow>
                    </TableHead>
                    <TableBody>
                      {rows
                        .slice(
                          page * rowsPerPage,
                          page * rowsPerPage + rowsPerPage
                        )
                        .map((row) => (
                          <TableRow
                            hover
                            role="checkbox"
                            tabIndex={-1}
                            key={row.code}
                          >
                            {columns.map((column) => {
                              const value = row[column.id];
                              return (
                                <TableCell
                                  key={column.id}
                                  align={column.align || "left"}
                                >
                                  {column.format && typeof value === "number"
                                    ? column.format(value)
                                    : value}
                                </TableCell>
                              );
                            })}
                          </TableRow>
                        ))}
                    </TableBody>
                  </Table>
                </TableContainer>
                <TablePagination
                  rowsPerPageOptions={[10, 25, 100]}
                  component="div"
                  count={rows.length}
                  rowsPerPage={rowsPerPage}
                  page={page}
                  onPageChange={handleChangePage}
                  onRowsPerPageChange={handleChangeRowsPerPage}
                />
              </Paper>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
