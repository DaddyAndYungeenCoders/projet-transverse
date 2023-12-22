import { Injectable } from '@angular/core';
import { IconMarkerTypes } from '../types/enum/IconType';
import { IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { Coordinates } from '../types/interfaces/Coordinates';
import L from 'leaflet';

@Injectable({
  providedIn: 'root',
})
export abstract class AbstractMarkerService<T> {
  private object!: T;
  constructor() {}

  abstract getIconMarker(type: IconMarkerTypes): IconDefinition;
  createMarkers(
    coords: Coordinates[],
    map: L.Map,
    type: IconMarkerTypes,
    color: string
  ): void {
    coords.forEach((coord) => {
      const icon = this.getIconMarker(type);
      const iconHtml = `<svg class="svg-inline--fa fa-w-16" aria-hidden="true" focusable="false" data-prefix="${icon.prefix}" data-icon="${icon.iconName}" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 ${icon.icon[0]} ${icon.icon[1]}" style="font-size: 24px;background-color:transparent; color: ${color};"><path fill="currentColor" d="${icon.icon[4]}"></path></svg>`;
      new L.Marker([coord.lat, coord.lng], {
        icon: L.divIcon({
          html: iconHtml,
          iconSize: [20, 20],
          className: 'custom-marker-icon',
        }),
      })
        .addTo(map)
        .bindPopup(JSON.stringify(this.getObjectInfo()));
    });
  }

  abstract getObjectInfo(): T;
}
