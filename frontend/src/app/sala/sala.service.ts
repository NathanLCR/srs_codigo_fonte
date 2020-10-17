import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import Sala from '../models/Sala';

@Injectable({
  providedIn: 'root'
})
export class SalaService {

  private readonly PATH = environment.apiUrl + "/salas/";

  constructor(private http: HttpClient) { }

  getSalas(){
    return this.http.get<Sala[]>(this.PATH);
  }
  
  deleteSala(id){
    return this.http.delete(this.PATH + id);
  }

  postSala(sala: Sala) {
    return this.http.post(this.PATH, sala);
  }

  putSala(sala: Sala) {
    return this.http.put(this.PATH, sala);
  }
}
