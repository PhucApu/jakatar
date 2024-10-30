import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import MenuInfoUser from "../../components/MenuInfoUser";
import * as React from "react";
import Button from "@mui/material/Button";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import PopupState, { bindTrigger, bindMenu } from "material-ui-popup-state";
import { DateRangePicker } from "@nextui-org/react";
import { parseDate } from "@internationalized/date";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";

const StyledTableCell = styled(TableCell)(() => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: "#0e7490",
    color: "#fff",
    fontWeight: "bold",
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
    color: "#333",
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

function createData(name, calories, fat, carbs, protein) {
  return { name, calories, fat, carbs, protein };
}

const rows = [
  createData(
    "BX100001",
    120000,
    "Đặt vé tuyến Sài Gòn - Đà Lạt",
    "2024-10-01 08:30",
    "Thành công"
  ),
  createData(
    "BX100002",
    100000,
    "Đặt vé tuyến Hà Nội - Hải Phòng",
    "2024-10-02 14:45",
    "Thành công"
  ),
  createData(
    "BX100003",
    150000,
    "Đặt vé tuyến Sài Gòn - Nha Trang",
    "2024-10-03 10:15",
    "Thất bại"
  ),
  createData(
    "BX100004",
    80000,
    "Đặt vé tuyến Cần Thơ - Cà Mau",
    "2024-10-04 17:50",
    "Thành công"
  ),
  createData(
    "BX100005",
    200000,
    "Đặt vé tuyến Hà Nội - Sapa",
    "2024-10-05 09:20",
    "Thành công"
  ),
  createData(
    "BX100006",
    180000,
    "Đặt vé tuyến Đà Nẵng - Huế",
    "2024-10-06 13:35",
    "Đang xử lý"
  ),
  createData(
    "BX100007",
    75000,
    "Đặt vé tuyến Hải Phòng - Hạ Long",
    "2024-10-07 07:55",
    "Thành công"
  ),
  createData(
    "BX100008",
    120000,
    "Đặt vé tuyến Sài Gòn - Vũng Tàu",
    "2024-10-08 11:10",
    "Thất bại"
  ),
  createData(
    "BX100009",
    150000,
    "Đặt vé tuyến Đà Nẵng - Quảng Ngãi",
    "2024-10-09 16:40",
    "Thành công"
  ),
];

export default function AnhBaPay() {
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
              Số dư ví <h1>10 đ</h1>
            </div>
            <div className="ml-auto flex items-center gap-2">
              <FontAwesomeIcon
                icon={faClockRotateLeft}
                style={{ color: "#74C0FC" }}
                className="mr-2"
              />
              <h2>Giao dịch</h2>
            </div>
          </div>

          <div className="flex flex-col gap-4">
            <h1 className="font-bold">Lịch sử giao dịch</h1>

            {/* Filter Options */}
            <div className="flex justify-between border rounded-2xl">
              <div className="w-1/3 p-4 text-center">
                <DateRangePicker
                  label="Stay duration"
                  isRequired
                  defaultValue={{
                    start: parseDate("2024-04-01"),
                    end: parseDate("2024-04-08"),
                  }}
                  className="max-w-xs rounded-2xl bg-teal-100"
                />
              </div>
              <div className="w-1/3 p-4 text-center">
                <PopupState variant="popover" popupId="demo-popup-menu">
                  {(popupState) => (
                    <React.Fragment>
                      <Button variant="contained" {...bindTrigger(popupState)}>
                        Chọn trạng thái...
                      </Button>
                      <Menu {...bindMenu(popupState)}>
                        <MenuItem onClick={popupState.close}>Khởi tạo</MenuItem>
                        <MenuItem onClick={popupState.close}>
                          Chờ duyệt
                        </MenuItem>
                        <MenuItem onClick={popupState.close}>Hủy bỏ</MenuItem>
                        <MenuItem onClick={popupState.close}>Đã duyệt</MenuItem>
                      </Menu>
                    </React.Fragment>
                  )}
                </PopupState>
              </div>
              <div className="w-1/3 p-4 text-center">
                <button className="bg-teal-600 text-white hover:bg-teal-800 px-4 py-2 rounded">
                  Lọc
                </button>
              </div>
            </div>

            {/* Transaction Table */}
            <div>
              <TableContainer component={Paper}>
                <Table sx={{ minWidth: 700 }} aria-label="customized table">
                  <TableHead>
                    <TableRow>
                      <StyledTableCell className="text-teal-600">
                        Mã giao dịch
                      </StyledTableCell>
                      <StyledTableCell align="right">Số tiền</StyledTableCell>
                      <StyledTableCell align="right">Nội dung</StyledTableCell>
                      <StyledTableCell align="right">Thời gian</StyledTableCell>
                      <StyledTableCell align="right">
                        Trạng thái
                      </StyledTableCell>
                    </TableRow>
                  </TableHead>
                  <TableBody>
                    {rows.map((row) => (
                      <StyledTableRow key={row.name}>
                        <StyledTableCell component="th" scope="row">
                          {row.name}
                        </StyledTableCell>
                        <StyledTableCell align="right">
                          {row.calories}
                        </StyledTableCell>
                        <StyledTableCell align="right">
                          {row.fat}
                        </StyledTableCell>
                        <StyledTableCell align="right">
                          {row.carbs}
                        </StyledTableCell>
                        <StyledTableCell align="right">
                          {row.protein}
                        </StyledTableCell>
                      </StyledTableRow>
                    ))}
                  </TableBody>
                </Table>
              </TableContainer>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
