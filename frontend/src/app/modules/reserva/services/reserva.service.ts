import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ListarReservaModel } from '../models/listar-reserva.model';
import {environment} from 'src/environments/environment';
import { CadastrarReservaModel } from '../models/cadastrar-reserva.model';
import { InfoReservaModel } from '../models/info-reserva.model';
import { EditarReservaModel } from '../models/editar-reserva.model';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  constructor(
    private http: HttpClient
  ) { }

  listarReservas():Observable<ListarReservaModel[]>{
    return of<ListarReservaModel[]>([
      {
        id: 1,
        idSala: 2,
        idCliente: 2,
        dataInicio: 'Card',
        dataFim: 'Fim',
        total: 2500.0
      }
    ])
    return this.http.get<ListarReservaModel[]>(`${environment.apiUrl}/reservas`);
  }


  cadastrarReserva(cadastroReserva: CadastrarReservaModel):Observable<InfoReservaModel>{
    return of<any>(true);  
    return this.http.post<InfoReservaModel>(`${environment.apiUrl}/reservas`,cadastroReserva);

  }

  recuperarReserva(id: number): Observable<InfoReservaModel> {
    return of<InfoReservaModel>({
      id,
      idCliente: 5,
      idSala: 3,
      dataInicio: 23,
      dataFim: 22,
      total: 90.0
    })
    return this.http.get<InfoReservaModel>(`${environment.apiUrl}/reservas/${id}`);
  }

  editarReserva(editarReservaModel: EditarReservaModel): Observable<InfoReservaModel> {
    return of<any>(true);
    return this.http.put<InfoReservaModel>(`${environment.apiUrl}/reservas`, editarReservaModel);
  }

  deletarReserva(id:number){
    return this.http.delete(`${environment.apiUrl}/reservas` + id);

  }

}
