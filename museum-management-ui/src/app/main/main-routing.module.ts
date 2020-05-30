import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { MainComponent } from './main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { VisitorsComponent } from './visitors/visitors.component';
import { ArtifactsComponent } from './artifacts/artifacts.component';
import { UsersComponent } from './users/users.component';
import { EmployeesComponent } from './employees/employees.component';

const routes: Routes = [
  {
    path: '', component: MainComponent,
    children: [
      { path: '', redirectTo: 'dashboard' },
      {
        path: 'dashboard', component: DashboardComponent
      },
      {
        path: 'visitors', component: VisitorsComponent
      },
      {
        path: 'artifacts', component: ArtifactsComponent
      },
      {
        path: 'users', component: UsersComponent
      },
      {
        path: 'employees', component: EmployeesComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
