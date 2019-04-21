import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
import { User, PageUser } from '../model/user';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  //url = "http://35.239.12.238:8080/"; K8s  
  url = "https://efx-pocs-niran.appspot.com/";  //App Engine
  constructor(private http: HttpClient) { }  
UserDetails(page : number): Observable<PageUser> {
  return this.http.get<PageUser>(this.url + 'api/employees?pagenumber=' + page + "&size=50");
  }
}
