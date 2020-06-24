import { NgModule, LOCALE_ID } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { ChartsModule } from 'ng2-charts';
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
import { AddEmployeeComponent } from './employees/add-employee/add-employee.component';
import { AddArtifactComponent } from './artifacts/add-artifact/add-artifact.component';
import { SectionSummaryComponent } from './artifacts/section-summary/section-summary.component';


@NgModule({
  declarations: [
    MainComponent, 
    DashboardComponent, 
    VisitorsComponent, 
    ArtifactsComponent, 
    UsersComponent, 
    EmployeesComponent, 
    AddVisitorComponent, 
    AddEmployeeComponent, 
    AddArtifactComponent, SectionSummaryComponent
    
  ],
  imports: [
    CommonModule,
    MainRoutingModule,
    DemoMaterialModule,
    FormsModule,
    HttpClientModule,
    ChartsModule
  ],
  providers: [
    RESTService,
    LoaderService,
    DatePipe
  ]
})
export class MainModule { }
