import { Component, OnInit } from '@angular/core';
import { LoaderService } from '../services/loader.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {
  showFiller = false;

  constructor(public loaderService: LoaderService) { }

  ngOnInit(): void {
  }

}
