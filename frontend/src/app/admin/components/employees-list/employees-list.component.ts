import { Component, Input } from '@angular/core';
import { Employee } from '../../../model/Employee';

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrl: './employees-list.component.scss'
})
export class EmployeesListComponent {

  @Input() employees: Employee[] = [];

  readonly displayedColumns: string[] = ['fullName', 'email', 'contact', 'recruitmentCompany', 'operation', 'department', 'birthdayDate', 
      'age', 'genre', 'status', 'admissionDate', 'registryDate'];

  constructor(){
    console.log(this.employees)
  }
}
