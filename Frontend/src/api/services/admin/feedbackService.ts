import apiClient from "../../apiClient";
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { Feedback } from '@type/model/Feedback';

export const getFeedbacks = async (): Promise<Feedback[]> => {
  return apiRequest(async () => {
    const response = await apiClient.get('/feedbacks');
    return response.data || [];
  });
};

export const getFeedbackById = async (feedbackId: string): Promise<ApiResponse<Feedback>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/feedbacks/${feedbackId}`);
    return response.data;
  });
};

export const createFeedback = async (feedback: Partial<Feedback>): Promise<ApiResponse<Feedback>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/feedbacks/insert', feedback);
    return response.data;
  });
};

export const updateFeedback = async (feedback: Partial<Feedback>): Promise<ApiResponse<Feedback>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/feedbacks/update', feedback);
    return response.data;
  });
};

export const deleteFeedback = async (feedbackId: string): Promise<ApiResponse<Feedback>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/feedbacks/${feedbackId}`);
    return response.data;
  });
};

export const hideFeedback = async (feedbackId: string): Promise<ApiResponse<Feedback>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/feedbacks/hidden/${feedbackId}`);
    return response.data;
  });
};
