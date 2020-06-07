import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { MainComponent } from './main.component';
import { DemoMaterialModule } from './material-module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { VisitorsComponent } from './visitors/visitors.component';
import { ArtifactsComponent } from './artifacts/artifacts.component';
import { UsersComponent } from './users/users.component';
import { EmployeesComponent } from './employees/employees.component';
import { HttpClientModule } from '@angular/common/http';
import { RESTService } from '../services/rest.service';
import { FormsModule } from '@angular/forms';
import { AddVisitorComponent } from './visitors/add-visitor/add-visitor.component';
import { LoaderService } from '../services/loader.service';


@NgModule({
  declarations: [MainComponent, DashboardComponent, VisitorsComponent, ArtifactsComponent, UsersComponent, EmployeesComponent, AddVisitorComponent],
  imports: [
    CommonModule,
    MainRoutingModule,
    DemoMaterialModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    RESTService,
    LoaderService
  ]
})
export class MainModule { }
