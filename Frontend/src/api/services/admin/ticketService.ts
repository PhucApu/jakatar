import apiClient from "../../apiClient";

import { apiRequest } from '../apiRequest'; // Import centralized API middleware
import { ApiResponse } from '@type/common/ApiResponse';
import { Ticket } from '@type/model/Ticket';

export const getTickets = async (): Promise<ApiResponse<Ticket[]>> => {
  return apiRequest(async () => {
    const response = await apiClient.get('/tickets');
    return response.data || [];
  });
};

export const getTicketById = async (ticketId: string): Promise<ApiResponse<Ticket>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/tickets/${ticketId}`);
    return response.data;
  });
};

export const createTicket = async (ticket: Partial<Ticket>): Promise<ApiResponse<Ticket>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/tickets/insert', ticket);
    return response.data;
  });
};

export const updateTicket = async (ticket: Partial<Ticket>): Promise<ApiResponse<Ticket>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/tickets/update', ticket);
    return response.data;
  });
};

export const deleteTicket = async (ticketId: string): Promise<ApiResponse<Ticket>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/tickets/delete/${ticketId}`);
    return response.data;
  });
};

export const hideTicket = async (ticketId: string): Promise<ApiResponse<Ticket>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/tickets/hidden/${ticketId}`);
    return response.data;
  });
};
