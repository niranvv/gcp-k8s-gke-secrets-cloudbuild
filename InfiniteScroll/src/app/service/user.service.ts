import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
import { User } from '../model/user';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = "http://35.239.242.232:8080/";  
  constructor(private http: HttpClient) { }  
UserDetails(page : number): Observable<User[]> {
  return this.http.get<User[]>(this.url + 'api/employees?pageNumber=' + page);
  }
}
