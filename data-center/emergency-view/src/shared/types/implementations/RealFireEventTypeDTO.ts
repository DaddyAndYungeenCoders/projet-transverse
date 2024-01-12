import {AbstractFireEventTypes} from "./AbstractFireEventTypesImpl";
import {faFire} from "@fortawesome/free-solid-svg-icons";

export class RealFireEventTypeDTO extends AbstractFireEventTypes {
  startDate: Date;
  endDate: Date | null;

  constructor(intensity: number) {
    super(faFire, 1, '\'real\' fire', false, intensity);
    this.startDate = new Date();
    this.endDate = null;
  }
}
