export interface BusRoute {
  routesId: number;
  departureLocation: string;
  destinationLocation: string;
  distanceKilometer: number;
  departureTime: string;
  arivalTime: string;
  price: number;
  isDelete: boolean;
  listBusEntities_Id: number[];
}