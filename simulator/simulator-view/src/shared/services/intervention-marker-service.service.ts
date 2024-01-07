import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import {
  IconDefinition,
  faWaterLadder,
} from '@fortawesome/free-solid-svg-icons';
import { IconMarkerTypes } from '../types/enum/IconType';
import { Coordinates } from '../types/interfaces/Coordinates';
import { Map } from 'leaflet';
import { InterventionMarkerType } from '../types/interfaces/MarkersTypes';
import {Coords} from '../types/DTOs/Coords';

type InterventionMarker = {
  name: string;
  color: string;
};
@Injectable({
  providedIn: 'root',
})
export class InterventionMarkerService extends AbstractMarkerService<InterventionMarkerType> {
  constructor() {
    super();
  }

  getObjectInfo(): InterventionMarkerType {
    return {
      name: 'Intervention',
      color: 'Black',
    };
  }

  override fetchAll() {
    console.log("FETCH ALL");
  }
}
