import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MainMapComponent } from "../shared/components/main-map/main-map.component";
import { ToolComponent } from "../shared/components/tool/tool.component";
import {ToolProps} from "../shared/types/interfaces/ToolProps";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, MainMapComponent, ToolComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  fireProp: ToolProps = {
    message: 'feu',
    imagePath: 'fire'
  };

  firemanProp: ToolProps = {
    message:  'Interventions en cours',
    imagePath: 'droplet'
  };

  smokingProp: ToolProps = {
    message:  'fausses alertes',
    imagePath: 'smoking'
  };

  homeProp: ToolProps = {
    message:  'Casernes',
    imagePath: 'home'
  };

  toolPropsArray: ToolProps[] = [this.fireProp, this.firemanProp, this.smokingProp, this.homeProp];

  filter(): void {
    console.log("Clicked");
  }
}
