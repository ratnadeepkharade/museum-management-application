import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RESTService } from 'src/app/services/rest.service';
import { LoaderService } from 'src/app/services/loader.service';

@Component({
  selector: 'app-add-visitor',
  templateUrl: './add-visitor.component.html',
  styleUrls: ['./add-visitor.component.scss']
})
export class AddVisitorComponent implements OnInit {

  mode = "Add";

  visitor = {
    firstName: '', lastName: '', gender: '', age: '', category: '', sectionId: '', visitorId: ''
  };

  sections = [];

  constructor(public dialogRef: MatDialogRef<AddVisitorComponent>,
    private restService: RESTService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public loaderService: LoaderService
  ) {
    if (this.data) {
      this.mode = "Edit";
    }
  }

  ngOnInit(): void {
    //console.log(this.data);
    if (this.data) {
      this.visitor = this.data;
      this.getSectionsRESTCall();
    }
  }

  onNoClick(): void {
    this.dialogRef.close("cancel");
  }

  onYesClick() {
    if (this.mode === "Add") {
      this.addVisitorRESTCall();
    } else {
      this.editVisitorRESTCall();
    }
  }

  getSectionsRESTCall(){
    this.restService.getRequest('sections/sectionList').subscribe((data: any[]) => {
      //console.log(data);
      this.sections = data;
    }, (err) => {
      //console.log(err);
    });
  }

  addVisitorRESTCall() {
    let obj = {
      firstName: this.visitor.firstName,
      lastName: this.visitor.lastName,
      gender: this.visitor.gender,
      age: this.visitor.age,
      category: this.visitor.category,
      sectionId: 1
    };
    this.loaderService.show();
    this.restService.postRequest('visitors/add', obj).subscribe((data: any[]) => {
      //console.log(data);
      this.loaderService.hide();
      this.dialogRef.close("save");
    }, (err) => {
      //console.log(err);
      this.loaderService.hide();
      this.dialogRef.close("save");
    });
  }

  editVisitorRESTCall() {
    let obj = {
      visitorId: this.visitor.visitorId,
      firstName: this.visitor.firstName,
      lastName: this.visitor.lastName,
      gender: this.visitor.gender,
      age: this.visitor.age,
      category: this.visitor.category,
      sectionId: this.visitor.sectionId
    };
    this.loaderService.show();
    this.restService.putRequest('visitors/update', obj).subscribe((data: any[]) => {
      //console.log(data);
      this.loaderService.hide();
      this.dialogRef.close("save");
    }, (err) => {
      //console.log(err);
      this.loaderService.hide();
      this.dialogRef.close("save");
    });
  }

}
