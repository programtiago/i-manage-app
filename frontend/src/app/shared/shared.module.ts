import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppMaterialModule } from './material/app-material.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AppMaterialModule
  ],
  exports: [
    AppMaterialModule
  ]
})
export class SharedModule { }
