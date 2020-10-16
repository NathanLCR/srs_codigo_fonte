import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListarReservaModel } from '../../models/listar-reserva.model';
import { ReservaService } from '../../services/reserva.service';

@Component({
  selector: 'app-listar-reservas',
  templateUrl: './listar-reservas.component.html',
  styleUrls: ['./listar-reservas.component.css']
})
export class ListarReservasComponent implements OnInit {

  listaReservas: ListarReservaModel[];

  constructor(
    private reservaService: ReservaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.listarReservas();
  }

  listarReservas() {
    this.reservaService
    .listarReservas()
    .subscribe(listaReservas =>{
      this.listaReservas = listaReservas;
  });
}

  direcionarCadastrar() {
    this.router.navigate(['../cadastrar-reservas'])
  }

  direcionarEditarReserva(id: number) {
    this.router.navigate([`../editar-reservas/${id}`])
  }
  
}
