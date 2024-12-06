import { Schedule } from '@type/model/Schedule';
import apiClient from '../../apiClient';
import { apiRequest } from '../apiRequest';

export const searchBuses = async (departure: string, destination: string): Promise<any> => {
  return apiRequest(async () => {
    const response = await apiClient.get<Schedule[]>(
      `/bus_routes_schedule/departureLocation_destinationLocation?departureLocation=${departure}&destinationLocation=${destination}`
    );
    return response.data || [];
  });
};
