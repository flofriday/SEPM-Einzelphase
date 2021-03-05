import {Component, OnInit} from '@angular/core';
import {SportService} from '../../service/sport.service';
import {Sport} from '../../dto/sport';

@Component({
  selector: 'app-sport',
  templateUrl: './sport.component.html',
  styleUrls: ['./sport.component.scss']
})
export class SportComponent implements OnInit {

  error = false;
  errorMessage = '';
  sport: Sport;

  constructor(private sportService: SportService) {
  }

  ngOnInit() {
    this.loadSport(-1);
  }

  /**
   * Error flag will be deactivated, which clears the error message
   */
  vanishError() {
    this.error = false;
  }

  /**
   * Loads the sport for the specified id
   *
   * @param id the id of the sport
   */
  private loadSport(id: number) {
    this.sportService.getSportById(id).subscribe(
      (sport: Sport) => {
        this.sport = sport;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }


  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }

}
