import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { CadastrarReservaModel } from 'src/app/models/cadastrar-reserva.model';
import { EditarReservaModel } from 'src/app/models/editar-reserva.model';
import {environment} from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
  
})
export class ReservaService {

  constructor(
    private http: HttpClient
  ) { }

  listarReservas():Observable<EditarReservaModel[]>{
    return this.http.get<EditarReservaModel[]>(`${environment.apiUrl}/reservas`);
  }


  cadastrarReserva(cadastroReserva: CadastrarReservaModel):Observable<EditarReservaModel>{ 
    return this.http.post<EditarReservaModel>(`${environment.apiUrl}/reservas`,cadastroReserva);

  }

  recuperarReserva(id: number): Observable<EditarReservaModel> {
    return this.http.get<EditarReservaModel>(`${environment.apiUrl}/reservas/${id}`);
  }

  editarReserva(editarReservaModel: EditarReservaModel): Observable<EditarReservaModel> {
    return this.http.put<EditarReservaModel>(`${environment.apiUrl}/reservas`, editarReservaModel);
  }

  deletarReserva(id:number){
    return this.http.delete(`${environment.apiUrl}/reservas/${id}`);   
  }

}