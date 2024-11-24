import apiClient from "../../apiClient";
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { Payment } from '@type/model/Payment';

export const getPayments = async (): Promise<ApiResponse<Payment[]>> => {
  return apiRequest(async () => {
    const response = await apiClient.get('/payments');
    return response.data || [];
  });
};

export const getPaymentById = async (paymentId: string): Promise<ApiResponse<Payment>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/payments/${paymentId}`);
    return response.data;
  });
};

export const createPayment = async (payment: Partial<Payment>): Promise<ApiResponse<Payment>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/payments/insert', payment);
    return response.data;
  });
};

export const updatePayment = async (payment: Partial<Payment>): Promise<ApiResponse<Payment>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/payments/update', payment);
    return response.data;
  });
};

export const deletePayment = async (paymentId: string): Promise<ApiResponse<Payment>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/payments/${paymentId}`);
    return response.data;
  });
};

export const hidePayment = async (paymentId: string): Promise<ApiResponse<Payment>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/payments/hidden/${paymentId}`);
    return response.data;
  });
};
