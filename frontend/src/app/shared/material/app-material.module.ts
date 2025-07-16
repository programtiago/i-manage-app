import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatToolbarModule,
    MatTableModule,
    MatCardModule,
    MatProgressSpinnerModule
  ],
  exports: [
    MatToolbarModule,
    MatTableModule,
    MatCardModule,
    MatProgressSpinnerModule
  ]
})
export class AppMaterialModule { }
