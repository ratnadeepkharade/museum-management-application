import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RESTService {
  private REST_API_SERVER = "http://localhost:8080/api/";

  constructor(private http:HttpClient) { }

  getRequest(url){
    return this.http.get(this.REST_API_SERVER + url);
  }
}
