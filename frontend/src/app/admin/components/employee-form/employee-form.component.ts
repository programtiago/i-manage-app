import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Employee } from '../../../model/Employee';
import { Location } from '@angular/common';
import { AdminService } from '../../services/admin.service';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../../../shared/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrl: './employee-form.component.scss'
})
export class EmployeeFormComponent implements OnInit{
  @Input() employee: Employee | null = null;

  employeeForm!: FormGroup;

  departments: string[] = ['Information Technology', 'Human Resources', 'Administration', 'Production'] //for testing
 
  operations: string[] = ['OPERATION_X', 'OPERATION_Y'] //for testing

  genders: string[] = ['Female', 'Male'] //for testing

  recruitmentCompanys: string[] = ['Intern', 'Randstad', 'Adecco', 'Synergie'] //for testing

  isEditMode: boolean = false;
  workNo!: number;

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private adminService: AdminService,
    private dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
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

    this.route.paramMap.subscribe(params => {
      const workerNo = params.get('workerNo');
      if (workerNo){
        this.isEditMode = true;
        this.workNo =+ workerNo;
        this.loadEmployeeByWorkerNumber(workerNo)
      }
    })
  }

  loadEmployeeByWorkerNumber(workerNumber: string){
    this.adminService.getEmployeeByWorkerNo(workerNumber).subscribe(emp => {
      this.employeeForm.patchValue(emp);
    })
  }

  goBack(){
    this.location.back();
  }

  createEmployee(){
    if (this.employeeForm.valid){
      const empFormValues = this.employeeForm.value;

      const empDataToSend = {
        ...empFormValues,
        birthdayDate: this.formatDate(empFormValues.birthdayDate),
        admissionDate: this.formatDate(empFormValues.admissionDate)
      }
      this.adminService.createEmployeeWithUser(empDataToSend).subscribe({
        next: () => {
          this.router.navigateByUrl("admin/employees")
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
      },
      closeOnNavigation: false,
      enterAnimationDuration: '100ms'
    })
  }

  formatDate(date: Date):string | null {
    if (!date) return null;
    return date.toISOString().split('T')[0];
  }

  updateEmployee(employee: Employee, workerNumber: number){
    this.adminService.updateEmployee(employee, workerNumber).subscribe({
      next: (updatedEmp) => {
        console.log('Employee updated:', updatedEmp);
        this.router.navigate(['admin/employees'])
      },
      error: (err) => {
        console.log('Error updating employee:', err);
      }
    })
  }
}
