import { Component, Input, OnInit } from "@angular/core";
import { Horse } from "src/app/dto/horse";

@Component({
  selector: "app-horse-card",
  templateUrl: "./horse-card.component.html",
  styleUrls: ["./horse-card.component.scss"],
})
export class HorseCardComponent implements OnInit {
  @Input() horse: Horse;

  constructor() {}

  ngOnInit(): void {}
}
