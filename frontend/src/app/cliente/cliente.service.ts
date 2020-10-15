import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import Cliente  from '../models/Cliente';

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
    
    createCliente(cliente){
        return this.http.post<Cliente>(this.PATH,cliente);
    }
    
}