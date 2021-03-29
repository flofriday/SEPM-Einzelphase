import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { HeaderComponent } from "./component/header/header.component";
import { SportComponent } from "./component/sport/sport.component";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { SportListComponent } from "./component/sport-list/sport-list.component";
import { SportNewComponent } from "./component/sport-new/sport-new.component";
import { HorseListComponent } from "./component/horse-list/horse-list.component";
import { HorseFormComponent } from "./component/horse-form/horse-form.component";
import { HorseDetailComponent } from "./component/horse-detail/horse-detail.component";
import { HorseNewComponent } from "./component/horse-new/horse-new.component";
import { HorseEditComponent } from "./component/horse-edit/horse-edit.component";
import { HorseCardComponent } from "./component/horse-card/horse-card.component";
import { SportCardComponent } from "./component/sport-card/sport-card.component";
import { HorseDeleteComponent } from "./component/horse-delete/horse-delete.component";
import { HorseSearchCardComponent } from "./component/horse-search-card/horse-search-card.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SportComponent,
    SportListComponent,
    SportNewComponent,
    HorseListComponent,
    HorseFormComponent,
    HorseDetailComponent,
    HorseNewComponent,
    HorseEditComponent,
    HorseCardComponent,
    SportCardComponent,
    HorseDeleteComponent,
    HorseSearchCardComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
