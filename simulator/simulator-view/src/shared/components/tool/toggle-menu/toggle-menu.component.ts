import {Component, OnDestroy, OnInit} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {NgClass, NgForOf} from "@angular/common";
import {FireCreationService} from "../../../services/fire-creation.service";
import {Subscription} from "rxjs";
import {RealFireEventTypeDTO} from "../../../types/implementations/RealFireEventTypeDTO";
import {FakeFireEventTypeDTO} from "../../../types/implementations/FakeFireEventTypeDTO";
import {AbstractFireEventTypes} from "../../../types/implementations/AbstractFireEventTypesImpl";

@Component({
  selector: 'app-toggle-menu',
  standalone: true,
  imports: [
    FaIconComponent,
    NgForOf,
    NgClass
  ],
  templateUrl: './toggle-menu.component.html',
  styleUrl: './toggle-menu.component.scss'
})
export class ToggleMenuComponent implements OnInit, OnDestroy {
  isValid: boolean = false;
  intensity: number = 1;
  $intensitySubscription: Subscription = new Subscription();

  elements: AbstractFireEventTypes[] = [
    new RealFireEventTypeDTO(0),
    new FakeFireEventTypeDTO(0)
  ];

  constructor(private fireCreationService: FireCreationService) {}

  ngOnInit() {
    this.intensity = this.fireCreationService.intensity;
    this.$intensitySubscription = this.fireCreationService.$intensity.subscribe(number => {
      this.intensity = number;
    })
  }

  ngOnDestroy() {
    this.$intensitySubscription.unsubscribe();
  }

  toggleSelected(id: number): void {
      this.elements.forEach(elem => elem.selected = false);

      if (this.elements[id - 1]) {
        this.elements[id - 1].selected = !this.elements[id - 1].selected;
      }

      this.isValid = this.isFormValid();
  }

  increment(): void {
    this.fireCreationService.increment();
    this.isValid = this.isFormValid();
  }

  decrement(): void {
    this.fireCreationService.decrement();
    this.isValid = this.isFormValid();
  }

  validate(): void {
    const selectedElements: AbstractFireEventTypes | undefined = this.elements.find(item => item.selected);
    selectedElements ? this.fireCreationService.validate(selectedElements) : console.error("error during fireEvent creation");
  }

  private isFormValid(): boolean {
    return this.intensity > 0 && this.elements.some(elem => elem.selected);
  }
}
