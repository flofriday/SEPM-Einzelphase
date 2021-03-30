import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { Observable } from "rxjs";
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
      this.tree$ = this.horseService.getTreeById(this.id, this.depth);
    });
  }

  private onSubmit() {
    this.router.navigate([], {
      queryParams: { depth: this.depth },
    });
  }
}
