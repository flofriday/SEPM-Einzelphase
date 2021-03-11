import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap } from "@angular/router";
import { Observable, EMPTY, forkJoin, of } from "rxjs";
import { Horse } from "src/app/dto/horse";
import { Sport } from "src/app/dto/sport";
import { HorseService } from "src/app/service/horse.service";
import { SportService } from "src/app/service/sport.service";
import { switchMap } from "rxjs/operators";

@Component({
  selector: "app-horse-detail",
  templateUrl: "./horse-detail.component.html",
  styleUrls: ["./horse-detail.component.scss"],
})
export class HorseDetailComponent implements OnInit {
  error = false;
  errorMessage = "";
  horse: Horse;
  sport: Sport;
  father: Horse;
  mother: Horse;

  constructor(
    private horseService: HorseService,
    private sportService: SportService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id = params.get("id");
      this.loadData(id);
    });
  }

  private loadData(id: any): void {
    this.error = false;
    this.errorMessage = "";
    this.horseService.getHorseById(id).subscribe(
      (horse: Horse) => {
        this.horse = horse;
        this.loadDependencies();
      },
      (error) => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  /**
   * Load all "Dependencies" of the current horse.
   * For example the horse only saves the Id of its favorite sport but that
   * number is useless for the user, so we need to make an extra request to
   * get the sports name.
   */
  private loadDependencies(): void {
    let dependencies = [];
    if (this.horse.favoriteSportId !== null) {
      dependencies.push(
        this.sportService.getSportById(this.horse.favoriteSportId)
      );
    } else {
      dependencies.push(of(null));
    }

    if (this.horse.motherId !== null) {
      dependencies.push(this.horseService.getHorseById(this.horse.motherId));
    } else {
      dependencies.push(of(null));
    }

    if (this.horse.fatherId !== null) {
      dependencies.push(this.horseService.getHorseById(this.horse.fatherId));
    } else {
      dependencies.push(of(null));
    }

    forkJoin(dependencies).subscribe(
      (results) => {
        this.sport = results[0] as Sport;
        this.mother = results[1] as Horse;
        this.father = results[2] as Horse;
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
