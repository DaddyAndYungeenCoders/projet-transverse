import {Coords} from './Coords';

export interface InterventionDTO {
  id: number;
  chiefOfficer: string;
  stamina: number; // out of 10
  idFirestation: number;
  coords: Coords;
  isAvailable: boolean;
}
