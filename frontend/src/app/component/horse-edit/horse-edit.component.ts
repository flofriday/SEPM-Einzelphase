import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { forkJoin } from "rxjs";
import { Horse } from "src/app/dto/horse";
import { Sport } from "src/app/dto/sport";
import { HorseService } from "src/app/service/horse.service";
import { SportService } from "src/app/service/sport.service";

@Component({
  selector: "app-horse-edit",
  templateUrl: "./horse-edit.component.html",
  styleUrls: ["./horse-edit.component.scss"],
})
export class HorseEditComponent implements OnInit {
  horse: Horse;
  horses: Horse[];
  sports: Sport[];
  error = false;
  errorMessage = "";

  constructor(
    private horseService: HorseService,
    private sportService: SportService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = params.get("id");
      this.loadData(id);
    });
  }

  private loadData(id: any) {
    this.error = false;
    this.errorMessage = "";

    forkJoin([
      this.horseService.getAllHorses(),
      this.sportService.getAllSports(),
    ]).subscribe(
      (results) => {
        this.horses = results[0];
        this.sports = results[1];
        this.horses.forEach((horse) => {
          if (horse.id == id) {
            this.horse = horse;
          }
        });
      },
      (error) => this.defaultServiceErrorHandling(error)
    );
  }

  private onSubmited(horse: Horse) {
    this.horseService.updateHorse(horse).subscribe(
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
