import { Injectable, Injector } from '@angular/core';
import { IconMarkerTypes } from '../types/enum/IconType';
import {
  faDroplet,
  faFire,
  faHome,
  faSmoking,
  faPassport,
  IconDefinition,
} from '@fortawesome/free-solid-svg-icons';
import L from 'leaflet';
import {
  MarkerParameter,
  MarkersTypes,
} from '../types/interfaces/MarkersTypes';
import { Map } from 'leaflet';
import { Coords } from '../types/DTOs/Coords';
import { FireMarkerService } from './fire-marker-service.service';

@Injectable({
  providedIn: 'root',
})
export abstract class AbstractMarkerService<T extends MarkersTypes> {
 // private fireMarkerService = Injector.create({ providers: [{ provide: FireMarkerService, useClass: FireMarkerService }]}).get(FireMarkerService);
  protected constructor() {}

  getIconMarker(type: IconMarkerTypes): IconDefinition {
    switch (type) {
      case IconMarkerTypes.FAKEFIRE:
        return faSmoking;
      case IconMarkerTypes.FIRE:
        return faFire;
      case IconMarkerTypes.FIREMAN:
        return faDroplet;
      case IconMarkerTypes.FIRESTATION:
        return faHome;
      case IconMarkerTypes.SENSOR:
        return faPassport;
    }
  }

  // TODO Fire station and intervention and sensor
  abstract fetchAll(map: Map): void;

  createMarkers(markerParams: MarkerParameter[], map: L.Map): void {
    markerParams.forEach((marker) => {
      const icon = this.getIconMarker(marker.type);
      let tailleIcone
      if (marker.intensity != undefined){
        tailleIcone = 24 + marker.intensity * 2;
      }
      else {
        tailleIcone = 24;
      }
      const iconHtml = `<svg class="svg-inline--fa fa-w-16" aria-hidden="true" focusable="false" data-prefix="${icon.prefix}" data-icon="${icon.iconName}" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 ${icon.icon[0]} ${icon.icon[1]}" style="font-size: ${tailleIcone};background-color:transparent; color: ${marker.color};"><path fill="currentColor" d="${icon.icon[4]}"></path></svg>`;
      new L.Marker([marker.coords.latitude, marker.coords.longitude], {
        icon: L.divIcon({
          html: iconHtml,
          iconSize: marker.height ? [marker.height, marker.height] : [20, 20],
          className: 'custom-marker-icon',
        }),
      })
        .addTo(map)
        .bindPopup(this.getObjectInfo(marker.intensity, marker.coords, marker.id));
    });
  }

  getObjectInfo(intensity?: number, coords?: Coords): any {
    return (
      '<span>Intensité du feu : ' +
      intensity?.toString() +
      '</span><br/><button (click)="onDeleteFireEvent(${id})" style="margin: 0.25rem auto auto; background-color: #870000; color: #ffffff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">\n' +
      '  SUPPRIMER\n' +
      '</button>'
    );
  }

  onDeleteFireEvent(id: number): void {
  //    this.fireMarkerService.deleteFireEvent(id).subscribe();
  }
}
