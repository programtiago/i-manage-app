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
    'status', 'admissionDate', 'registryDate'];

  departmentMap: { [key: string] : string } = {
    'Tecnologias de Informação': 'TI',
    'Recursos Humanos': 'RH',
    'Produção': 'PROD',
    'Administração': 'ADMIN'
  }

  genreMap: { [key: string]: string } = {
    'Masculino': 'M',
    'MASCULINO': 'M',
    'Feminino': 'F',
    'FEMININO': 'F'

  }


  constructor(){

  }
}
