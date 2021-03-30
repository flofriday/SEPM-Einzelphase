import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Horse } from "../dto/horse";
import { HorseTree } from "../dto/horsetree";

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
   * Fetches all horses from the backend that match the filters.
   */
  search(horse: Horse): Observable<Horse[]> {
    console.log("Search all horses");
    let params = new HttpParams();
    if (horse.name !== null) params = params.append("name", horse.name);
    if (horse.description !== null)
      params = params.append("description", horse.description);
    if (horse.sex !== null) params = params.append("sex", horse.sex);
    if (horse.birthDay !== null)
      params = params.append("birthDay", horse.birthDay);
    if (horse.favoriteSportId !== null)
      params = params.append(
        "favoriteSportId",
        horse.favoriteSportId.toString()
      );

    return this.httpClient.get<Horse[]>(baseUri, {
      params: params,
    });
  }

  getTreeById(id: number, depth: number): Observable<HorseTree> {
    console.log("Load horse tree for " + id);
    return this.httpClient.get<HorseTree>(
      baseUri + "/" + id + "/tree?depth=" + depth
    );
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
    return this.httpClient.put<Horse>(baseUri + "/" + horse.id, horse);
  }

  /**
   * Update an existing horse.
   *
   * @param horse to update.
   */
  deleteHorse(id: number): Observable<Horse> {
    console.log("Delete the horse " + id);
    return this.httpClient.delete<Horse>(baseUri + "/" + id);
  }
}
