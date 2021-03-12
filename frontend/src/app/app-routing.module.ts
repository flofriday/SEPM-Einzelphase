import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { HorseDetailComponent } from "./component/horse-detail/horse-detail.component";
import { HorseListComponent } from "./component/horse-list/horse-list.component";
import { HorseNewComponent } from "./component/horse-new/horse-new.component";
import { SportFormComponent } from "./component/sport-form/sport-form.component";
import { SportListComponent } from "./component/sport-list/sport-list.component";

const routes: Routes = [
  { path: "", redirectTo: "/horses", pathMatch: "full" },
  { path: "sports", component: SportListComponent },
  { path: "sports/new", component: SportFormComponent },
  { path: "horses", component: HorseListComponent },
  { path: "horses/new", component: HorseNewComponent },
  { path: "horses/:id", component: HorseDetailComponent },
  //{path: 'horses/:id/edit', component: HorseEditComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
