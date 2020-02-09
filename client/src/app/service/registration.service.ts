import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppSettings} from '../app-settings/app-settings';
import {FormResponse} from '../types/formResponse';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) {
  }

  startRegistrationProcess() {
    return this.http.get<FormResponse>(AppSettings.REGISTRATION_BASE_URL);
  }

  submitRegistrationForm(taskId, value) {
    return this.http.post(AppSettings.REGISTRATION_BASE_URL.concat('/' + taskId), value);
  }


}
