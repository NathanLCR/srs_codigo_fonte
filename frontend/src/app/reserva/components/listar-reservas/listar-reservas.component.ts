import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ListarReservaModel } from 'src/app/models/listar-reserva.model';
import { ReservaService } from '../../services/reserva.service';

@Component({
  selector: 'app-listar-reservas',
  templateUrl: './listar-reservas.component.html',
  styleUrls: ['./listar-reservas.component.css']
})
export class ListarReservasComponent implements OnInit {

  listaReservas: ListarReservaModel[];
  displayForm = false;

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

  direcionarDeletarReserva(id: number) {
    this.router.navigate([`../deletar-reservas/${id}`])
  }

  showForm() {
    this.displayForm = true;
  }

  
}
