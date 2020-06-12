import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RESTService } from 'src/app/services/rest.service';
import {AddEmployeeComponent} from "./add-employee/add-employee.component"
import { LoaderService } from 'src/app/services/loader.service';

export interface EmployeeModel {
  empId: number;
  firstName: string;
  lastName: string;
  roleName: string;
  sectionName:string;
  emailId:string;
}

const EMPLOYEE_DATA: EmployeeModel[] = [
  { empId: 1, firstName: 'John', lastName: 'Doe', roleName: 'PRO', sectionName: 'Gallery', emailId: 'p@gmail.com' }
];

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  displayedColumns: string[] = ['empId','firstName', 'lastName','roleName','sectionName','emailId', 'actions'];
  dataSource = new MatTableDataSource<EmployeeModel>(EMPLOYEE_DATA);

  constructor(public dialog: MatDialog,
    private restService: RESTService,
    private loaderService: LoaderService) {
      
  }

  ngOnInit() {
    this.showEmployeeData();
  }

  showEmployeeData() {
    //this.loaderService.show();
    this.restService.getRequest('employee/employeeList').subscribe((data: any[]) => {
      console.log(data);
      //this.loaderService.hide();
      this.dataSource = new MatTableDataSource<EmployeeModel>(data);
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
    const dialogRef = this.dialog.open(AddEmployeeComponent, {
      width: '520px',
      data: ''
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "save") {

      }
    });
  }

  openEditDialog(element) {
    const dialogRef = this.dialog.open(AddEmployeeComponent, {
      width: '520px',
      data: element
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "save") {
        this.showEmployeeData();
      }
    });
  }

  openDeleteDialog(element){
    let id = element.empId;
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '520px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === "ok") {
        this.loaderService.show();
        this.restService.deleteRequest('employee/delete/' + id).subscribe((data: any[]) => {
          console.log(data);
          this.loaderService.hide();
          this.showEmployeeData();
        }, (err) => {
          console.log(err);
          this.loaderService.hide();
          this.showEmployeeData();
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