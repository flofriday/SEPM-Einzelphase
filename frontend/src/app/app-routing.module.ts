import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HorseDeleteComponent } from "./component/horse-delete/horse-delete.component";
import { HorseDetailComponent } from "./component/horse-detail/horse-detail.component";
import { HorseEditComponent } from "./component/horse-edit/horse-edit.component";
import { HorseListComponent } from "./component/horse-list/horse-list.component";
import { HorseNewComponent } from "./component/horse-new/horse-new.component";
import { SportNewComponent } from "./component/sport-new/sport-new.component";
import { SportListComponent } from "./component/sport-list/sport-list.component";

const routes: Routes = [
  { path: "", redirectTo: "/horses", pathMatch: "full" },
  { path: "sports", component: SportListComponent },
  { path: "sports/new", component: SportNewComponent },
  { path: "horses", component: HorseListComponent },
  { path: "horses/new", component: HorseNewComponent },
  { path: "horses/:id", component: HorseDetailComponent },
  { path: "horses/:id/edit", component: HorseEditComponent },
  { path: "horses/:id/delete", component: HorseDeleteComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
