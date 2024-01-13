import { AbstractMarkerService } from './abstract-marker.service';
import {
  MarkerParameter,
  SensorMarkerType,
} from '../types/interfaces/MarkersTypes';
import { Injectable } from '@angular/core';
import { Map } from 'leaflet';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SensorDTO } from '../types/DTOs/SensorDTO';
import { WEBSERVER_PORT } from '../types/constants/shared-constants';
import { IconMarkerTypes } from '../types/enum/IconType';
import * as turf from '@turf/turf';
import { Coordinates } from '../types/interfaces/Coordinates';
import * as L from 'leaflet';

@Injectable({
  providedIn: 'root',
})
export class SensorMarkerService extends AbstractMarkerService<SensorMarkerType> {
  private BASE_URL = `http://localhost:${WEBSERVER_PORT}/api/sensor`;

  constructor(private _http: HttpClient) {
    super();
  }

  override getObjectInfo(intensity?: number): any {
    return (
      '<span>Intensité détéctée : ' +
      intensity?.toString() +
      '</span><br/><button style="margin: 0.25rem auto auto; background-color: #870000; color: #ffffff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">\n' +
      '  SUPPRIMER\n' +
      '</button>'
    );
  }
  override createMarkers(markerParams: MarkerParameter[], map: L.Map): void {
    markerParams.forEach((marker) => {
      const icon = this.getIconMarker(marker.type);
      const iconHtml = `<svg class="svg-inline--fa fa-w-16" aria-hidden="true" focusable="false" data-prefix="${icon.prefix}" data-icon="${icon.iconName}" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 ${icon.icon[0]} ${icon.icon[1]}" style="font-size: 24px;background-color:transparent; color: ${marker.color};"><path fill="currentColor" d="${icon.icon[4]}"></path></svg>`;
      new L.Marker([marker.coords.latitude, marker.coords.longitude], {
        icon: L.divIcon({
          html: iconHtml,
          iconSize: marker.height ? [marker.height, marker.height] : [20, 20],
          className: 'custom-marker-icon',
        }),
      })
        .addTo(map)
        .bindPopup(super.getObjectInfo(marker.intensity, marker.coords));

      const leafletCircle = L.circle(
        [marker.coords.latitude, marker.coords.longitude],
        250
      )
        .setStyle({
          color: 'transparent',
        })
        .addTo(map);

      const leafletRectangle = L.rectangle(leafletCircle.getBounds(), {
        color: 'transparent',
        weight: 2,
      }).addTo(map);

      leafletRectangle.on('mouseover', function () {
        leafletRectangle.setStyle({
          color: '#3388ff',
        });
      });

      leafletRectangle.on('mouseout', function () {
        leafletRectangle.setStyle({
          color: 'transparent',
        });
      });
    });
  }

  override fetchAll(map: Map) {
    let markerParams: MarkerParameter[] = [];

    this._http
      .get<SensorDTO[]>(`${this.BASE_URL}/fetch-all`)
      .subscribe((list) => {
        list.forEach((sensor) => {
          markerParams.push({
            coords: sensor.coords,
            type: IconMarkerTypes.SENSOR,
            color: 'black',
            intensity: sensor.intensity,
            height: 50,
          });
        });
        this.createMarkers(markerParams, map);
      });
  }
  addSensorsZone(map: Map, latlngs: Coordinates[]) {
    // const positionTab: turf.Position[] = [];
    // latlngs.forEach((point) => {
    //   positionTab.push([point.lat, point.lng]);
    // });
    // const turfPolygon = turf.polygon([positionTab]);
    // console.error(turfPolygon);
    // this._http
    //   .get<SensorDTO[]>(`${this.BASE_URL}/fetch-all`)
    //   .subscribe((list) => {
    //     list.forEach((sensor) => {
    //       const intersection = turf.intersect(circle, turfPolygon);
    //       if (intersection) {
    //         console.error(intersection);
    //         const intersectionLayer = L.geoJSON(intersection, {
    //           style: () => ({
    //             color: 'blue', // La couleur du bord
    //             fillColor: '#a29', // La couleur de remplissage
    //             fillOpacity: 0.5, // L'opacité de remplissage
    //           }),
    //         }).addTo(map);
    //         intersectionLayer.bindPopup(
    //           'Informations du capteur: ' + sensor.intensity
    //         );
    //       }
    //     });
    //   });
  }
}
