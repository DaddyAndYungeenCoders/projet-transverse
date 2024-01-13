import { Coords } from '../DTOs/Coords';
import { IconMarkerTypes } from '../enum/IconType';

export interface MarkerParameter {
  coords: Coords;
  type: IconMarkerTypes;
  color: string;
  intensity?: number;
  height?: number;
}

export interface MarkersTypes {
  markerType?: string;
  name: string;
  color: string;
}

export interface FireMarkerType extends MarkersTypes {
  markerType?: 'fireMarkerType';
  size: number;
}

export interface FireStationMarkerType extends MarkersTypes {
  markerType?: 'fireStationMarkerType';
}

export interface InterventionMarkerType extends MarkersTypes {
  markerType?: 'interventionMarkerType';
}

export interface SensorMarkerType extends MarkersTypes {
  markerType?: 'sensorMarkerType';
}
