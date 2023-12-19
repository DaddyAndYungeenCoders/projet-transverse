import { AfterViewInit, Component } from '@angular/core';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { Coordinates } from '../../types/interfaces/Coordinates';
import * as L from 'leaflet';

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

  constructor() { }

  ngAfterViewInit() {
    this.mountMap(); // Creating the map
  }

  private mountMap() {
    const parc: Coordinates = {
      lat: 45.7790285,
      lng: 4.8755341,
    };

    this.map = L.map('map', {
      center: [parc.lat, parc.lng],
      zoom: this.defaultZoomLevel,
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
