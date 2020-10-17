import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReservaRoutingModule } from './reserva-routing.module';
import { ListarReservasComponent } from './components/listar-reservas/listar-reservas.component';
import { ManterReservasComponent } from './components/manter-reservas/manter-reservas.component';
import { HttpClientModule } from '@angular/common/http';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [ListarReservasComponent, ManterReservasComponent],
  imports: [
    CommonModule,
    ReservaRoutingModule,
    HttpClientModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class ReservaModule { }
