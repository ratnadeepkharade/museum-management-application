import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoaderService {

  constructor() { }

  public showLoader = false;

  show(){
    this.showLoader = true;
  }

  hide(){
    this.showLoader = false;
  }
}
