import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import { IconDefinition, faHome } from '@fortawesome/free-solid-svg-icons';
import { IconMarkerTypes } from '../types/enum/IconType';
import { Coordinates } from '../types/interfaces/Coordinates';
import { Map } from 'leaflet';

type FireStationMarker = {
  name: string;
  color: string;
};
@Injectable({
  providedIn: 'root',
})
export class FirestationMarkerService extends AbstractMarkerService<FireStationMarker> {
  constructor() {
    super();
  }

  getIconMarker(type: IconMarkerTypes): IconDefinition {
    return faHome;
  }
  getObjectInfo(): FireStationMarker {
    return {
      name: 'FireStation',
      color: 'Blue',
    };
  }
  override createMarkers(
    coords: Coordinates[],
    map: Map,
    type: IconMarkerTypes,
    color: string
  ): void {
    super.createMarkers(coords, map, type, color);
  }
}
