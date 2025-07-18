import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from '../../model/Employee';

const API_URL_EMPLOYEES = '/api/employees'

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getAllEmployees():Observable<Employee[]>{
    return this.http.get<Employee[]>(API_URL_EMPLOYEES)
  }

  createEmployeeWithUser(employee: Employee): Observable<Employee>{
    return this.http.post<Employee>(`${API_URL_EMPLOYEES}/create-with-user`, employee);
  }
}
