import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RESTService } from 'src/app/services/rest.service';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoaderService } from 'src/app/services/loader.service';
import { AddArtifactComponent } from './add-artifact/add-artifact.component';

export interface ArtifactModel {
  artifactsID: number;
  artifactName: string;
  dateArrived: number;
  artifactType: string;
  sectionid: number;
  empid: number;
  amount: number;
  acquiredFrom: string;
  quantity: number;
}

const ARTIFACT_DATA: ArtifactModel[] = [
  { artifactsID: 1,
    artifactName: "MonaLisaPainting", 
  dateArrived:28461824, 
  artifactType: 'Painting' ,
  sectionid: 1,
  empid: 1,
  amount: 3000,
  acquiredFrom: "ABC",
  quantity:1
}
];


@Component({
  selector: 'app-artifacts',
  templateUrl: './artifacts.component.html',
  styleUrls: ['./artifacts.component.scss']
})
export class ArtifactsComponent implements OnInit {
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  

  artifact : any="";
  dataSource  = new MatTableDataSource<ArtifactModel>(ARTIFACT_DATA);
  displayedColumns: string[]  = ['artifactsID','artifactName', 'dateArrived','artifactType','sectionName','firstName', 'amount','acquiredFrom','quantity','actions'];

  constructor(public dialog: MatDialog,
    private restService: RESTService,
    private loaderService: LoaderService) { }

  ngOnInit(): void {
    this.showArtifactData();
     
      //this.products = data;
    }
 


  showArtifactData() {
    //this.loaderService.show();
    this.restService.getRequest('artifacts/artifactList').subscribe((data: any[]) => {
      console.log(data);
      //this.loaderService.hide();
      this.dataSource = new MatTableDataSource<ArtifactModel>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openAddDialog() {
    const dialogRef = this.dialog.open(AddArtifactComponent, {
      width: '520px',
      data: ''
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "save") {

      }
    });
  }

  openEditDialog(element) {
    const dialogRef = this.dialog.open(AddArtifactComponent, {
      width: '520px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "save") {
        this.showArtifactData();
      }
    });
  }

  openDeleteDialog(element){
    let id = element.artifactsID;
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '520px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "ok") {
        this.loaderService.show();
        this.restService.deleteRequest('artifacts/delete/' + id).subscribe((data: any[]) => {
          console.log(data);
          this.loaderService.hide();
          this.showArtifactData();
        }, (err) => {
          console.log(err);
          this.loaderService.hide();
          this.showArtifactData();
        });
      }
    });
  }

}


@Component({
  selector: 'dialog-overview-example-dialog',
  template: `
  <h1 mat-dialog-title>Delete Employee</h1>
  <div mat-dialog-content>
    <p>Are you sure you want to delete employee?</p>
  </div>
  <div mat-dialog-actions>
  <button mat-raised-button class="mat-focus-indicator mat-raised-button mat-button-base" (click)="onNoClick()">Cancel</button>
  &nbsp;&nbsp;<button mat-raised-button class="mat-focus-indicator mat-raised-button mat-button-base" (click)="onYesClick()">Ok</button>
  </div>
  `,
})
export class DialogOverviewExampleDialog {

  constructor(public dialogRef: MatDialogRef<DialogOverviewExampleDialog>) 
  {}

  onNoClick(): void {
    this.dialogRef.close();
  }
  onYesClick() {
    this.dialogRef.close("ok");
  }

}
