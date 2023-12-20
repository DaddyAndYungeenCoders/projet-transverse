import {EventEmitter, Injectable} from '@angular/core';
import * as L from "leaflet";

@Injectable({
  providedIn: 'root'
})
export class FireCreationService {
  $intensity: EventEmitter<number> = new EventEmitter<number>();
  intensity: number = 0;
  type: any = {};
  isSettingNewElement: boolean = false;

  constructor() { }

  increment(): void {
    this.intensity++;
    this.$intensity.emit(this.intensity);
  }

  decrement(): void {
    this.intensity--;
    this.$intensity.emit(this.intensity);
  }

  create(event: L.LeafletMouseEvent): void {
    if (this.isSettingNewElement) {
      console.log(event.latlng.lat);
      console.log(this.type);
      console.log(this.intensity);
      // TODO CALL API TO CREATE FIRE
    } else {
      return;
    }

    this.isSettingNewElement = false;
  }
}
