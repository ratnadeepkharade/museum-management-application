import { Component, OnInit } from '@angular/core';
import { RESTService } from 'src/app/services/rest.service';
import { LoaderService } from 'src/app/services/loader.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  currentCount: any = ''
  totalCount: any = ''
  sectionCount: any = ''
  artifactCount: any = ''

  constructor(
    private restService: RESTService,
    private loaderService: LoaderService) {
  }

  public barChartOptions = {
    scaleShowVerticalLines: false,
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  };
  public barChartLabels = ['Jan', 'Feb', 'March', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
  public barChartType = 'line';
  public barChartLegend = true;
  public barChartData = [
    { data: [280, 480, 400, 200, 550, 270, 500, 700, 500, 300, 550, 550], label: 'Monthly Visitors' }
  ];

  public barChartOptions2 = {
    scaleShowVerticalLines: false,
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }

      }]
    }
  };
  public barChartLabels2 = ['Gallery', 'History', 'Paintings', 'Sculptures', 'Modern Art'];
  public barChartType2 = 'bar';
  public barChartLegend2 = true;
  public barChartData2 = [
    {
      data: [280, 480, 400, 200, 550],
      label: 'Visitors',
      backgroundColor: ["rgba(191, 18, 93, 0.6)", "rgba(210, 65, 24, 0.7)", "rgba(132, 21, 210,0.7)", "rgba(202, 33, 120, 0.7)", "rgba(132, 21, 210, 0.7)"],
      barThickness: 25,
      maxBarThickness: 25,
      minBarLength: 10
    }
  ];

  ngOnInit() {
    this.getCounts();
  }
  getCounts() {
    this.getCurrentVisitorCount(1);
    this.getCurrentVisitorCount(2);
    this.getCurrentVisitorCount(3);
    this.getCurrentVisitorCount(4);
    this.getMonthlyCountRestCall();
    this.getTopSectionsRestCall();
  }
  getCurrentVisitorCount(type) {
    //this.loaderService.show();
    this.restService.getRequest('dashboard/count/' + type).subscribe((data) => {
      console.log(data);
      if (type === 1) {
        this.currentCount = data;
      }
      if (type === 2) {
        this.totalCount = data;
      }
      if (type === 3) {
        this.artifactCount = data;
      }
      if (type === 4) {
        this.sectionCount = data;
      }
      //this.loaderService.hide();
    })
  }

  getMonthlyCountRestCall(){
    this.restService.getRequest('dashboard/monthlyCount').subscribe((data:any) => {
      console.log(data);
      this.barChartLabels = [];
      this.barChartData[0].data = []
      for (let i = 0; i< data.length; i++) {
        this.barChartLabels.push(data[i].monthId);
        this.barChartData[0].data.push(data[i].count)
      }
      //this.loaderService.hide();
    })
  }

  getTopSectionsRestCall(){
    this.restService.getRequest('dashboard/topSections').subscribe((data:any) => {
      console.log(data);
      this.barChartLabels2 = [];
      this.barChartData2[0].data = []
      for (let i = 0; i< data.length; i++) {
        this.barChartLabels2.push(data[i].sectionName);
        this.barChartData2[0].data.push(data[i].count)
      }
      //this.loaderService.hide();
    })
  }

}
