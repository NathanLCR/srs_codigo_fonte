import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { CadastrarReservaModel } from 'src/app/models/cadastrar-reserva.model';
import { EditarReservaModel } from 'src/app/models/editar-reserva.model';
import { InfoReservaModel } from 'src/app/models/info-reserva.model';
import { ListarReservaModel } from 'src/app/models/listar-reserva.model';
import {environment} from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  constructor(
    private http: HttpClient
  ) { }

  listarReservas():Observable<ListarReservaModel[]>{
    return this.http.get<ListarReservaModel[]>(`${environment.apiUrl}/reservas`);
  }


  cadastrarReserva(cadastroReserva: CadastrarReservaModel):Observable<InfoReservaModel>{ 
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
    return this.http.put<InfoReservaModel>(`${environment.apiUrl}/reservas`, editarReservaModel);
  }

  deletarReserva(id:number){
    return this.http.delete(`${environment.apiUrl}/reservas` + id);
  }

}
