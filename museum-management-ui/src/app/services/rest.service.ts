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

  postRequest(url, data){
    return this.http.post(this.REST_API_SERVER + url, data);
  }

  putRequest(url, data){
    return this.http.put(this.REST_API_SERVER + url, data);
  }

  deleteRequest(url){
    return this.http.delete(this.REST_API_SERVER + url);
  }

}
