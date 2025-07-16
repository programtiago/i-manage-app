import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { AppMaterialModule } from '../shared/material/app-material.module';
import { MatToolbar, MatToolbarRow } from "@angular/material/toolbar";

@NgModule({
  declarations: [
    HeaderComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
    MatToolbar,
    MatToolbarRow
  ],
  exports: [
    HeaderComponent
  ]
})
export class CoreModule { }
