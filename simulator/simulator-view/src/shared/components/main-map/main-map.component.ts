import { AfterViewInit, Component, OnInit } from '@angular/core';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { Coordinates } from '../../types/interfaces/Coordinates';
import * as L from 'leaflet';
import { IconMarkerTypes } from '../../types/enum/IconType';
import { FireCreationService } from '../../services/fire-creation.service';
import { FireMarkerService } from '../../services/fire-marker-service.service';
import { InterventionMarkerService } from '../../services/intervention-marker-service.service';
import { FirestationMarkerService } from '../../services/firestation-marker-service.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-main-map',
  standalone: true,
  imports: [NgClass, NgIf, NgForOf],
  templateUrl: './main-map.component.html',
  styleUrl: './main-map.component.scss',
})
export class MainMapComponent implements OnInit, AfterViewInit {
  map!: L.Map;
  isInMenuCreationMode: boolean = false;
  $isInCreationSubscription: Subscription = new Subscription();

  private defaultZoomLevel = 20;
  constructor(
    private fireCreationService: FireCreationService,
    private fireMarkerService: FireMarkerService,
  ) {}

  ngOnInit(): void {
    this.isInMenuCreationMode = this.fireCreationService.isSettingNewElement;
    this.$isInCreationSubscription =
      this.fireCreationService.$isInCreationState.subscribe((isCreating) => {
        this.isInMenuCreationMode = isCreating;
        if (!isCreating) {
          this.fireMarkerService.fetchAll(this.map);
        }
      });
  }
  ngAfterViewInit() {
    this.mountMap(); // Creating the map
    this.map.setZoom(19); // to avoid display bug
    this.map.on('click', (e) => this.createFireEvent(e));
    this.fireMarkerService.fetchAll(this.map);
  }

  private createFireEvent(e: L.LeafletMouseEvent) {
    this.fireCreationService.create(e);
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
