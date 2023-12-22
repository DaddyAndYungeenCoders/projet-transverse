import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import {
  IconDefinition,
  faWaterLadder,
} from '@fortawesome/free-solid-svg-icons';
import { IconMarkerTypes } from '../types/enum/IconType';
import { Coordinates } from '../types/interfaces/Coordinates';
import { Map } from 'leaflet';

type InterventionMarker = {
  name: string;
  color: string;
};
@Injectable({
  providedIn: 'root',
})
export class InterventionMarkerService extends AbstractMarkerService<InterventionMarker> {
  constructor() {
    super();
  }

  getIconMarker(type: IconMarkerTypes): IconDefinition {
    return faWaterLadder;
  }
  getObjectInfo(): InterventionMarker {
    return {
      name: 'Intervention',
      color: 'Black',
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
