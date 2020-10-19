import { Component, OnInit } from '@angular/core';
import { ListarReservaModel } from 'src/app/models/listar-reserva.model';
import { ReservaService } from '../services/reserva.service';
import { ActivatedRoute, Router } from '@angular/router';
import { InfoReservaModel } from 'src/app/models/info-reserva.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-listar-reservas',
  templateUrl: './reservas.component.html',
  styleUrls: ['./reservas.component.css']
})
export class ReservasComponent implements OnInit {

  listaReservas: ListarReservaModel[];
  displayForm = false;
  formReserva: FormGroup; 
  reserva:InfoReservaModel;
  reservaForm;

  constructor(
    private reservaService: ReservaService,
    private router: Router,
    private formBuilder: FormBuilder,
    private actRouter: ActivatedRoute

  ) { 
    this.reservaForm = this.formBuilder.group(
      {
        id: null,
        idCliente: null,
        idSala: null,
        dataInicio: "",
        dataFim: "",
        total: null
      }
  )
  }

  ngOnInit(): void {
    this.listarReservas();
    this.recuperarIdRota();
  }

  listarReservas() {
    this.reservaService
    .listarReservas()
    .subscribe(listaReservas =>{
      this.listaReservas = listaReservas;
  });
}

  direcionarSalvar(reserva) {
    this.displayForm = true;
    
  }


  direcionarDeletarReserva(reserva) {
    this.reservaService.deletarReserva(reserva.id)
    .subscribe();   
    this.listaReservas = this.listaReservas.filter(val => val.id !== reserva.id);
  }

  recuperarIdRota(){
    const id  = this.actRouter.snapshot.params['id']
    if(id){
      this.recuperarReserva(id);
    }
  }


  recuperarReserva(id:number){
    this.reservaService.recuperarReserva(id)
    .subscribe(reserva =>{
    this.reserva = reserva;
    this.formReserva.patchValue(reserva);
    })

  }

  
  editarReserva(reserva) {
    this.displayForm = true;
    this.reservaForm.setValue(
    {
      id: this.reserva.id,
      idCliente:this.reserva.idCliente,
      idSala:this.reserva.idSala,
      dataInicio: this.reserva.dataInicio,
      dataFim: this.reserva.dataFim,
      total: this.reserva.total
    }
    )}

  cadastrarReserva(value) {
    this.reservaService.cadastrarReserva(value).subscribe(
      () => {
        console.log('Reserva Cadastrada');
        this.router.navigate(['../reservas']);
      },
      () => {
        console.log('Erro ao chamar serviço');
      });
      
  
}
  




}