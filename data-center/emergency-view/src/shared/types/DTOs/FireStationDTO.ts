import {Coords} from './Coords';

export interface FireStationDTO {
  id?: number,
  coords: Coords,
  name: string,
  address?: string
}
