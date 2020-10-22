import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {environment} from 'src/environments/environment';
import Reserva from '../models/Reserva';


@Injectable({
  providedIn: "root"
  
})
export class ReservaService {

  constructor(
    private http: HttpClient
  ) { }

  listarReservas():Observable<Reserva[]>{
    return this.http.get<Reserva[]>(`${environment.apiUrl}/reservas`);
  }


  cadastrarReserva(cadastroReserva: Reserva):Observable<Reserva>{ 
    return this.http.post<Reserva>(`${environment.apiUrl}/reservas`,cadastroReserva);

  }

  recuperarReserva(id: number): Observable<Reserva> {
    return this.http.get<Reserva>(`${environment.apiUrl}/reservas/${id}`);
  }

  editarReserva(reserva: Reserva): Observable<Reserva> {
    return this.http.put<Reserva>(`${environment.apiUrl}/reservas`, reserva);
  }

  deletarReserva(id:number){
    return this.http.delete(`${environment.apiUrl}/reservas/${id}`);
  }

}
