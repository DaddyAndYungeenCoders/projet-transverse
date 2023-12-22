import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import { IconDefinition, faFire } from '@fortawesome/free-solid-svg-icons';
import { IconMarkerTypes } from '../types/enum/IconType';
import { Map } from 'leaflet';
import { Coordinates } from '../types/interfaces/Coordinates';
import { FireMarkerType } from '../types/interfaces/MarkersTypes';

@Injectable({
  providedIn: 'root',
})
export class FireMarkerService extends AbstractMarkerService<FireMarkerType> {
  constructor() {
    super();
  }

  getIconMarker(type: IconMarkerTypes): IconDefinition {
    return faFire;
  }
  getObjectInfo(): FireMarkerType {
    return {
      name: 'Fire',
      color: 'DeepRed',
      size: 10,
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
