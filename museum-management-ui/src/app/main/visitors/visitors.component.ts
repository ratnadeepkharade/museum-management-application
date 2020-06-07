import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import {SelectionModel} from '@angular/cdk/collections';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {MatPaginator} from '@angular/material/paginator';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RESTService } from 'src/app/services/rest.service';
import { AddVisitorComponent } from './add-visitor/add-visitor.component';

export interface PeriodicElement {
  visitorId: number;
  firstName: string;
  lastName: string;
  gender: string;
  age:number;
  category:string;
  sectionId:number;
}

export interface DialogData {
  animal: string;
  name: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {visitorId: 1, firstName: 'John', lastName:'Doe', gender: 'H', age:12, category:'Full day', sectionId:9234},
  {visitorId: 1, firstName: 'John', lastName:'Doe', gender: 'H', age:12, category:'Full day', sectionId:9234},
  {visitorId: 1, firstName: 'John', lastName:'Doe', gender: 'H', age:12, category:'Full day', sectionId:9234},
  {visitorId: 1, firstName: 'John', lastName:'Doe', gender: 'H', age:12, category:'Full day', sectionId:9234}
];

@Component({
  selector: 'app-visitors',
  templateUrl: './visitors.component.html',
  styleUrls: ['./visitors.component.scss']
})
export class VisitorsComponent implements OnInit {
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  displayedColumns: string[] = ['select', 'visitorId', 'firstName', 'lastName', 'gender', 'age', 'category', 'sectionId'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);
  selection = new SelectionModel<PeriodicElement>(true, []);

  animal: string;
  name: string;

  constructor(public dialog: MatDialog, private restService:RESTService) {

  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;

    this.restService.getRequest('visitors/visitorList').subscribe((data: any[])=>{
      console.log(data);
      this.dataSource = new MatTableDataSource<PeriodicElement>(data);
    }) 
  }
  
  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
        this.selection.clear() :
        this.dataSource.data.forEach(row => this.selection.select(row));
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: PeriodicElement): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.visitorId + 1}`;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openDialog() {
    const dialogRef = this.dialog.open(AddVisitorComponent, {
      width: '520px',
      data: {name: this.name, animal: this.animal}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
      if(result === "save") {
        //ELEMENT_DATA.push({visitorId: 50, firstName: 'Johnson', lastName:'Doe', gender: 'H', age:12, category:'Full day', sectionId:1})
        //this.dataSource = new MatTableDataSource(ELEMENT_DATA);
        //this.dataSource.paginator = this.paginator;
      }
    });

  }

}