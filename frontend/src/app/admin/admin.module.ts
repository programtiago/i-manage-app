import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { EmployeesComponent } from './components/employees/employees.component';
import { EmployeesListComponent } from './components/employees-list/employees-list.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    EmployeesComponent,
    EmployeesListComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule
  ]
})
export class AdminModule { }
