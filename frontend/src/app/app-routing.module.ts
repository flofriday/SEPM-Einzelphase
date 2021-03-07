import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SportComponent} from './component/sport/sport.component';
import {SportListComponent} from './component/sport-list/sport-list.component';

const routes: Routes = [
  {path: '', redirectTo: 'sports', pathMatch: 'full'},
  {path: 'sports', component: SportListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
