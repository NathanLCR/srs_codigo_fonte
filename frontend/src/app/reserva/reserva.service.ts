import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import Reserva from '../models/Reserva';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  private readonly PATH = environment.apiUrl + "/reservas/";

  constructor(private http: HttpClient) { }

  getReservas(){
    return this.http.get<Reserva[]>(this.PATH);
  }

  deleteReserva(id){
    return this.http.delete(this.PATH + id);
  }
}
