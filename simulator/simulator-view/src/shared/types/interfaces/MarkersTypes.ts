export interface MarkersTypes {
  name: string;
  color: string;
}

export interface FireMarkerType extends MarkersTypes {
  size: number;
}

export interface FireStationMarkerType extends MarkersTypes {}

export interface InterventionMarkerType extends MarkersTypes {}
