import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeesComponent } from './components/employees/employees.component';
import { EmployeeFormComponent } from './components/employee-form/employee-form.component';

const routes: Routes = [
  {
    path: 'employees',
    component: EmployeesComponent
  },

  {
    path: 'employee/create',
    component: EmployeeFormComponent
  },

  {
    path: 'employee/edit/:workerNo',
    component: EmployeeFormComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
