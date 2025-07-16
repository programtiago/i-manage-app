import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../../../model/Employee';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrl: './employees.component.scss'
})
export class EmployeesComponent implements OnInit{

    constructor(private adminService: AdminService){}
  
    employees: Employee[] = [];
    displayedColumns: string[] = ['fullName', 'email', 'contact', 'recruitmentCompany', 'operation', 'department', 'birthdayDate', 
      'age', 'genre', 'status', 'admissionDate', 'registryDate'];

    
    ngOnInit(): void {
      this.getEmployees();
    }  

    getEmployees(): void {
      this.adminService.getAllEmployees()
        .subscribe(emps => {
          this.employees = emps;
          console.log(emps);
        })
    }
}
