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

  readonly displayedColumns: string[] = ['fullName', 'email', 'contact', 'recruitmentCompany', 'operation', 'department', 'birthdayDate', 
    'status', 'admissionDate', 'registryDate'];

  departmentMap: { [key: string] : string } = {
    'Tecnologias de Informação': 'TI',
    'Recursos Humanos': 'RH',
    'Produção': 'PROD',
    'Administração': 'ADMIN'
  }
  
  constructor(private router: Router){}

  openEmployeeForm(employee: Employee){
    //this.router.navigateByUrl("admin/new-employee")
  }
}
