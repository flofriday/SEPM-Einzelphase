import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Horse } from "src/app/dto/horse";
import { Sport } from "src/app/dto/sport";

@Component({
  selector: "app-horse-search-card",
  templateUrl: "./horse-search-card.component.html",
  styleUrls: ["./horse-search-card.component.scss"],
})
export class HorseSearchCardComponent implements OnInit {
  @Input() horse: Horse;
  @Input() sports: Sport[];
  @Output() applied = new EventEmitter<Horse>();

  constructor() {}

  ngOnInit(): void {}

  private onApply() {
    if (this.horse.name !== null && this.horse.name.trim() === "")
      this.horse.name = null;
    if (this.horse.description !== null && this.horse.description.trim() === "")
      this.horse.description = null;
    if (this.horse.birthDay !== null && this.horse.birthDay.trim() === "")
      this.horse.birthDay = null;
    this.applied.emit(this.horse);
  }
}
