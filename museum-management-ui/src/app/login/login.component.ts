import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private REST_API_SERVER = "http://localhost:8080/api/login/user";

  username = "";
  password = "";

  constructor(private http: HttpClient, private router: Router) { }


  ngOnInit(): void {
   
  }

  public sendGetRequest() {
    return this.http.get(this.REST_API_SERVER);
  }

  login() {
    console.log('in login');
    if ( !this.username || !this.password) {

    } else {
      this.sendGetRequest().subscribe((data: any[]) => {
        console.log(data);
        //this.products = data;
        this.router.navigate(['/main']);
      });
    }
    
  }

}
