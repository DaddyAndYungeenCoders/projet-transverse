import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import { IconDefinition, faFire } from '@fortawesome/free-solid-svg-icons';
import { IconMarkerTypes } from '../types/enum/IconType';
import { Map } from 'leaflet';
import { Coordinates } from '../types/interfaces/Coordinates';

type FireMarker = {
  name: string;
  color: string;
};

@Injectable({
  providedIn: 'root',
})
export class FireMarkerService extends AbstractMarkerService<FireMarker> {
  constructor() {
    super();
  }

  getIconMarker(type: IconMarkerTypes): IconDefinition {
    return faFire;
  }
  getObjectInfo(): FireMarker {
    return {
      name: 'Fire',
      color: 'DeepRed',
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
