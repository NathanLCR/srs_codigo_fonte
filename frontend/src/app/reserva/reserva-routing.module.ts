import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ReservasComponent} from './components/reservas.component';



const routes: Routes = [
  {
    path: 'reservas',
    component: ReservasComponent

  }
]
  
  

@NgModule({ 
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ReservaRoutingModule { }
