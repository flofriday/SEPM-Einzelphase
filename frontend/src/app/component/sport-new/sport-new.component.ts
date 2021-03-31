import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { first } from "rxjs/operators";
import { SportService } from "src/app/service/sport.service";
import { Sport } from "../../dto/sport";

@Component({
  selector: "app-sport-new",
  templateUrl: "./sport-new.component.html",
  styleUrls: ["./sport-new.component.scss"],
})
export class SportNewComponent implements OnInit {
  error = false;
  errorMessage = "";
  sport: Sport = { id: null, name: "", description: "" };

  constructor(private sportService: SportService, private router: Router) {}

  ngOnInit(): void {}

  private onSubmit() {
    this.sportService.createSport(this.sport).subscribe(
      () => {
        this.router.navigate(["/sports"]);
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
