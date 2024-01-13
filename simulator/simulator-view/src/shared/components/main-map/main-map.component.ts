import { AfterViewInit, Component, OnInit } from '@angular/core';
import { NgClass, NgForOf, NgIf } from '@angular/common';
import { Coordinates } from '../../types/interfaces/Coordinates';
import * as L from 'leaflet';
import { FireCreationService } from '../../services/fire-creation.service';
import { FireMarkerService } from '../../services/fire-marker-service.service';
import { Subscription } from 'rxjs';
import { InterventionMarkerService } from '../../services/intervention-marker-service.service';
import { FirestationMarkerService } from '../../services/firestation-marker-service.service';
import { SensorMarkerService } from '../../services/sensor-marker-service';
import { StompService } from '../../services/StompService';
import * as jsonPolygon from './polygon.json';
import * as turf from '@turf/turf';
import { SensorDTO } from '../../types/DTOs/SensorDTO';
import { Coords } from '../../types/DTOs/Coords';
import { SensorsCreationService } from '../../services/sensors-creation.service';
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
    private fireStationMarkerService: FirestationMarkerService,
    private interventionMarkerService: InterventionMarkerService,
    private fireMarkerService: FireMarkerService,
    private stompService: StompService,
    private sensorMarkerService: SensorMarkerService,
    private sensorsCreationService: SensorsCreationService
  ) {}

  ngOnInit(): void {
    this.isInMenuCreationMode = this.fireCreationService.isSettingNewElement;
    this.stompService.subscribe('/topic/fire-event', () => {
      this.refreshFires();
    });
    //this.stompService.subscribe("/topic/intervention", () => {
    //  this.refreshIntervention()
    //});
    this.$isInCreationSubscription =
      this.fireCreationService.$isInCreationState.subscribe((isCreating) => {
        this.isInMenuCreationMode = isCreating;
      });
  }
  ngAfterViewInit() {
    this.mountMap(); // Creating the map
    this.map.setZoom(19); // to avoid display bug
    this.map.on('click', (e) => this.createFireEvent(e));
    this.fetchAll(); // fetching all elements once when starting
    const latlngs: Coordinates[] = this.extractData(jsonPolygon);
    let polygon = L.polygon(latlngs, { color: 'red' }).addTo(this.map);
  }

  private extractData(data: any): any[] {
    if (
      data.type === 'MultiPolygon' &&
      data.coordinates &&
      data.coordinates.length > 0
    ) {
      const coordinates = data.coordinates[0][0];

      return coordinates.map((coord: [number, number]) => {
        return { lat: coord[1], lng: coord[0] };
      });
    } else {
      console.error('Le fichier JSON ne correspond pas au format attendu.');
      return [];
    }
  }

  private refreshFires() {
    this.fireMarkerService.fetchAll(this.map);
  }
  private refreshIntervention() {
    console.log('here move intervention'); // TODO
  }

  private fetchAll() {
    this.fireMarkerService.fetchAll(this.map);
    this.fireStationMarkerService.fetchAll(this.map);
    this.interventionMarkerService.fetchAll(this.map);
    this.sensorMarkerService.fetchAll(this.map);
    this.sensorMarkerService.addSensorsZone(
      this.map,
      this.extractData(jsonPolygon)
    );
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
