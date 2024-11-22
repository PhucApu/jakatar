import apiClient from '../../apiClient';
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { Bus } from '@type/model/Bus';

export const getBuses = async (): Promise<Bus[]> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<Bus[]>>('/buses');
    return response.data.data || [];
  });
};

export const getBusById = async (busId: string): Promise<Bus> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<Bus>>(`/buses/${busId}`);
    return response.data.data!;
  });
};

export const createBus = async (bus: Partial<Bus>): Promise<Bus> => {
  return apiRequest(async () => {
    const response = await apiClient.post<ApiResponse<Bus>>('/buses/insert', bus);
    return response.data.data!;
  });
};

export const updateBus = async (bus: Partial<Bus>): Promise<Bus> => {
  return apiRequest(async () => {
    const response = await apiClient.put<ApiResponse<Bus>>('/buses/update', bus);
    return response.data.data!;
  });
};

export const deleteBus = async (busId: string): Promise<Bus> => {
  return apiRequest(async () => {
    const response = await apiClient.delete<ApiResponse<Bus>>(`/buses/${busId}`);
    return response.data.data!;
  });
};

export const hideBus = async (busId: string): Promise<Bus> => {
  return apiRequest(async () => {
    const response = await apiClient.delete<ApiResponse<Bus>>(`/buses/hidden/${busId}`);
    return response.data.data!;
  });
};
