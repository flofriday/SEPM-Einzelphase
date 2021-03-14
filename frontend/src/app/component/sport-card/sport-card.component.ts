import { Component, Input, OnInit } from "@angular/core";
import { Sport } from "src/app/dto/sport";

@Component({
  selector: "app-sport-card",
  templateUrl: "./sport-card.component.html",
  styleUrls: ["./sport-card.component.scss"],
})
export class SportCardComponent implements OnInit {
  @Input() sport: Sport;

  constructor() {}

  ngOnInit(): void {}
}
