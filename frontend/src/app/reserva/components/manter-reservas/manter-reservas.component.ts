import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InfoReservaModel } from 'src/app/models/info-reserva.model';

import { ReservaService } from '../../services/reserva.service';

@Component({
  selector: 'app-manter-reservas',
  templateUrl: './manter-reservas.component.html',
  styleUrls: ['./manter-reservas.component.css']
})
export class ManterReservasComponent implements OnInit {

  formReserva: FormGroup; 
  reserva:InfoReservaModel;

  constructor(
    private formBuilder: FormBuilder,
    private reservaService: ReservaService,
    private router: Router,
    private actRouter: ActivatedRoute,

  ) { }

  ngOnInit(): void {
    this.criarFormulario();
    this.recuperarIdRota();
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
  criarFormulario(){
    this.formReserva = this.formBuilder.group(
      {
        dataInicio: [null,[Validators.required]],
        dataFim:[null,[Validators.required]],
        total: [null,[Validators.required]]
      }
  );
  }
  salvar(){

    if(this.formReserva.valid){
      if(this.isEditar()){
        this.editarReserva();
      } else{
        this.cadastrarReserva();
      }
    } else{
      console.log('Formulário Inválido');
    }
  }

  private editarReserva() {
    this.reservaService.editarReserva(
    {
      id: this.reserva.id,
      idCliente:this.reserva.idCliente,
      idSala:this.reserva.idSala,
      dataInicio: this.formReserva.get('dataInicio').value,
      dataFim: this.formReserva.get('dataFim').value,
      total: this.reserva.total
    }
  ).subscribe(
    () => {
    console.log('Reserva Atualizada');
    this.router.navigate(['../reservas']);
   },
      () => {
        console.log('Erro ao chamar serviço');
      });
  }

  private cadastrarReserva() {
    this.reservaService.cadastrarReserva(
      {
        idCliente:22,
        idSala:33,
        dataInicio: this.formReserva.get('dataInicio').value,
        dataFim: this.formReserva.get('dataFim').value,
        total: this.formReserva.get('total').value
      }
    ).subscribe(
      () => {
        console.log('Reserva Cadastrada');
        this.router.navigate(['../reservas']);
      },
      () => {
        console.log('Erro ao chamar serviço');
      });
  }

  isEditar(): boolean {
    return this.reserva != null;
  }

}


