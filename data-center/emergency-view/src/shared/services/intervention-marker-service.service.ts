import { Injectable } from '@angular/core';
import { AbstractMarkerService } from './abstract-marker.service';
import {InterventionMarkerType, MarkerParameter} from '../types/interfaces/MarkersTypes';
import { Map } from 'leaflet';
import {WEBSERVER_PORT} from '../types/constants/shared-constants';
import {HttpClient} from '@angular/common/http';
import {IconMarkerTypes} from '../types/enum/IconType';
import {InterventionDTO} from '../types/DTOs/InterventionDTO';

@Injectable({
  providedIn: 'root',
})
export class InterventionMarkerService extends AbstractMarkerService<InterventionMarkerType> {
  private BASE_URL = `http://localhost:${WEBSERVER_PORT}/api/team/fetch-all`;
  constructor(private _http: HttpClient) {
    super();
  }

  override getObjectInfo(markerParam: MarkerParameter): any {
    return "<span>Equipe numero " + markerParam.number2?.toString() + "</span><br/><span>Reste endurance: " + markerParam.textNumber?.toString() + "</span><button style=\"margin: 0.25rem auto auto; background-color: #870000; color: #ffffff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;\">\n" +
      "  SUPPRIMER\n" +
      "</button>"
  }

  override fetchAll(map: Map) {
    let markerParams: MarkerParameter[] = [];

    this._http.get<InterventionDTO[]>(this.BASE_URL).subscribe(
      (list) => {
      const interventions = list.filter((intervention) => !intervention.isAvailable);
      interventions.forEach(intervention => {
          let iconMarkerType = IconMarkerTypes.INTERVENTION;
          markerParams.push({
            coords: intervention.coords,
            type: iconMarkerType,
            color: 'green',
            textNumber: intervention.stamina,
            number2: intervention.id
          })
        });
        super.createMarkers(markerParams, map);
      }
    )
  }
}
