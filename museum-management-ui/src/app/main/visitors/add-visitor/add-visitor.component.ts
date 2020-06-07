import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RESTService } from 'src/app/services/rest.service';
import { LoaderService } from 'src/app/services/loader.service';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-add-visitor',
  templateUrl: './add-visitor.component.html',
  styleUrls: ['./add-visitor.component.scss']
})
export class AddVisitorComponent implements OnInit {


  ngOnInit(): void {
  }

  visitor = {
    firstName: '', lastName:'', gender: '', age:'', category:'', sectionId:''
  };
  firstName = '';

  constructor( public dialogRef: MatDialogRef<AddVisitorComponent>, 
    private restService:RESTService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
    public loaderService: LoaderService
  ) {

  }

  onNoClick(): void {
    this.dialogRef.close("cancel");
  }
  onYesClick() {
    let obj = {firstName: this.visitor.firstName, lastName:this.visitor.lastName, gender: this.visitor.gender, age:this.visitor.age, category:this.visitor.category, sectionId:this.visitor.sectionId};
    this.loaderService.show();
    this.restService.postRequest('visitors/add',obj).subscribe((data: any[])=>{
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
