import { Component, OnInit } from "@angular/core";
import { Observable, throwError } from "rxjs";
import { Horse } from "src/app/dto/horse";
import { HorseService } from "src/app/service/horse.service";
import { catchError } from "rxjs/operators";

@Component({
  selector: "app-horse-list",
  templateUrl: "./horse-list.component.html",
  styleUrls: ["./horse-list.component.scss"],
})
export class HorseListComponent implements OnInit {
  error = false;
  errorMessage = "";
  horses$: Observable<Horse[]>;

  constructor(private horseService: HorseService) {}

  ngOnInit(): void {
    this.loadHorses();
  }

  private loadHorses() {
    this.error = false;
    this.errorMessage = "";
    this.horses$ = this.horseService.getAllHorses().pipe(
      catchError((err) => {
        this.defaultServiceErrorHandling(err);
        return throwError(err);
      })
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
