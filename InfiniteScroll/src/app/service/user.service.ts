import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
import { User } from '../model/user';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = "http://localhost:63278/";  
  constructor(private http: HttpClient) { }  
UserDetails(page : number): Observable<User[]> {
  return this.http.get<User[]>(this.url + 'Api/AngularAPI/AllUserDetails?pageNumber=' + page);
  }
}
