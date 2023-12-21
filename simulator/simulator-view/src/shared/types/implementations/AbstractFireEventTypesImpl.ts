import {FireEventTypes} from "../interfaces/FireEventTypes";
import {IconDefinition} from "@fortawesome/fontawesome-svg-core";

export class AbstractFireEventTypes implements FireEventTypes {
  icon: IconDefinition;
  id: number;
  message: string;
  selected: boolean;
  intensity: number;

  constructor(icon: IconDefinition, id: number, message: string, selected: boolean, intensity: number) {
    this.id = id;
    this.message = message;
    this.icon = icon;
    this.selected = selected;
    this.intensity = intensity;
  }

}
