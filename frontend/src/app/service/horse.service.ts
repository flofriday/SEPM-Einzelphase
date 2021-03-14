import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Horse } from "../dto/horse";

const baseUri = environment.backendUrl + "/horses";

@Injectable({
  providedIn: "root",
})
export class HorseService {
  constructor(private httpClient: HttpClient) {}

  /**
   * Loads specific horse from the backend
   *
   * @param id of horse to load
   */
  getHorseById(id: number): Observable<Horse> {
    console.log("Load horse details for " + id);
    return this.httpClient.get<Horse>(baseUri + "/" + id);
  }

  /**
   * Fetches all horses from the backend.
   */
  getAllHorses(): Observable<Horse[]> {
    console.log("Load all horses");
    return this.httpClient.get<Horse[]>(baseUri);
  }

  /**
   * Create an new horse.
   *
   * @param horse to add.
   */
  createHorse(horse: Horse): Observable<Horse> {
    console.log("Create new horse " + horse);
    return this.httpClient.post<Horse>(baseUri, horse);
  }

  /**
   * Update an existing horse.
   *
   * @param horse to update.
   */
  updateHorse(horse: Horse): Observable<Horse> {
    console.log("Update the horse " + horse);
    return this.httpClient.put<Horse>(baseUri, horse);
  }
}
