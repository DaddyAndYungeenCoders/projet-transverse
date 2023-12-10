import {AfterViewInit, Component} from '@angular/core';
import * as L from 'leaflet';
import {Coords} from "../../types/interfaces/Coords";

@Component({
  selector: 'app-main-map',
  standalone: true,
  imports: [],
  templateUrl: './main-map.component.html',
  styleUrls: [
    './main-map.component.scss',
    '../../styles.scss'
  ]
})
export class MainMapComponent implements AfterViewInit {
  map!: L.Map;
  private defaultZoomLevel = 12;

  constructor() {
  }

  ngAfterViewInit() {
    this.mountMap(); // Creating the map
  }

  private mountMap() {
    const parc: Coords = {
      lat: 48.114384,
      lng: -1.669494
    };


    this.map = L.map('map', {
      center: [parc.lat, parc.lng],
      zoom: this.defaultZoomLevel
    });

    const mainLayer = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      minZoom: 12,
      maxZoom: 17
    }).addTo(this.map);
  }
}
