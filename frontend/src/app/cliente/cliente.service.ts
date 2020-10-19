import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {Cliente} from '../models/Cliente';

@Injectable({
    providedIn: 'root'
  })
export class ClienteService {
    
    private readonly PATH = environment.apiUrl + "/clientes/"; 

    constructor(private http: HttpClient) { }

    getClientes() {
        return this.http.get<Cliente[]>(this.PATH);
    }

    getClienteById(id){
        return this.http.get<Cliente>(this.PATH + '/{id}')
    }
    postCliente(cliente: Cliente){
        return this.http.post(this.PATH,cliente);
    }

    putCliente(cliente: Cliente){
        return this.http.put(this.PATH,cliente);
    }
    deleteCliente(id){
        return this.http.delete(this.PATH + id);
    }

}