import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EquipamentoRoutingModule } from './equipamento-routing.module';
import { CrudEquipamentoComponent } from './crud-equipamento/crud-equipamento.component';


@NgModule({
  declarations: [CrudEquipamentoComponent],
  imports: [
    CommonModule,
    EquipamentoRoutingModule
  ]
})
export class EquipamentoModule { }
