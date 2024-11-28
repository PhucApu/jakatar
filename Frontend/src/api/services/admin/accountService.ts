import apiClient from '../../apiClient';
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { User } from '@type/model/User';

export const getAccounts = async (): Promise<ApiResponse<User[]>> => {
  return apiRequest(async () => {
    const response = await apiClient.get('/accounts');
    return response.data || [];
  });
};

export const getAccountById = async (username: string): Promise<ApiResponse<User>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/accounts/${username}`);
    return response.data;
  });
};

export const createAccount = async (user: Partial<User>): Promise<ApiResponse<User>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/accounts/insert', user);
    return response.data;
  });
};

export const updateAccount = async (user: Partial<User>): Promise<ApiResponse<User>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/accounts/update', user);
    return response.data;
  });
};

export const deleteAccount = async (username: string): Promise<ApiResponse<User>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/accounts/delete/${username}`);
    return response.data;
  });
};

export const hideAccount = async (username: string): Promise<ApiResponse<User>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/accounts/hidden/${username}`);
    return response.data;
  });
};
