import apiClient from "../../apiClient";
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { BusRoute } from '@type/model/BusRoute';

export const getBusRoutes = async (): Promise<ApiResponse<BusRoute[]>> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<BusRoute[]>>('/busroutes');
    return response.data || [];
  });
};

export const getBusRouteById = async (busRouteId: string): Promise<ApiResponse<BusRoute>> => {
  return apiRequest(async () => {
    const response = await apiClient.get(`/busroutes/${busRouteId}`);
    return response.data;
  });
};

export const createBusRoute = async (busRoute: Partial<BusRoute>): Promise<ApiResponse<BusRoute>> => {
  return apiRequest(async () => {
    const response = await apiClient.post('/busroutes/insert', busRoute);
    return response.data;
  });
};

export const updateBusRoute = async (busRoute: Partial<BusRoute>): Promise<ApiResponse<BusRoute>> => {
  return apiRequest(async () => {
    const response = await apiClient.put('/busroutes/update', busRoute);
    return response.data;
  });
};

export const deleteBusRoute = async (busRouteId: string): Promise<ApiResponse<BusRoute>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/busroutes/delete/${busRouteId}`);
    return response.data;
  });
};

export const hideBusRoute = async (busRouteId: string): Promise<ApiResponse<BusRoute>> => {
  return apiRequest(async () => {
    const response = await apiClient.delete(`/busroutes/hidden/${busRouteId}`);
    return response.data;
  });
};
