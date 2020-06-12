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

  mode = "Add";
 

  employee = {
    firstName: '', lastName:'', emailId: '', roleName:'', sectionName:'', empId:''
  };
  firstName = '';
  constructor(public dialogRef: MatDialogRef<AddEmployeeComponent>,
    private restService: RESTService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public loaderService: LoaderService
  ) {
    if (this.data) {
      this.mode = "Edit";
    }
  }

  ngOnInit(): void {
    console.log(this.data);
    if (this.data) {
      this.employee = this.data;
    }
  }

  onNoClick(): void {
    this.dialogRef.close("cancel");
  }

  onYesClick() {
    if (this.mode === "Add") {
      this.addEmployeeRESTCall();
      this.dialogRef.close("save");
    } else {
      this.editEmployeeRESTCall();
      this.dialogRef.close("save");
    }
  }

  addEmployeeRESTCall() {
    let obj = {firstName: this.employee.firstName, lastName:this.employee.lastName, emailId: this.employee.emailId, roleName:this.employee.roleName, sectionName:this.employee.sectionName};
    this.loaderService.show();
    this.restService.postRequest('employee/add', obj).subscribe((data: any[]) => {
      console.log(data);
      this.loaderService.hide();
      this.dialogRef.close("save");
    }, (err) => {
      console.log(err);
      this.loaderService.hide();
      this.dialogRef.close("save");
    });
  }

  editEmployeeRESTCall() {
    let obj = {empId:this.employee.empId,firstName: this.employee.firstName, lastName:this.employee.lastName, emailId: this.employee.emailId, roleName:this.employee.roleName, sectionName:this.employee.sectionName};
    this.loaderService.show();
    this.restService.putRequest('employee/update', obj).subscribe((data: any[]) => {
      console.log(data);
      this.loaderService.hide();
      this.dialogRef.close("save");
    }, (err) => {
      console.log(err);
      this.loaderService.hide();
      this.dialogRef.close("save");
    });
  }

}
