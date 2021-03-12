import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HeaderComponent } from "./component/header/header.component";
import { SportComponent } from "./component/sport/sport.component";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { SportListComponent } from "./component/sport-list/sport-list.component";
import { SportFormComponent } from "./component/sport-form/sport-form.component";
import { HorseListComponent } from "./component/horse-list/horse-list.component";
import { HorseFormComponent } from "./component/horse-form/horse-form.component";
import { HorseDetailComponent } from "./component/horse-detail/horse-detail.component";
import { HorseNewComponent } from "./component/horse-new/horse-new.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SportComponent,
    SportListComponent,
    SportFormComponent,
    HorseListComponent,
    HorseFormComponent,
    HorseDetailComponent,
    HorseNewComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
