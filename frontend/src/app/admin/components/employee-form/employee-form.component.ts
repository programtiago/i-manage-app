import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employee } from '../../../model/Employee';
import { Location } from '@angular/common';
import { AdminService } from '../../services/admin.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../../shared/error-dialog/error-dialog.component';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrl: './employee-form.component.scss'
})
export class EmployeeFormComponent implements OnInit{
  @Input() employee: Employee | null = null;
  @Output() formSubmit = new EventEmitter<Employee>()

  employeeForm!: FormGroup;

  departments: string[] = ['Information Technology', 'Human Resources', 'Administration', 'Production'] //for testing
 
  operations: string[] = ['OPERATION_X', 'OPERATION_Y'] //for testing

  genders: string[] = ['Female', 'Male'] //for testing

  recruitmentCompanys: string[] = ['Intern', 'Randstad', 'Adecco', 'Synergie'] //for testing

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private adminService: AdminService,
    private dialog: MatDialog
  ){}

  ngOnInit(): void {
    this.employeeForm = this.fb.group({
      fullName: [this.employee?.fullName || '', [Validators.minLength(10), Validators.maxLength(100)]],
      workerNo: [this.employee?.workerNo || null, [Validators.min(30000), Validators.max(100000)]],
      email: [this.employee?.email || '', [Validators.minLength(15), Validators.maxLength(60), Validators.email]],
      phoneNumber: [this.employee?.phoneNumber || '', [Validators.minLength(9), Validators.maxLength(14)]],
      recruitmentCompany: [this.employee?.recruitmentCompany || ''],
      operation: [this.employee?.operation || '', Validators.required],
      department: [this.employee?.department || '', Validators.required],
      gender: [this.employee?.gender || '', Validators.required],
      birthdayDate: [this.employee?.birthdayDate || null, Validators.required],
      admissionDate: [this.employee?.admissionDate || null, Validators.required]
    })
  }

  goBack(){
    this.location.back();
  }

  createEmployee(){
    if (this.employeeForm.valid){
      this.adminService.createEmployeeWithUser(this.employeeForm.value).subscribe({
        next: () => {
          
        },
        error: (errorResponse) => {
          const message = errorResponse?.error?.message || 'Unexpected error ocurred.';

          this.openErrorDialog(message)
        }
      })
    }
  }

  openErrorDialog(message: string): void {
    this.dialog.open(ErrorDialogComponent, {
      data: {
        title: 'Error',
        message
      }
    })
  }
}
