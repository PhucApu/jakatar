import apiClient from "../../apiClient";
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { PenaltyTicket } from '@type/model/PenaltyTicket';

export const getPenaltyTickets = async (): Promise<PenaltyTicket[]> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<PenaltyTicket[]>>('/penaltyTickets');
    return response.data.data || [];
  });
};

export const getPenaltyTicketById = async (penaltyTicketId: string): Promise<PenaltyTicket> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<PenaltyTicket>>(`/penaltyTickets/${penaltyTicketId}`);
    return response.data.data!;
  });
};

export const createPenaltyTicket = async (penaltyTicket: Partial<PenaltyTicket>): Promise<PenaltyTicket> => {
  return apiRequest(async () => {
    const response = await apiClient.post<ApiResponse<PenaltyTicket>>('/penaltyTickets/insert', penaltyTicket);
    return response.data.data!;
  });
};

export const updatePenaltyTicket = async (penaltyTicket: Partial<PenaltyTicket>): Promise<PenaltyTicket> => {
  return apiRequest(async () => {
    const response = await apiClient.put<ApiResponse<PenaltyTicket>>('/penaltyTickets/update', penaltyTicket);
    return response.data.data!;
  });
};

export const deletePenaltyTicket = async (penaltyTicketId: string): Promise<PenaltyTicket> => {
  return apiRequest(async () => {
    const response = await apiClient.delete<ApiResponse<PenaltyTicket>>(`/penaltyTickets/${penaltyTicketId}`);
    return response.data.data!;
  });
};

export const hidePenaltyTicket = async (penaltyTicketId: string): Promise<PenaltyTicket> => {
  return apiRequest(async () => {
    const response = await apiClient.delete<ApiResponse<PenaltyTicket>>(`/penaltyTickets/hidden/${penaltyTicketId}`);
    return response.data.data!;
  });
};
