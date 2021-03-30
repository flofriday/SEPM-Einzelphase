import { Component, Input, OnInit } from "@angular/core";
import { HorseTree } from "src/app/dto/horsetree";

@Component({
  selector: "app-horse-tree-item",
  templateUrl: "./horse-tree-item.component.html",
  styleUrls: ["./horse-tree-item.component.scss"],
})
export class HorseTreeItemComponent implements OnInit {
  @Input() tree: HorseTree;
  private collapsed = false;

  constructor() {}

  ngOnInit(): void {}

  private onCollapse() {
    this.collapsed = !this.collapsed;
    console.log(this.collapsed);
  }
}
