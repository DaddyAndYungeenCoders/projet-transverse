import {Injectable} from '@angular/core';
import {AbstractMarkerService} from './abstract-marker.service';
import {IconMarkerTypes} from '../types/enum/IconType';
import {Map} from 'leaflet';
import {FireMarkerType, MarkerParameter} from '../types/interfaces/MarkersTypes';
import {HttpClient} from '@angular/common/http';
import {WEBSERVER_PORT} from '../types/constants/shared-constants';
import {FireEventDTO} from '../types/DTOs/FireEventDTO';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root',
})
export class FireMarkerService extends AbstractMarkerService<FireMarkerType> {
  private BASE_URL = `http://localhost:${WEBSERVER_PORT}/api/fire-event/fetch-all`;
  constructor(private _http: HttpClient) {
    super();
  }

  override fetchAll(map: Map) {
    let markerParams: MarkerParameter[] = [];

    this._http.get<FireEventDTO[]>(this.BASE_URL).subscribe(
      list => {
        list.forEach(fire => {
          let iconMarkerType: IconMarkerTypes = fire.real ? IconMarkerTypes.FIRE : IconMarkerTypes.FAKEFIRE;
          markerParams.push({
            coords: fire.coords,
            type: iconMarkerType,
            color: 'black',
            intensity: fire.realIntensity,
            id: fire.id
          })
        });
        super.createMarkers(markerParams, map);
      }
    )
  }
  
  deleteFireEvent(id: number): Observable<void> {
    console.info("A fire has been deleted");
    return this._http.delete<void>(`${this.BASE_URL}/delete/${id}`);
  }
}
