import { Component, Input } from '@angular/core';
import { Employee } from '../../../model/Employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrl: './employees-list.component.scss'
})
export class EmployeesListComponent {

  @Input() employees: Employee[] = [];

  readonly displayedColumns: string[] = ['workerNo', 'fullName', 'email', 'contact', 'recruitmentCompany', 'operation', 'department', 'birthdayDate', 
    'status', 'admissionDate', 'registryDate', 'actions'];

  departmentMap: { [key: string] : string } = {
    'Information Technology': 'TI',
    'Human Resources': 'HR',
    'Production': 'PROD',
    'Administration': 'ADMIN'
  }
  
  constructor(private router: Router){}

  openEmployeeForm(workerNo: string){
    this.router.navigateByUrl("admin/employee/edit/" + workerNo);
  }

  viewEmployeeUserDetails(){
    
  }
}
