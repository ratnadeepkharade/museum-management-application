import { Component, OnInit, Inject } from '@angular/core';
import { RESTService } from 'src/app/services/rest.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { LoaderService } from 'src/app/services/loader.service';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-add-artifact',
  templateUrl: './add-artifact.component.html',
  styleUrls: ['./add-artifact.component.scss']
})
export class AddArtifactComponent implements OnInit {

  mode = "Add";
 

  artifact = {
    artifactid: '',artifactName: '', artifactType: '',sectionid: '',empid: '', amount:'', acquiredFrom: '',quantity: '',width:'',height:'',artist:'',weight:'',artistsculpt:''
  };
  artifactName = '';
  constructor(public dialogRef: MatDialogRef<AddArtifactComponent>,
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
      this.artifact = this.data;
    }
  }

  onNoClick(): void {
    this.dialogRef.close("cancel");
  }

  onYesClick() {
    if (this.mode === "Add") {
      this.addArtifactRESTCall();
      this.dialogRef.close("save");
      
    } else {
      this.editArtifactRESTCall();
      this.dialogRef.close("save");
    }
  }

  addArtifactRESTCall() {
    let obj = {artifactName: this.artifact.artifactName, artifactType: this.artifact.artifactType,sectionid: this.artifact.sectionid,empid: this.artifact.empid, amount:this.artifact.amount, acquiredFrom: this.artifact.acquiredFrom,quantity: this.artifact.quantity,height: this.artifact.height,width: this.artifact.width,artist: this.artifact.artist,weight: this.artifact.weight,artistsculpt: this.artifact.artistsculpt};
    this.loaderService.show();
    this.restService.postRequest('artifacts/add', obj).subscribe((data: any[]) => {
      console.log(data);
      this.loaderService.hide();
      this.dialogRef.close("save");
      
    }, (err) => {
      console.log(err);
      this.loaderService.hide();
      this.dialogRef.close("save");
    });
  }

  editArtifactRESTCall() {
    let obj = {artifactid: this.artifact.artifactid,artifactName: this.artifact.artifactName,  artifactType: this.artifact.artifactType,sectionid: this.artifact.sectionid,empid: this.artifact.empid, amount:this.artifact.amount, acquiredFrom: this.artifact.acquiredFrom,quantity: this.artifact.quantity};
    this.loaderService.show();
    this.restService.putRequest('artifacts/update', obj).subscribe((data: any[]) => {
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
