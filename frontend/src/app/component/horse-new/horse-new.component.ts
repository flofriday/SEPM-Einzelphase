import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { forkJoin } from "rxjs";
import { emptyHorse, Horse } from "src/app/dto/horse";
import { Sport } from "src/app/dto/sport";
import { HorseService } from "src/app/service/horse.service";
import { SportService } from "src/app/service/sport.service";

@Component({
  selector: "app-horese-new",
  templateUrl: "./horse-new.component.html",
  styleUrls: ["./horse-new.component.scss"],
})
export class HorseNewComponent implements OnInit {
  horse: Horse;
  horses: Horse[];
  sports: Sport[];
  error = false;
  errorMessage = "";

  constructor(
    private horseService: HorseService,
    private sportService: SportService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.horse = emptyHorse();
    this.horse.name = "";
    this.horse.description = "";
    forkJoin([
      this.horseService.getAllHorses(),
      this.sportService.getAllSports(),
    ]).subscribe(
      (results) => {
        this.horses = results[0];
        this.sports = results[1];
      },
      (error) => this.defaultServiceErrorHandling(error)
    );
  }

  private onSubmited(horse: Horse) {
    this.horseService.createHorse(horse).subscribe(
      (horse) => {
        this.router.navigate(["/horses", horse.id]);
      },
      (error) => this.defaultServiceErrorHandling(error)
    );
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = "The backend seems not to be reachable";
    } else if (error.error.message === "No message available") {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }
}
