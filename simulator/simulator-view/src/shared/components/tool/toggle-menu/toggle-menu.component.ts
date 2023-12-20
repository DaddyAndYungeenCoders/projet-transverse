import {Component, OnDestroy, OnInit} from '@angular/core';
import {faDroplet, faFire, faSmoking} from "@fortawesome/free-solid-svg-icons";
import {IconDefinition} from "@fortawesome/fontawesome-svg-core";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {NgClass, NgForOf} from "@angular/common";
import {FireCreationService} from "../../../services/fire-creation.service";
import {Subscription} from "rxjs";
import {CdkDrag} from "@angular/cdk/drag-drop";

@Component({
  selector: 'app-toggle-menu',
  standalone: true,
  imports: [
    FaIconComponent,
    NgForOf,
    NgClass,
    CdkDrag
  ],
  templateUrl: './toggle-menu.component.html',
  styleUrl: './toggle-menu.component.scss'
})
export class ToggleMenuComponent implements OnInit, OnDestroy {
  fireIcon: IconDefinition = faFire;
  smokingIcon: IconDefinition = faSmoking;
  isValid: boolean = false;
  intensity: number = 1;
  $intensitySubscription: Subscription = new Subscription();

  elements = [
    {
      message: "'real' fire",
      selected: false,
      icon: this.fireIcon,
      id: 0
    },
    {
      message: "'fake' fire",
      selected: false,
      icon: this.smokingIcon,
      id: 1
    }
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

      if (this.elements[id]) {
      this.elements[id].selected = !this.elements[id].selected;
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
    this.fireCreationService.type = this.elements.find(item => item.selected);
    this.fireCreationService.isSettingNewElement = true;
  }

  private isFormValid(): boolean {
    return this.intensity > 0 && this.elements.some(elem => elem.selected);
  }
}
