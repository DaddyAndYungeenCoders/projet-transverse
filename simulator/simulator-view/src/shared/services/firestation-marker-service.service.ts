import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import { Map } from 'leaflet';
import { FireStationMarkerType } from '../types/interfaces/MarkersTypes';

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

  override fetchAll(map: Map) {
    console.log("FETCH ALL");
  }
}
