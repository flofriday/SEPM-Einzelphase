import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { Horse } from "src/app/dto/horse";
import { HorseService } from "src/app/service/horse.service";

@Component({
  selector: "app-horse-delete",
  templateUrl: "./horse-delete.component.html",
  styleUrls: ["./horse-delete.component.scss"],
})
export class HorseDeleteComponent implements OnInit {
  private horse$: Observable<Horse>;
  error = false;
  errorMessage = "";
  horseId: number;

  constructor(
    private horseService: HorseService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.horseId = +params.get("id");
      this.horse$ = this.horseService.getHorseById(this.horseId).pipe(
        catchError((err) => {
          this.defaultServiceErrorHandling(err);
          return throwError(err);
        })
      );
    });
  }

  private delete() {
    this.horseService.deleteHorse(this.horseId).subscribe(
      () => {
        this.router.navigate(["/horses"]);
      },
      (error) => {
        this.defaultServiceErrorHandling(error);
      }
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
