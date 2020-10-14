import { Component, OnInit} from '@angular/core';

import { Cliente } from './cliente';
import { ClienteService } from './clienteservice';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-cliente',
  templateUrl: './crud-cliente.component.html',
  styles: [`
  :host ::ng-deep .p-dialog .product-image {
      width: 150px;
      margin: 0 auto 2rem auto;
      display: block;
  }
`],
styleUrls: ['./crud-cliente.component.scss']
})
export class ClienteComponent implements OnInit {


  clienteDialog: boolean;

  clientes: Cliente[];

  cliente: Cliente;

  selectedClientes: Cliente[];

  submitted: boolean;

  constructor(private clienteService: ClienteService, private messageService: MessageService, private confirmationService: ConfirmationService) { }

  ngOnInit() {
      this.clienteService.getClientes().subscribe(resultado => this.clientes = resultado);
  }

  openNew() {
      this.cliente = {};
      this.submitted = false;
      this.clienteDialog = true;
  }

  deleteSelectedClientes() {
      this.confirmationService.confirm({
          message: 'Are you sure you want to delete the selected products?',
          header: 'Confirm',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {
              this.clientes = this.clientes.filter(val => !this.selectedClientes.includes(val));
              this.selectedClientes = null;
              this.messageService.add({severity:'success', summary: 'Successful', detail: 'Clientes Deleted', life: 3000});
          }
      });
  }

  editCliente(cliente: Cliente) {
      this.cliente = {...cliente};
      this.clienteDialog = true;
  }

  deleteCliente(cliente: Cliente) {
      this.confirmationService.confirm({
          message: 'Are you sure you want to delete ' + cliente.nome + '?',
          header: 'Confirm',
          icon: 'pi pi-exclamation-triangle',
          accept: () => {
              this.clientes = this.clientes.filter(val => val.id !== cliente.id);
              this.cliente = {};
              this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
          }
      });
  }

  hideDialog() {
      this.clienteDialog = false;
      this.submitted = false;
  }
  
  saveCliente() {
      this.submitted = true;

      if (this.cliente.nome.trim()) {
          if (this.cliente.id) {
              this.clientes[this.findIndexById(this.cliente.id)] = this.cliente;                
              this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Updated', life: 3000});
          }
          else {
              this.cliente.id = this.createId();
              //this.cliente.image = 'product-placeholder.svg';
              this.clientes.push(this.cliente);
              this.messageService.add({severity:'success', summary: 'Successful', detail: 'Product Created', life: 3000});
          }

          this.clientes = [...this.clientes];
          this.clienteDialog = false;
          this.cliente = {};
      }
  }

  findIndexById(id: string): number {
      let index = -1;
      for (let i = 0; i < this.clientes.length; i++) {
          if (this.clientes[i].id === id) {
              index = i;
              break;
          }
      }

      return index;
  }

  createId(): string {
      let id = '';
      var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
      for ( var i = 0; i < 5; i++ ) {
          id += chars.charAt(Math.floor(Math.random() * chars.length));
      }
      return id;
  }
}



  
