import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EquipamentoService {

  private readonly PATH = environment.apiUrl + "/equipamentos";

  constructor(private http: HttpClient) { }

  getEquipamentos(){
    const equipamentos = [];
    
    this.http.get(this.PATH).subscribe(resultado => equipamentos.push(resultado));

    return equipamentos;
  }
}
