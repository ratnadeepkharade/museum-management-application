import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RESTService } from 'src/app/services/rest.service';
import { LoaderService } from 'src/app/services/loader.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  currentCount:any=''
  totalCount:any=''
  sectionCount:any=''
  artifactCount:any=''

  constructor(
    private restService: RESTService,
    private loaderService: LoaderService) {
      
  }

  ngOnInit() {
    this.getCounts();;
  }
  getCounts() {
    this.getCurrentVisitorCount(1);
    this.getCurrentVisitorCount(2);
    this.getCurrentVisitorCount(3);
    this.getCurrentVisitorCount(4);
  }
  getCurrentVisitorCount(type) {
    //this.loaderService.show();
    this.restService.getRequest('dashboard/count/'+ type).subscribe((data) => {
      console.log(data);
      if(type === 1){
        this.currentCount= data;
      }
      if(type === 2){
        this.totalCount= data;
      }
      if(type === 3){
        this.artifactCount= data;
      }
      if(type === 4){
        this.sectionCount= data;
      }
      //this.loaderService.hide();
    })
  }

}
