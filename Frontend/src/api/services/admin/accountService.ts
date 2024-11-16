import apiClient from '../../apiClient';

import { ApiResponse } from '@type/common/ApiResponse';
import { User } from '@type/model/User';

export const getAcounts = async (): Promise<User[]> => {
  const response = await apiClient.get<ApiResponse<User[]>>('/accounts');
  return response.data.data || [];
};

export const getAccountById = async (username: string): Promise<User> => {
  const response = await apiClient.get<ApiResponse<User>>(`/accounts/${username}`);
  return response.data.data!;
};

export const createAccount = async (user: Partial<User>): Promise<User> => {
  const response = await apiClient.post<ApiResponse<User>>('/accounts/insert', user);
  return response.data.data!;
};

export const updateAccount = async (user: Partial<User>): Promise<User> => {
  const response = await apiClient.put<ApiResponse<User>>('/accounts/update', user);
  return response.data.data!;
};

export const deleteAccount = async (username: string): Promise<User> => {
  const response = await apiClient.delete<ApiResponse<User>>(`/accounts/${username}`);
  return response.data.data!;
};

export const hideAccount = async (username: string): Promise<User> => {
  const response = await apiClient.delete<ApiResponse<User>>(`/accounts/hidden/${username}`);
  return response.data.data!;
}