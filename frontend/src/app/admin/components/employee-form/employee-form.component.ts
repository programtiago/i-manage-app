import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employee } from '../../../model/Employee';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrl: './employee-form.component.scss'
})
export class EmployeeFormComponent implements OnInit{
  @Input() employee: Employee | null = null;
  @Output() formSubmit = new EventEmitter<Employee>()

  employeeForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
  ){}

  ngOnInit(): void {
    this.employeeForm = this.fb.group({
      fullName: [this.employee?.fullName || '', Validators.required],
      workerNumber: [this.employee?.workerNo || '', Validators.required],
      email: [this.employee?.email || '', [Validators.required, Validators.email]],
      contact: [this.employee?.phoneNumber || '', Validators.required],
      recruitmentCompany: [this.employee?.recruitmentCompany || ''],
      operation: [this.employee?.operation || '', Validators.required],
      department: [this.employee?.department || '', Validators.required],
      birthdayDate: [this.employee?.birthdayDate || '', Validators.required],
      admissionDate: [this.employee?.admissionDate || '', Validators.required]
    })
  }
}
