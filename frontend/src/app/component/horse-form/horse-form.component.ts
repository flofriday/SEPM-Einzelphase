import { Component, Input, OnInit } from "@angular/core";
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
  @Input() readonly: boolean;

  horseFather: Horse;
  horseMother: Horse;
  horseSport: Sport;

  constructor() {}

  ngOnInit(): void {
    this.horseFather = this.horse;
    this.horseMother = this.horse;
  }
}
