import {AbstractMarkerService} from './abstract-marker.service';
import {MarkerParameter, SensorMarkerType} from '../types/interfaces/MarkersTypes';
import {Injectable} from '@angular/core';
import {Map} from 'leaflet';
import {HttpClient} from '@angular/common/http';
import {SensorDTO} from '../types/DTOs/SensorDTO';
import {WEBSERVER_PORT} from '../types/constants/shared-constants';
import {IconMarkerTypes} from '../types/enum/IconType';

@Injectable({
  providedIn: 'root'
})
export class SensorMarkerService extends AbstractMarkerService<SensorMarkerType> {
  private BASE_URL = `http://localhost:${WEBSERVER_PORT}/api/sensor/fetch-all`;

  constructor(private _http: HttpClient) {
    super();
  }

  override getObjectInfo(markerParam: MarkerParameter): any {
    return "<span>Intensité détéctée : " + markerParam.textNumber?.toString() + "</span><br/><button style=\"margin: 0.25rem auto auto; background-color: #870000; color: #ffffff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;\">\n" +
    "  SUPPRIMER\n" +
    "</button>"
  }

  override fetchAll(map: Map) {
    let markerParams: MarkerParameter[] = [];

    this._http.get<SensorDTO[]>(this.BASE_URL).subscribe(
      list => {
        list.forEach(sensor => {
          markerParams.push({
            coords: sensor.coords,
            type: IconMarkerTypes.SENSOR,
            color: 'black',
            textNumber: sensor.intensity
          })
        });
        super.createMarkers(markerParams, map);
      }
    )
  }
}
