import { Component, OnInit } from '@angular/core';
import Cliente from "../models/Cliente";
import { ClienteService } from "../cliente/cliente.service";
import { ClienteComponent } from '../cliente/cliente.component';
import { BarGrafico } from './BarGrafico';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  
  clientes: Cliente[];
  data: BarGrafico;
  numClientes: any = 1;
  
  constructor(
       private clienteService: ClienteService,
       
  ) { }


  ngOnInit(): void {

      this.clienteService.getClientes().subscribe((resultado) => {
           this.clientes = resultado;
       });
       
      }
     
  }
  