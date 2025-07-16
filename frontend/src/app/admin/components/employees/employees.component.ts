import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../../../model/Employee';
import { AdminService } from '../../services/admin.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrl: './employees.component.scss'
})
export class EmployeesComponent implements OnInit{

    employees$: Observable<Employee[]>

    constructor(private adminService: AdminService){
      this.employees$ = this.adminService.getAllEmployees();
    }
    
    ngOnInit(): void {
    }
}
