import apiClient from "../../apiClient";
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { BusRoute } from '@type/model/BusRoute';

export const getBusRoutes = async (): Promise<BusRoute[]> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<BusRoute[]>>('/buses/routes');
    return response.data.data || [];
  });
};

export const getBusRouteById = async (busRouteId: string): Promise<BusRoute> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<BusRoute>>(`/buses/routes/${busRouteId}`);
    return response.data.data!;
  });
};

export const createBusRoute = async (busRoute: Partial<BusRoute>): Promise<BusRoute> => {
  return apiRequest(async () => {
    const response = await apiClient.post<ApiResponse<BusRoute>>('/buses/routes/insert', busRoute);
    return response.data.data!;
  });
};

export const updateBusRoute = async (busRoute: Partial<BusRoute>): Promise<BusRoute> => {
  return apiRequest(async () => {
    const response = await apiClient.put<ApiResponse<BusRoute>>('/buses/routes/update', busRoute);
    return response.data.data!;
  });
};

export const deleteBusRoute = async (busRouteId: string): Promise<BusRoute> => {
  return apiRequest(async () => {
    const response = await apiClient.delete<ApiResponse<BusRoute>>(`/buses/routes/${busRouteId}`);
    return response.data.data!;
  });
};

export const hideBusRoute = async (busRouteId: string): Promise<BusRoute> => {
  return apiRequest(async () => {
    const response = await apiClient.delete<ApiResponse<BusRoute>>(`/buses/routes/hidden/${busRouteId}`);
    return response.data.data!;
  });
};
