import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from '../../../model/User';
import { Employee } from '../../../model/Employee';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-user-details-dialog',
  templateUrl: './user-details-dialog.component.html',
  styleUrl: './user-details-dialog.component.scss'
})
export class UserDetailsDialogComponent implements OnInit{

  userRoles: string[] = ['PROD', 'ADMIN', 'HR', 'TI']
  
  userFormGroup: FormGroup;

  employee!: Employee;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<UserDetailsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User,
    private adminService: AdminService
  ){
    this.userFormGroup = this.fb.group({
      username: [data.username],
      password: [data.password],
      createdAt: [{ value: data.createdAt, disabled: true}],
      updatedAt: [{ value: data.updatedAt, disabled: true}],
      userRoles: [data.userRoles],
    })
  }

  ngOnInit(): void {
    this.adminService.getEmployeeByWorkerNo(this.data.username.toString()).subscribe((res) => {
      this.employee = res;
    })
  }

  onCancel(){
    this.dialogRef.close();
  }

  onSave(){
    if (this.userFormGroup.valid){
      
    }
  }
}
