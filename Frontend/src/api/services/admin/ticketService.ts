import apiClient from "../../apiClient";

import { apiRequest } from '../apiRequest'; // Import centralized API middleware
import { ApiResponse } from '@type/common/ApiResponse';
import { Ticket } from '@type/model/Ticket';

export const getTickets = async (): Promise<Ticket[]> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<Ticket[]>>('/tickets');
    return response.data.data || [];
  });
};

export const getTicketById = async (ticketId: number): Promise<Ticket> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<Ticket>>(`/tickets/${ticketId}`);
    return response.data.data!;
  });
};

export const createTicket = async (ticket: Partial<Ticket>): Promise<Ticket> => {
  return apiRequest(async () => {
    const response = await apiClient.post<ApiResponse<Ticket>>('/tickets/insert', ticket);
    return response.data.data!;
  });
};

export const updateTicket = async (ticket: Partial<Ticket>): Promise<Ticket> => {
  return apiRequest(async () => {
    const response = await apiClient.put<ApiResponse<Ticket>>('/tickets/update', ticket);
    return response.data.data!;
  });
};

export const deleteTicket = async (ticketId: number): Promise<Ticket> => {
  return apiRequest(async () => {
    const response = await apiClient.delete<ApiResponse<Ticket>>(`/tickets/delete/${ticketId}`);
    return response.data.data!;
  });
};

export const hideTicket = async (ticketId: number): Promise<Ticket> => {
  return apiRequest(async () => {
    const response = await apiClient.delete<ApiResponse<Ticket>>(`/tickets/hidden/${ticketId}`);
    return response.data.data!;
  });
};


// lấy ticket theo ticketId và userName
export const getTicketByIdAndUserName = async (
  ticketId: number,
  username: string
): Promise<Ticket> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<Ticket>>('/tickets/ticket_by_ticketId_username', {
      params: {
        ticketId,
        username,
      },
    });
    console.log(">>DATA110000:",response.data.data);
    // return response.data.data!;
    return response.data.data || {};
  });
};


// lấy danh sách ticket theo dateA đến dateB
export const getListTicketDateAToDateB = async (
  dateA: string,
  dateB: string
): Promise<Ticket> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<Ticket>>('/tickets/statistics', {
      params: {
        dateA,
        dateB,
      },
    });
    // console.log(">>DATA2:",response.data.data);
    // // return response.data.data!;
    // console.log(">>DATA3:",response.data.data)
    return response.data.data || {};
  });
};


// lấy danh sách ticket theo dateA đến dateB and UserName
export const getListTicketDateAToDateBAndUser = async (
  username: string,
  dateA: string,
  dateB: string
): Promise<Ticket> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<Ticket>>('/tickets/ticket_by_date_username', {
      params: {
        username,
        dateA,
        dateB,
      },
    });
    // console.log(">>DATA10:",response.data.data);
    // return response.data.data!;
    
    return response.data.data || {};
  });
};
