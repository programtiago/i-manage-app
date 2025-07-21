import { Component, Input } from '@angular/core';
import { Employee } from '../../../model/Employee';
import { Router } from '@angular/router';
import { User } from '../../../model/User';
import { MatDialog } from '@angular/material/dialog';
import { UserDetailsDialogComponent } from '../user-details-dialog/user-details-dialog.component';

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrl: './employees-list.component.scss'
})
export class EmployeesListComponent {

   constructor(
    private router: Router,
    private dialog: MatDialog
  ){}

  @Input() employees: Employee[] = [];

  readonly displayedColumns: string[] = ['workerNo', 'fullName', 'email', 'contact', 'recruitmentCompany', 'operation', 'department', 'birthdayDate', 
    'status', 'admissionDate', 'registryDate', 'actions'];

  departmentMap: { [key: string] : string } = {
    'Information Technology': 'TI',
    'Human Resources': 'HR',
    'Production': 'PROD',
    'Administration': 'ADMIN'
  }

  openEmployeeForm(workerNo: string){
    this.router.navigateByUrl("admin/employee/edit/" + workerNo);
  }

  viewEmployeeUserDetails(user: User){
    const dialogRef = this.dialog.open(UserDetailsDialogComponent, {
      width: '450px',
      data: user,
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result){
        console.log('Dialog closed with updated user:', result);
      }
    })
  }

  onUserAccountViewDetailsClick(event: MouseEvent, user: User): void {
    event.stopPropagation(); 
    this.viewEmployeeUserDetails(user);
  }
}
