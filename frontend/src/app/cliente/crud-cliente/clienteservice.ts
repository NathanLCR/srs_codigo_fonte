import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Cliente } from './cliente';
import { environment } from 'src/environments/environment.prod';
import { httpInterceptorProviders } from '@nuvem/primeng-components';
import { ClienteComponent } from './crud-cliente.component';

@Injectable()
export class ClienteService {
    
    private readonly PATH = environment.apiUrl + '/clientes'; 

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