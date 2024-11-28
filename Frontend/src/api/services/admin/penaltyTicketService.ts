import apiClient from "../../apiClient";
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { PenaltyTicket } from '@type/model/PenaltyTicket';

export const getPenaltyTickets = async (): Promise<ApiResponse<PenaltyTicket[]>> => {
  return apiRequest(async () => {
    const response = await apiClient.get('/penaltytickets');
    return response.data || [];
  });
};

export const getPenaltyTicketById = async (penaltyTicketId: string): Promise<ApiResponse<PenaltyTicket>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/penaltytickets/${penaltyTicketId}`);
    return response.data;
  });
};

export const createPenaltyTicket = async (penaltyTicket: Partial<PenaltyTicket>): Promise<ApiResponse<PenaltyTicket>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/penaltytickets/insert', penaltyTicket);
    return response.data;
  });
};

export const updatePenaltyTicket = async (penaltyTicket: Partial<PenaltyTicket>): Promise<ApiResponse<PenaltyTicket>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/penaltytickets/update', penaltyTicket);
    return response.data;
  });
};

export const deletePenaltyTicket = async (penaltyTicketId: string): Promise<ApiResponse<PenaltyTicket>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/penaltytickets/delete/${penaltyTicketId}`);
    return response.data;
  });
};

export const hidePenaltyTicket = async (penaltyTicketId: string): Promise<ApiResponse<PenaltyTicket>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/penaltytickets/hidden/${penaltyTicketId}`);
    return response.data;
  });
};
