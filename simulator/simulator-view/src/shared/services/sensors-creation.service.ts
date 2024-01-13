import { EventEmitter, Injectable } from '@angular/core';
import * as L from 'leaflet';
import { RealFireEventTypeDTO } from '../types/implementations/RealFireEventTypeDTO';
import { AbstractFireEventTypes } from '../types/implementations/AbstractFireEventTypesImpl';
import { HttpClient } from '@angular/common/http';
import { WEBSERVER_PORT } from '../types/constants/shared-constants';
import { SensorDTO } from '../types/DTOs/SensorDTO';

@Injectable({
  providedIn: 'root',
})
export class SensorsCreationService {
  private BASE_URL = `http://localhost:${WEBSERVER_PORT}/api/sensor`;

  constructor(private _http: HttpClient) {}

  create(sensors: SensorDTO[]): void {
    sensors.forEach((sensor) => {
      this._http
        .post<SensorDTO>(`${this.BASE_URL}/create`, sensor)
        .subscribe((response) => {
          console.info('A sensor has been created', response);
        });
    });
  }
}
