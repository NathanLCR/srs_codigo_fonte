import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CrudEquipamentoComponent} from './crud-equipamento/crud-equipamento.component';

const routes: Routes = [
  {path: "/", component:CrudEquipamentoComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EquipamentoRoutingModule { }
