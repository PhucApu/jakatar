import apiClient from "../../apiClient";
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { Employee } from '@type/model/Employee';

export const getEmployees = async (): Promise<ApiResponse<Employee[]>> => {
  return apiRequest(async () => {
    const response = await apiClient.get('/employees');
    return response.data || [];
  });
};

export const getEmployeeById = async (employeeId: string): Promise<ApiResponse<Employee>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/employees/${employeeId}`);
    return response.data;
  });
};

export const createEmployee = async (employee: Partial<Employee>): Promise<ApiResponse<Employee>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/employees/insert', employee);
    return response.data;
  });
};

export const updateEmployee = async (employee: Partial<Employee>): Promise<ApiResponse<Employee>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/employees/update', employee);
    return response.data;
  });
};

export const deleteEmployee = async (employeeId: string): Promise<ApiResponse<Employee>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/employees/${employeeId}`);
    return response.data;
  });
};

export const hideEmployee = async (employeeId: string): Promise<ApiResponse<Employee>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/employees/hidden/${employeeId}`);
    return response.data;
  });
};
