import apiClient from "../../apiClient";
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { Discount } from '@type/model/Discount';

export const getDiscounts = async (): Promise<ApiResponse<Discount[]>> => {
  return apiRequest(async () => {
    const response = await apiClient.get('/discounts');
    return response.data || [];
  });
};

export const getDiscountById = async (discountId: string): Promise<ApiResponse<Discount>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/discounts/${discountId}`);
    return response.data;
  });
};

export const createDiscount = async (discount: Partial<Discount>): Promise<ApiResponse<Discount>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/discounts/insert', discount);
    return response.data;
  });
};

export const updateDiscount = async (discount: Partial<Discount>): Promise<ApiResponse<Discount>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/discounts/update', discount);
    return response.data;
  });
};

export const deleteDiscount = async (discountId: string): Promise<ApiResponse<Discount>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/discounts/delete/${discountId}`);
    return response.data;
  });
};

export const hideDiscount = async (discountId: string): Promise<ApiResponse<Discount>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/discounts/hidden/${discountId}`);
    return response.data;
  });
};
