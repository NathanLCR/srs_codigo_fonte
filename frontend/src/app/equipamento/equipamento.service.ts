import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import Equipamento from '../models/Equipamento';

@Injectable({
  providedIn: 'root'
})
export class EquipamentoService {

  private readonly PATH = environment.apiUrl + "/equipamentos/";

  constructor(private http: HttpClient) { }

  getEquipamentos(){
    return this.http.get<Equipamento[]>(this.PATH);
  }

  deleteEquipamento(id){
    return this.http.delete(this.PATH + id);
  }
}
