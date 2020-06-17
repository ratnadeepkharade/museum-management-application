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

  constructor(
    private restService: RESTService,
    private loaderService: LoaderService) {
      
  }

  ngOnInit() {
    this.getCounts();;
  }
  getCounts() {
    this.getCurrentVisitorCount();
  }
  getCurrentVisitorCount() {
    //this.loaderService.show();
    this.restService.getRequest('dashboard/count/1').subscribe((data: any[]) => {
      console.log(data);
      //this.loaderService.hide();
    })
  }

}
