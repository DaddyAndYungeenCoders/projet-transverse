import {Injectable} from '@angular/core';
import {AbstractMarkerService} from './abstract-marker.service';
import {IconMarkerTypes} from '../types/enum/IconType';
import {Map} from 'leaflet';
import {FireMarkerType, MarkerParameter} from '../types/interfaces/MarkersTypes';
import {HttpClient} from '@angular/common/http';
import {WEBSERVER_PORT} from '../types/constants/shared-constants';
import {FireEventDTO} from '../types/DTOs/FireEventDTO';
import { Observable } from 'rxjs/internal/Observable';
import {Coords} from "../types/DTOs/Coords";

@Injectable({
  providedIn: 'root',
})
export class FireMarkerService extends AbstractMarkerService<FireMarkerType> {
  private BASE_URL = `http://localhost:${WEBSERVER_PORT}/api/fire-event`
  private URL = this.BASE_URL + `/fetch-all`;
  private deleteURL = this.BASE_URL + "/delete";
  constructor(private _http: HttpClient) {
    super();
  }

  override fetchAll(map: Map) {
    let markerParams: MarkerParameter[] = [];

    this._http.get<FireEventDTO[]>(this.URL).subscribe(
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
    return this._http.delete<void>(`${this.deleteURL}/${id}`);
  }

  override getObjectInfo(intensity?: number, coords?: Coords, id?: number): any {
    const button = document.createElement('button');
    button.style.cssText = 'margin: 0.25rem auto auto; background-color: #870000; color: #ffffff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;';
    button.textContent = 'SUPPRIMER';

    button.addEventListener('click', () => {
      if (id != undefined)
        this.deleteFireEvent(id).subscribe();
    });

    const container = document.createElement('div');
    container.innerHTML = `<span>Intensité du feu : ${intensity}</span><br/>`;
    container.appendChild(button);

    return container;
  }
}
