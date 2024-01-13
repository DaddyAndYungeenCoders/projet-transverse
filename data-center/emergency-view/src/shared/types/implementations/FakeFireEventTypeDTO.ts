import {AbstractFireEventTypes} from "./AbstractFireEventTypesImpl";
import {faSmoking} from "@fortawesome/free-solid-svg-icons";

export class FakeFireEventTypeDTO extends AbstractFireEventTypes {
  startDate: Date;
  endDate: Date | null;

  constructor(intensity: number) {
    super(faSmoking, 2, '\'fake\' fire', false, intensity);
    this.startDate = new Date();
    this.endDate = null;
  }
}
