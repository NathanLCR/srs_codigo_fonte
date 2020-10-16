import { Component, OnInit} from '@angular/core';
import { ClienteService } from './cliente.service';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import {Cliente} from '../models/Cliente';
import { FormBuilder, Validators } from "@angular/forms";


@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css'],
  providers: [ConfirmationService],
})
export class ClienteComponent implements OnInit {


  clientes: Cliente[];

  cliente: Cliente;

  displayForm = false;

  constructor(private clienteService: ClienteService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
      this.clienteService.getClientes().subscribe(resultado => this.clientes = resultado);
  }


  


 

  showForm() {
    this.displayForm = true;
  }

  
}



  
