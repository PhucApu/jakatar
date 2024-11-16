import apiClient from "../../apiClient";

import { ApiResponse } from "@type/common/ApiResponse";
import { Ticket } from "@type/model/Ticket";

export const getTickets = async (): Promise<Ticket[]> => {
  const response = await apiClient.get<ApiResponse<Ticket[]>>('/tickets');
  return response.data.data || [];
};

export const getTicketById = async (ticketId: string): Promise<Ticket> => {
  const response = await apiClient.get<ApiResponse<Ticket>>(`/tickets/${ticketId}`);
  return response.data.data!;
};

export const createTicket = async (ticket: Partial<Ticket>): Promise<Ticket> => {
  const response = await apiClient.post<ApiResponse<Ticket>>('/tickets/insert', ticket);
  return response.data.data!;
};

export const updateTicket = async (ticket: Partial<Ticket>): Promise<Ticket> => {
  const response = await apiClient.put<ApiResponse<Ticket>>('/tickets/update', ticket);
  return response.data.data!;
};

export const deleteTicket = async (ticketId: string): Promise<Ticket> => {
  const response = await apiClient.delete<ApiResponse<Ticket>>(`/tickets/${ticketId}`);
  return response.data.data!;
};

export const hideTicket = async (ticketId: string): Promise<Ticket> => {
  const response = await apiClient.delete<ApiResponse<Ticket>>(`/tickets/hidden/${ticketId}`);
  return response.data.data!;
}