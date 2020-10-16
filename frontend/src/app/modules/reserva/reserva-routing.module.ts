import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarReservasComponent } from './components/listar-reservas/listar-reservas.component';
import { ManterReservasComponent } from './components/manter-reservas/manter-reservas.component';


const routes: Routes = [
  {
    path: 'reservas',
    component: ListarReservasComponent

  },
  {
    path:'cadastrar-reservas',
    component: ManterReservasComponent
  }
  ,
  {
    path: 'editar-reservas/:id',
    component: ManterReservasComponent
  }
  ];

@NgModule({ 
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservaRoutingModule { }
