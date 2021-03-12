import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Horse } from "src/app/dto/horse";
import { Sport } from "src/app/dto/sport";

@Component({
  selector: "app-horse-form",
  templateUrl: "./horse-form.component.html",
  styleUrls: ["./horse-form.component.scss"],
})
export class HorseFormComponent implements OnInit {
  @Input() horse: Horse;
  @Input() horses: Horse[];
  @Input() sports: Sport[];
  @Input() buttonName: String;
  @Output() submited = new EventEmitter<Horse>();

  maleHorses: Horse[] = [];
  femaleHorses: Horse[] = [];

  constructor() {}

  ngOnInit(): void {
    this.horses.forEach((horse) => {
      if (horse.sex == "male") this.maleHorses.push(horse);
      if (horse.sex == "female") this.femaleHorses.push(horse);
    });
  }

  private onSubmit() {
    this.submited.emit(this.horse);
  }
}
