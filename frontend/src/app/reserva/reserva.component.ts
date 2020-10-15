import { Component, OnInit } from '@angular/core';
import Reserva from '../models/Reserva';
import {ReservaService} from './reserva.service';

@Component({
  selector: 'app-equipamento',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})
export class ReservaComponent implements OnInit {

  reservas: Reserva[];

  displayForm = false;

  constructor(private reservaService: ReservaService) { }

  ngOnInit(): void {
    this.reservaService.getReservas().subscribe(resultado => this.reservas = resultado);
  }

  deletar(reserva) {
    this.reservaService.deleteReserva(reserva.id).subscribe();
    this.reservas = this.reservas.filter(val => val.id !== reserva.id);
  }

  showForm() {
    this.displayForm = true;
  }

}
