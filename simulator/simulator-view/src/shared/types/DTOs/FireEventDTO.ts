import {Coords} from './Coords';

export interface FireEventDTO {
  id?: number,
  coords: Coords,
  realIntensity: number,
  startDate: Date,
  endDate: Date | null,
  real: boolean
}
