import {Injectable} from '@angular/core';
import {AbstractMarkerService} from './abstract-marker.service';
import {Map} from 'leaflet';
import {FireStationMarkerType, MarkerParameter} from '../types/interfaces/MarkersTypes';
import {WEBSERVER_PORT} from '../types/constants/shared-constants';
import {IconMarkerTypes} from '../types/enum/IconType';
import {HttpClient} from '@angular/common/http';
import {FireStationDTO} from '../types/DTOs/FireStationDTO';

@Injectable({
  providedIn: 'root',
})
export class FirestationMarkerService extends AbstractMarkerService<FireStationMarkerType> {
  private BASE_URL = `http://localhost:${WEBSERVER_PORT}/api/fire-station/fetch-all`;
  constructor(private _http: HttpClient) {
    super();
  }

  override getObjectInfo(): FireStationMarkerType {
    return {
      name: 'FireStation',
      color: 'Blue',
    };
  }

  override fetchAll(map: Map) {
    let markerParams: MarkerParameter[] = [];

    this._http.get<FireStationDTO[]>(this.BASE_URL).subscribe(
      list => {
        list.forEach(fire => {
          markerParams.push({
            coords: fire.coords,
            type: IconMarkerTypes.FIRESTATION,
            color: 'black',
            intensity: 0
          })
        });
        super.createMarkers(markerParams, map);
      }
    )
  }
}
