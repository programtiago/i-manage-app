import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { EmployeesComponent } from './components/employees/employees.component';
import { EmployeesListComponent } from './components/employees-list/employees-list.component';
import { SharedModule } from '../shared/shared.module';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';

@NgModule({
  declarations: [
    EmployeesComponent,
    EmployeesListComponent,
    EmployeeFormComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    SharedModule
]
})
export class AdminModule { }
