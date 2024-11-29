import apiClient from '../../apiClient';
import { apiRequest } from '../apiRequest';

import { ApiResponse } from '@type/common/ApiResponse';
import { StatisticResult } from '@type/model/StatisticResult';


/**
 * Fetches ticket statistics between two dates
 * @param dateA Start date in ISO string format
 * @param dateB End date in ISO string format
 * @returns Promise of statistics result
 */
export const getStatisticsTickets = async (
  dateA: string,
  dateB: string
): Promise<StatisticResult> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<StatisticResult>>('/statistics', {
      params: {
        dateA,
        dateB,
      },
    });
    return response.data.data || {};
  });
};

/**
 * Fetches penalty ticket statistics between two dates
 * @param dateA Start date in ISO string format
 * @param dateB End date in ISO string format
 * @returns Promise of penalty statistics result
 */
export const getStatisticsPenaltyTickets = async (
  dateA: string,
  dateB: string
): Promise<StatisticResult> => {
  return apiRequest(async () => {
    const response = await apiClient.get<ApiResponse<StatisticResult>>('/statistics', {
      params: {
        dateA,
        dateB,
      },
    });
    return response.data.data || {};
  });
};
