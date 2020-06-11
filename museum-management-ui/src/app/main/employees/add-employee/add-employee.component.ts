import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RESTService } from 'src/app/services/rest.service';
import { LoaderService } from 'src/app/services/loader.service';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.scss']
})
export class AddEmployeeComponent implements OnInit {


  ngOnInit(): void {
  }

  employee = {
    firstName: '', lastName:'', emailId: '', roleName:'', sectionName:''
  };
  firstName = '';

  constructor( public dialogRef: MatDialogRef<AddEmployeeComponent>, 
    private restService:RESTService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    public loaderService: LoaderService
  ) {

  }

  onNoClick(): void {
    this.dialogRef.close("cancel");
  }
  onYesClick() {
    let obj = {firstName: this.employee.firstName, lastName:this.employee.lastName, emailId: this.employee.emailId, roleName:this.employee.roleName, sectionName:this.employee.sectionName};
    this.loaderService.show();
    this.restService.postRequest('employee/add',obj).subscribe((data: any[])=>{
      console.log(data);
      this.loaderService.hide();
      this.dialogRef.close("save");
    },
    (err) => {
      console.log(err);
      this.loaderService.hide();
      this.dialogRef.close("save");
    });
  }

}
