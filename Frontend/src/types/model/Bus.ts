export interface Bus {
  busId: number;
  busNumber: string;
  capacity: number;
  brand: string;
  isDelete: boolean;
  listEmployeeEntities_Id: number[];
  listTicketEntities_Id: any[];
  listPenaltyTicketEntities_Id: number[];
}