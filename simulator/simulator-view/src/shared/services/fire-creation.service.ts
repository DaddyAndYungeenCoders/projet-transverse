import { EventEmitter, Injectable } from '@angular/core';
import * as L from 'leaflet';
import { RealFireEventTypeDTO } from '../types/implementations/RealFireEventTypeDTO';
import { AbstractFireEventTypes } from '../types/implementations/AbstractFireEventTypesImpl';
import {HttpClient} from '@angular/common/http';
import {WEBSERVER_PORT} from '../types/constants/shared-constants';
import {FireEventDTO} from '../types/DTOs/FireEventDTO';
import {Coords} from '../types/DTOs/Coords';

@Injectable({
  providedIn: 'root',
})
export class FireCreationService {
  $intensity: EventEmitter<number> = new EventEmitter<number>();
  $isInCreationState: EventEmitter<boolean> = new EventEmitter<boolean>();

  intensity: number = 0;
  type: AbstractFireEventTypes = new RealFireEventTypeDTO(0);
  isSettingNewElement: boolean = false;
  private BASE_URL = `http://localhost:${WEBSERVER_PORT}/api/fire-event`;

  constructor(private _http: HttpClient) {}

  increment(): void {
    this.intensity < 10 ? this.intensity++ : this.intensity;
    this.$intensity.emit(this.intensity);
  }

  decrement(): void {
    this.intensity > 0 ? this.intensity-- : this.intensity;
    this.$intensity.emit(this.intensity);
  }

  validate(type: AbstractFireEventTypes): void {
    this.type = type;
    this.type.intensity = this.intensity;
    this.isSettingNewElement = true;
    this.$isInCreationState.emit(this.isSettingNewElement);
  }

  create(event: L.LeafletMouseEvent): void {
    if (this.isSettingNewElement) {
      let coordinates: Coords = {
        longitude: event.latlng.lng,
        latitude: event.latlng.lat
      };

      let newfire: FireEventDTO = {
        coords: coordinates,
        realIntensity: this.type.intensity,
        startDate: new Date(),
        endDate: null,
        real: this.type instanceof RealFireEventTypeDTO
      };

      this._http.post<FireEventDTO>(`${this.BASE_URL}/create`, newfire).subscribe(response => {
        console.info("A fire event has been created", response);
        this.isSettingNewElement = false;
        this.$isInCreationState.emit(this.isSettingNewElement);
      })
    } else {
      return;
    }

    this.isSettingNewElement = false;
  }
}
