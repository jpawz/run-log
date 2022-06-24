import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RunLogListRoutingModule } from './run-log-list-routing.module';
import { RunLogListComponent } from './run-log-list.component';


@NgModule({
  declarations: [
    RunLogListComponent
  ],
  imports: [
    CommonModule,
    RunLogListRoutingModule
  ]
})
export class RunLogListModule { }
