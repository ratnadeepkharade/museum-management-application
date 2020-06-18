import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RESTService } from 'src/app/services/rest.service';
import { AddVisitorComponent } from './add-visitor/add-visitor.component';
import { LoaderService } from 'src/app/services/loader.service';

export interface VisitorModel {
  visitorId: number;
  firstName: string;
  lastName: string;
  gender: string;
  entryDate: number;
  age: number;
  category: string;
  sectionId: number;
  sectionName: string;
}

const VISITOR_DATA: VisitorModel[] = [
  { visitorId: 1, firstName: 'John', lastName: 'Doe', entryDate:28461824, gender: 'H', age: 12, category: 'Full day', sectionId: 1131, sectionName: '9234' }
];

@Component({
  selector: 'app-visitors',
  templateUrl: './visitors.component.html',
  styleUrls: ['./visitors.component.scss']
})
export class VisitorsComponent implements OnInit {
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  displayedColumns: string[] = ['visitorId', 'firstName', 'lastName', 'entryDate' ,'gender', 'age', 'category', 'sectionName', 'actions'];
  dataSource = new MatTableDataSource<VisitorModel>(VISITOR_DATA);

  constructor(public dialog: MatDialog,
    private restService: RESTService,
    private loaderService: LoaderService) {
      
  }

  ngOnInit() {
    this.showVisitorData();
  }

  showVisitorData() {
    //this.loaderService.show();
    this.restService.getRequest('visitors/visitorList').subscribe((data: any[]) => {
      console.log(data);
      //this.loaderService.hide();
      this.dataSource = new MatTableDataSource<VisitorModel>(data);
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
    const dialogRef = this.dialog.open(AddVisitorComponent, {
      width: '520px',
      data: ''
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "save") {
        this.showVisitorData();
      }
    });
  }

  openEditDialog(element) {
    const dialogRef = this.dialog.open(AddVisitorComponent, {
      width: '520px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "save") {
        this.showVisitorData();
      }
    });
  }

  openDeleteDialog(element){
    let id = element.visitorId;
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '520px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "ok") {
        this.loaderService.show();
        this.restService.deleteRequest('visitors/delete/' + id).subscribe((data: any[]) => {
          console.log(data);
          this.loaderService.hide();
          this.showVisitorData();
        }, (err) => {
          console.log(err);
          this.loaderService.hide();
          this.showVisitorData();
        });
      }
    });
  }
}


@Component({
  selector: 'dialog-overview-example-dialog',
  template: `
  <h1 mat-dialog-title>Delete Visitor</h1>
  <div mat-dialog-content>
    <p>Are you sure you want to delete visitor?</p>
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