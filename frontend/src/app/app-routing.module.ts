import { SalaComponent } from './sala/sala.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DiarioErrosComponent } from './components/diario-erros/diario-erros.component';
import { LoginSuccessComponent } from '@nuvem/angular-base';
import {EquipamentoComponent} from './equipamento/equipamento.component';
import { ClienteComponent } from './cliente/cliente.component';
import { ReservasComponent } from './reserva/reservas.component';

const routes: Routes = [
    { path: 'diario-erros', component: DiarioErrosComponent, data: { breadcrumb: 'Diário de Erros'} },
    { path: 'login-success', component: LoginSuccessComponent },
    { path: 'equipamentos', component: EquipamentoComponent },
    { path: 'clientes', component: ClienteComponent},
    { path: 'salas', component: SalaComponent},
    {path: 'reservas', component: ReservasComponent}] 
@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {} 
