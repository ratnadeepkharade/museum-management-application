import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private REST_API_SERVER = "http://localhost:8080/api/login/user";

  constructor(private http:HttpClient) { }


  ngOnInit(): void {
    this.sendGetRequest().subscribe((data: any[])=>{
      console.log(data);
      //this.products = data;
    })  
    //console.log( this.http.get(this.REST_API_SERVER));
}

public sendGetRequest(){
  return this.http.get(this.REST_API_SERVER);
}

}
