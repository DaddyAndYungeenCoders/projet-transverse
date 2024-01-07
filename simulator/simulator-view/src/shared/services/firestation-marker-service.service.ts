import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import { IconDefinition, faHome } from '@fortawesome/free-solid-svg-icons';
import { IconMarkerTypes } from '../types/enum/IconType';
import { Coordinates } from '../types/interfaces/Coordinates';
import { Map } from 'leaflet';
import { FireStationMarkerType } from '../types/interfaces/MarkersTypes';
import {Coords} from '../types/DTOs/Coords';

@Injectable({
  providedIn: 'root',
})
export class FirestationMarkerService extends AbstractMarkerService<FireStationMarkerType> {
  constructor() {
    super();
  }

  override getObjectInfo(): FireStationMarkerType {
    return {
      name: 'FireStation',
      color: 'Blue',
    };
  }

  override fetchAll() {
    console.log("FETCH ALL");
  }
}
