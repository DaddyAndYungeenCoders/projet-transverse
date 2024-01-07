import { Injectable } from '@angular/core';
import { IconMarkerTypes } from '../types/enum/IconType';
import {faDroplet, faFire, faHome, faSmoking, IconDefinition} from '@fortawesome/free-solid-svg-icons';
import L from 'leaflet';
import {MarkerParameter, MarkersTypes} from '../types/interfaces/MarkersTypes';
import { Map } from 'leaflet';

@Injectable({
  providedIn: 'root',
})
export abstract class AbstractMarkerService<T extends MarkersTypes> {
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
        return faHome
    }
  };

  abstract fetchAll(map: Map): void;

  createMarkers(
    markerParams: MarkerParameter[],
    map: L.Map,
  ): void {
    markerParams.forEach((marker) => {
      const icon = this.getIconMarker(marker.type);
      const iconHtml = `<svg class="svg-inline--fa fa-w-16" aria-hidden="true" focusable="false" data-prefix="${icon.prefix}" data-icon="${icon.iconName}" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 ${icon.icon[0]} ${icon.icon[1]}" style="font-size: 24px;background-color:transparent; color: ${marker.color};"><path fill="currentColor" d="${icon.icon[4]}"></path></svg>`;
      new L.Marker([marker.coords.latitude, marker.coords.longitude], {
        icon: L.divIcon({
          html: iconHtml,
          iconSize: [20, 20],
          className: 'custom-marker-icon',
        }),
      })
        .addTo(map)
        .bindPopup("<span>Intensité du feu:" + marker.intensity?.toString() + "</span><br/><button style=\"margin: 0.25rem auto auto; background-color: #870000; color: #ffffff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;\">\n" +
          "  SUPPRIMER\n" +
          "</button>");
    });
  }

  abstract getObjectInfo(intensity?: number): any;
}
