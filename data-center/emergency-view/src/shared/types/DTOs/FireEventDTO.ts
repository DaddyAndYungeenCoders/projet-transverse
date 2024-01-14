import {Coords} from './Coords';

export interface FireEventDTO {
  id?: number,
  coords: Coords,
  intensity: number,
  startDate: Date,
  endDate: Date | null,
  sensorId: number,
  real: boolean,
  isVerified: boolean,
  is_handled: boolean
}
