import apiClient from "../../apiClient";

import { ApiResponse } from '@type/common/ApiResponse';
import { Feedback } from "@type/model/Feedback";

export const getFeedbacks = async (): Promise<Feedback[]> => {
  const response = await apiClient.get<ApiResponse<Feedback[]>>('/feedbacks');
  return response.data.data || [];
};

export const getFeedbackById = async (feedbackId: string): Promise<Feedback> => {
  const response = await apiClient.get<ApiResponse<Feedback>>(`/feedbacks/${feedbackId}`);
  return response.data.data!;
};

export const createFeedback = async (feedback: Partial<Feedback>): Promise<Feedback> => {
  const response = await apiClient.post<ApiResponse<Feedback>>('/feedbacks/insert', feedback);
  return response.data.data!;
};

export const updateFeedback = async (feedback: Partial<Feedback>): Promise<Feedback> => {
  const response = await apiClient.put<ApiResponse<Feedback>>('/feedbacks/update', feedback);
  return response.data.data!;
};

export const deleteFeedback = async (feedbackId: string): Promise<Feedback> => {
  const response = await apiClient.delete<ApiResponse<Feedback>>(`/feedbacks/${feedbackId}`);
  return response.data.data!;
};

export const hideFeedback = async (feedbackId: string): Promise<Feedback> => {
  const response = await apiClient.delete<ApiResponse<Feedback>>(`/feedbacks/hidden/${feedbackId}`);
  return response.data.data!;
}