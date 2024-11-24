import apiClient from '../../apiClient';
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { Bus } from '@type/model/Bus';

export const getBuses = async (): Promise<ApiResponse<Bus[]>> => {
  return apiRequest(async () => {
    const response = await apiClient.get('/buses');
    return response.data || [];
  });
};

export const getBusById = async (busId: string): Promise<ApiResponse<Bus>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/buses/${busId}`);
    return response.data;
  });
};

export const createBus = async (bus: Partial<Bus>): Promise<ApiResponse<Bus>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/buses/insert', bus);
    return response.data;
  });
};

export const updateBus = async (bus: Partial<Bus>): Promise<ApiResponse<Bus>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/buses/update', bus);
    return response.data;
  });
};

export const deleteBus = async (busId: string): Promise<ApiResponse<Bus>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/buses/${busId}`);
    return response.data;
  });
};

export const hideBus = async (busId: string): Promise<ApiResponse<Bus>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/buses/hidden/${busId}`);
    return response.data;
  });
};
