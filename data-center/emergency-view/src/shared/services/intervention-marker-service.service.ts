import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import { InterventionMarkerType } from '../types/interfaces/MarkersTypes';
import { Map } from 'leaflet';

@Injectable({
  providedIn: 'root',
})
export class InterventionMarkerService extends AbstractMarkerService<InterventionMarkerType> {
  constructor() {
    super();
  }

  override fetchAll(map: Map) {
    console.log("FETCH ALL");
  }
}
