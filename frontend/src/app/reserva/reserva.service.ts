import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, of } from "rxjs";
import { CadastrarReservaModel } from "src/app/models/cadastrar-reserva.model";
import { EditarReservaModel } from "src/app/models/editar-reserva.model";
import { environment } from "src/environments/environment";
import Reserva from "../models/Reserva";

@Injectable({
    providedIn: "root",
})
export class ReservaService {
    constructor(private http: HttpClient) {}

    getReservas() {
        return this.http.get(`${environment.apiUrl}/reservas`);
    }

    postReserva(cadastroReserva: CadastrarReservaModel) {
        return this.http.post(
            `${environment.apiUrl}/reservas`,
            cadastroReserva
        );
    }

    getReserva(id: number) {
        return this.http.get(`${environment.apiUrl}/reservas/${id}`);
    }

    putReserva(reserva: Reserva) {
        return this.http.put(`${environment.apiUrl}/reservas`, reserva);
    }

    deleteReserva(id: number) {
        return this.http.delete(`${environment.apiUrl}/reservas/${id}`);
    }
}
