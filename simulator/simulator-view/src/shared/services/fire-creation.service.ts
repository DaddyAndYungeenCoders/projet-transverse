import {EventEmitter, Injectable} from '@angular/core';
import * as L from "leaflet";
import {RealFireEventTypeDTO} from "../types/implementations/RealFireEventTypeDTO";
import {AbstractFireEventTypes} from "../types/implementations/AbstractFireEventTypesImpl";

@Injectable({
  providedIn: 'root'
})
export class FireCreationService {
  $intensity: EventEmitter<number> = new EventEmitter<number>();
  intensity: number = 0;
  type: AbstractFireEventTypes = new RealFireEventTypeDTO(0);
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

  validate(type: AbstractFireEventTypes): void {
    this.type = type;
    this.type.intensity = this.intensity;
    this.isSettingNewElement = true;
  }

  create(event: L.LeafletMouseEvent): void {
    if (this.isSettingNewElement) {
      console.log(this.type);
      console.log(event.latlng);
      // TODO CALL API TO CREATE FIRE
    } else {
      return;
    }

    this.isSettingNewElement = false;
  }
}
