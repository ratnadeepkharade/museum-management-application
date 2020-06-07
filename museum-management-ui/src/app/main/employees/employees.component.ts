import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {
  private REST_API_SERVER = "http://localhost:8080/api/employee/employeeList";

 
  dataSource  = [];
  tableColumns  :  string[] = ['checked','firstName', 'lastName','roleName','sectionName','emailId'];
  constructor(private http:HttpClient) { }
  ngOnInit(): void {
    this.sendGetRequest().subscribe((data: any[])=>{
      console.log(data);
      this.dataSource = data;
     
      //this.products = data;
    })  
    //console.log( this.http.get(this.REST_API_SERVER));
}

public sendGetRequest(){
  return this.http.get(this.REST_API_SERVER);

}
}
