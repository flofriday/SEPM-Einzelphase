import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Sport} from '../dto/sport';
import {environment} from 'src/environments/environment';

const baseUri = environment.backendUrl + '/sports';

@Injectable({
  providedIn: 'root'
})
export class SportService {
  constructor(private httpClient: HttpClient) {
  }

  /**
   * Loads specific sport from the backend
   *
   * @param id of sport to load
   */
  getSportById(id: number): Observable<Sport> {
    console.log('Load sport details for ' + id);
    return this.httpClient.get<Sport>(baseUri + '/' + id);
  }

  /**
   * Fetches all sports from the backend.
   */
  getAllSports(): Observable<Sport[]> {
    console.log('Load all sports');
    return this.httpClient.get<Sport[]>(baseUri);
  }

  createSport(sport: Sport): Observable<Sport> {
    console.log('Create new sport', sport);
    return this.httpClient.post<Sport>(
      baseUri,
      sport
    );
  }
}
