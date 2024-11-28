import apiClient from '../../apiClient';
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { EmployeeBus } from '@type/model/EmployeeBus';


/**
 * Cập nhật liên kết giữa bus và employee
 * @param busId - ID của bus
 * @param driverId - ID của employee (driver)
 * @returns Promise<EmployeeDTO> - Đối tượng EmployeeDTO phản hồi từ server
 */
export const updateEmployeeBus = async (busId: number, driverId: number): Promise<EmployeeBus> => {
    return apiRequest(async () => {
      const response = await apiClient.put<ApiResponse<EmployeeBus>>('/update/bus_and_employee', null, {
        params: { busId, driverId },
      });
      return response.data.data!;
    });
  };


// export const updateEmployeeBus = async (employeeBus: Partial<EmployeeBus>): Promise<EmployeeBus> => {
//     return apiRequest(async () => {
//       const response = await apiClient.put<ApiResponse<EmployeeBus>>('/update/bus_and_employee', employeeBus);
//       return response.data.data!;
//     });
//   };



export const deleteEmployeeBus = async (busId: number, driverId: number): Promise<EmployeeBus> => {
    return apiRequest(async () => {
      const response = await apiClient.delete<ApiResponse<EmployeeBus>>('/delete/bus_and_employee', {
        params: { busId, driverId },
      });
      return response.data.data!;
    });
  };

  

//   export const deleteEmployeeBus = async (employeeBus: number): Promise<EmployeeBus> => {
//     return apiRequest(async () => {
//       const response = await apiClient.delete<ApiResponse<EmployeeBus>>(`/delete/bus_and_employee/${employeeBus}`);
//       return response.data.data!;
//     });
//   };