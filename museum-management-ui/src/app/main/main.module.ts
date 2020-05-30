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


@NgModule({
  declarations: [MainComponent, DashboardComponent, VisitorsComponent, ArtifactsComponent, UsersComponent, EmployeesComponent],
  imports: [
    CommonModule,
    MainRoutingModule,
    DemoMaterialModule
  ]
})
export class MainModule { }
