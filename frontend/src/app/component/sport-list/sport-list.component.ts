import { Component, OnInit } from "@angular/core";
import { SportService } from "../../service/sport.service";
import { Sport } from "../../dto/sport";
import { catchError } from "rxjs/operators";
import { Observable, throwError } from "rxjs";

@Component({
  selector: "app-sport-list",
  templateUrl: "./sport-list.component.html",
  styleUrls: ["./sport-list.component.scss"],
})
export class SportListComponent implements OnInit {
  error = false;
  errorMessage = "";
  sports$: Observable<Sport[]>;

  constructor(private sportService: SportService) {}

  ngOnInit(): void {
    this.loadSports();
  }

  private loadSports() {
    this.error = false;
    this.errorMessage = "";
    this.sports$ = this.sportService.getAllSports().pipe(
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
