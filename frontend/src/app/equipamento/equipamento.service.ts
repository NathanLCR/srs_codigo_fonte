import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";
import Equipamento from "../models/Equipamento";

@Injectable({
    providedIn: "root",
})
export class EquipamentoService {
    private readonly PATH = environment.apiUrl + "/equipamentos/";

    constructor(private http: HttpClient) {}

    getEquipamentos() {
        return this.http.get<Equipamento[]>(this.PATH);
    }

    getEquipamento(id: number) {
        return this.http.get<Equipamento>(this.PATH + id);
    }

    deleteEquipamento(id: number) {
        return this.http.delete(this.PATH + id);
    }

    postEquipamento(equipamento: Equipamento) {
        return this.http.post(this.PATH, equipamento);
    }

    putEquipamento(equipamento: Equipamento) {
        return this.http.post(this.PATH, equipamento);
    }
}
