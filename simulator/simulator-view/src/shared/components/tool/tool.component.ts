import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgClass, NgIf, NgTemplateOutlet} from "@angular/common";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faFire, faTruck, faSmoking, faDroplet, faHome, faPlus} from "@fortawesome/free-solid-svg-icons";
import {IconProp} from "@fortawesome/fontawesome-svg-core";

@Component({
  selector: 'app-tool',
  standalone: true,
  imports: [
    NgIf,
    FaIconComponent,
    NgTemplateOutlet,
    NgClass
  ],
  templateUrl: './tool.component.html',
  styleUrl: './tool.component.scss'
})
export class ToolComponent {
  imagePath: string = '';
  menuOpened: boolean = false;
  plusIcon = faPlus;

  @Input() set text(value: string) {
    this._text = value.toUpperCase();
  }

  @Input() isCreateButton: boolean = false;

  @Input() set image(value: string) {
    this.imagePath = value;
    console.log(value);
    switch (value) {
      case 'fire': {
        this._image = faFire;
        break;
      }
      case 'truck': {
        this._image = faTruck;
        break;
      }
      case 'smoking': {
        this._image = faSmoking;
        break;
      }
      case 'droplet': {
        this._image = faDroplet;
        break;
      }
      case 'home': {
        this._image = faHome;
        break;
      }
      default: {
        this._image = faFire;
        break;
      }
    }
  };

  get image(): IconProp {
    return this._image;
  }

  @Output() onClick: EventEmitter<null> = new EventEmitter<null>();

  private _text: string = '';
  _image: IconProp = faFire;

  get text(): string {
    return this._text;
  }

  handle(): void {
    this.onClick.emit();
  }

  openMenu(): void {
    this.menuOpened = !this.menuOpened;
  }
}
