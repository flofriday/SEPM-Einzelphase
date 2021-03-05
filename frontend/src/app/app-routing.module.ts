import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SportComponent} from './component/sport/sport.component';

const routes: Routes = [
  {path: '', redirectTo: 'sports', pathMatch: 'full'},
  {path: 'sports', component: SportComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
