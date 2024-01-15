import {Coords} from './Coords';

export interface SensorDTO {
  id?: number,
  coords: Coords,
  intensity: number
}
