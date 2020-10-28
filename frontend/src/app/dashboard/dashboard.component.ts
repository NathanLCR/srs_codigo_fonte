import { Component, OnInit } from '@angular/core';
import Cliente from "../models/Cliente";
import { ClienteService } from "../cliente/cliente.service";
import { ClienteComponent } from '../cliente/cliente.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  
  clientes: Cliente[];
  
  constructor(
       private clienteService: ClienteService,
      // private clienteComponent: ClienteComponent
    
  ) { }


  ngOnInit(): void {

      this.clienteService.getClientes().subscribe((resultado) => {
           this.clientes = resultado;
       });

  }

  qtdClientes(){
    
    return this.clientes.length;
  }

}
