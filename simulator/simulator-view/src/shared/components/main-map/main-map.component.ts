import { AfterViewInit, Component } from '@angular/core';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { Coordinates } from '../../types/interfaces/Coordinates';
import * as L from 'leaflet';
import { MarkerService } from '../../services/marker.service';
import { IconMarkerTypes } from '../../types/enum/IconType';

@Component({
  selector: 'app-main-map',
  standalone: true,
  imports: [NgClass, NgIf, NgForOf],
  templateUrl: './main-map.component.html',
  styleUrl: './main-map.component.scss',
})
export class MainMapComponent implements AfterViewInit {
  map!: L.Map;
  private defaultZoomLevel = 20;

  constructor(private markerService: MarkerService) {}

  ngAfterViewInit() {
    this.mountMap(); // Creating the map
    this.map.setZoom(19); // to avoid display bug
    this.markerService.createMarkers(
      [
        {
          lat: 45.7792278,
          lng: 4.8755341,
        },
        {
          lat: 45.7790395,
          lng: 4.8755342,
        },
        {
          lat: 45.7791182,
          lng: 4.8755343,
        },
      ],
      this.map,
      IconMarkerTypes.FIRESTATION
    );
  }

  private mountMap() {
    const parc: Coordinates = {
      lat: 45.7790285,
      lng: 4.8755341,
    };

    this.map = L.map('map', {
      center: [parc.lat, parc.lng],
      zoom: this.defaultZoomLevel,
      attributionControl: false,
      zoomControl: false,
    });

    const mainLayer = L.tileLayer(
      'https://tiles.stadiamaps.com/tiles/osm_bright/{z}/{x}/{y}{r}.png',
      {
        minZoom: 5,
        maxZoom: 20,
      }
    ).addTo(this.map);
  }
}
