import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { HorseTree } from "src/app/dto/horsetree";
import { HorseService } from "src/app/service/horse.service";

@Component({
  selector: "app-horse-tree",
  templateUrl: "./horse-tree.component.html",
  styleUrls: ["./horse-tree.component.scss"],
})
export class HorseTreeComponent implements OnInit {
  tree$: Observable<HorseTree>;
  depth = 3;
  id: number;
  error = false;
  errorMessage = "";

  constructor(
    private horseService: HorseService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.id = +params.get("id");
      this.tree$ = this.horseService.getTreeById(this.id, this.depth);
    });
    this.route.queryParams.subscribe((params) => {
      this.depth = params["depth"];
      if (this.depth == null) this.depth = 3;
      this.tree$ = this.horseService.getTreeById(this.id, this.depth).pipe(
        catchError((err) => {
          this.defaultServiceErrorHandling(err);
          return throwError(err);
        })
      );
    });
  }

  private onSubmit() {
    this.router.navigate([], {
      queryParams: { depth: this.depth },
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
