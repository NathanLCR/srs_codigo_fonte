import { Component, OnInit } from '@angular/core';
import { ListarReservaModel } from 'src/app/models/listar-reserva.model';
import { ActivatedRoute, Router } from '@angular/router';
import { InfoReservaModel } from 'src/app/models/info-reserva.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {MessageService} from 'primeng/api';
import { ReservaService } from './reserva.service';

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
    private actRouter: ActivatedRoute,
    private messageService: MessageService

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

  addSucess() {
    this.messageService.add({severity:'success', summary:'Sucesso!', detail:'Reserva Cadastrada'});
}
  addError() {
  this.messageService.add({severity:'info', summary:'Sucesso!', detail:'Reserva Cancelada'});
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
    this.addError();
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
      id: reserva.id,
      idCliente:reserva.idCliente,
      idSala:reserva.idSala,
      dataInicio: reserva.dataInicio,
      dataFim: reserva.dataFim,
      total: reserva.total
    })
    
  }

  cadastrarReserva(value) {
    this.displayForm = false;
    this.addSucess();
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