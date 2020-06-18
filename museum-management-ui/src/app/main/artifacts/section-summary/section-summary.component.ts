import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { RESTService } from 'src/app/services/rest.service';
import { LoaderService } from 'src/app/services/loader.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

export interface SummaryModel {
  sectionid: number;
  sectionName: string;
  artifactCount: number;
  TotalAmount: number;
  
}

const SUMMARY_DATA: SummaryModel[] = [
  { sectionid: 1,
    sectionName: "Main",
    artifactCount: 0,
    TotalAmount: 0
   
}
];
@Component({
  selector: 'app-section-summary',
  templateUrl: './section-summary.component.html',
  styleUrls: ['./section-summary.component.scss']
})
export class SectionSummaryComponent implements OnInit {
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  dataSource  = new MatTableDataSource<SummaryModel>(SUMMARY_DATA);
  displayedColumns: string[]  = ['sectionid','sectionName', 'artifactCount','TotalAmount'];
 

  constructor(public dialog: MatDialog,
    private restService: RESTService,
    private loaderService: LoaderService) { }

  ngOnInit(): void {
    this.showSummaryData();
     
      //this.products = data;
    }
 


    showSummaryData() {
      //this.loaderService.show();
      this.restService.getRequest('summary/addsummaryList').subscribe((data: any[]) => {
        console.log(data);
        //this.loaderService.hide();
        this.dataSource = new MatTableDataSource<SummaryModel>(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      })
    }

 
  
}
