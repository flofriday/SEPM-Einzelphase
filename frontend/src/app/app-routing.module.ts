import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SportFormComponent } from './component/sport-form/sport-form.component';
import {SportListComponent} from './component/sport-list/sport-list.component';

const routes: Routes = [
  //{path: '', redirectTo: 'sports', pathMatch: 'full'},
  {path: 'sports', component: SportListComponent},
  {path: 'sports/new', component: SportFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
