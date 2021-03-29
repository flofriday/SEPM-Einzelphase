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

  constructor(
    private horseService: HorseService,
    private sportService: SportService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      let id: number = +params.get("id");
      this.horseService.getHorseById(id).subscribe(
        (horse: Horse) => {
          this.horse = horse;
        },
        (error) => {
          this.defaultServiceErrorHandling(error);
        }
      );
    });
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
