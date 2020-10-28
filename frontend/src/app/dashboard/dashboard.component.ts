import { Component, OnInit } from '@angular/core';
import Cliente from "../models/Cliente";
import { ClienteService } from "../cliente/cliente.service";
import { BarGrafico } from './BarGrafico';
import Equipamento from '../models/Equipamento';
import Reserva from '../models/Reserva';
import Sala from '../models/Sala';
import { EquipamentoService } from '../equipamento/equipamento.service';
import { SalaService } from '../sala/sala.service';
import { ReservaService } from '../reserva/reserva.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  
  clientes: Cliente[];
  equipamentos: Equipamento[];
  salas: Sala[];
  reservas: Reserva[];
  
  data: BarGrafico;
  
  constructor(
       private clienteService: ClienteService,
       private equipamentoService: EquipamentoService,
       private salaService: SalaService,
       private reservaService: ReservaService
       
  ) { 
    
    this.data = new BarGrafico().data
  }


  ngOnInit(): void {

      this.clienteService.getClientes().subscribe((resultado) => {
           this.clientes = resultado;
       });

       this.equipamentoService.getEquipamentos().subscribe((resultado) => {
        this.equipamentos = resultado;
      });

      this.salaService.getSalas().subscribe((resultado) => {
        this.salas = resultado;
      });

      this.reservaService.getReservas().subscribe((resultado) => {
        this.reservas = resultado;
      });
       
      }
     
  }
  
