import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { EquipamentoService } from './../equipamento/equipamento.service';
import { SalaService } from './sala.service';
import { ConfirmationService } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import Sala from '../models/Sala';
import { FormBuilder } from '@angular/forms';
import Equipamento from '../models/Equipamento';

interface TipoSala{
  label: string,
  value: number
}


@Component({
  selector: 'app-sala',
  templateUrl: './sala.component.html',
  styleUrls: ['./sala.component.css'],
  providers: [ConfirmationService]
})
export class SalaComponent implements OnInit {

  salas: Sala[];

  displayForm = false;

  displayEquipamentoForm = false;

  salaForm;

  salaEquipamentoForm;

  equipamentos;

  salaEquipamentos;

  tiposDeSala: TipoSala[] = [
    {
        label: "Sala de Reunião",
        value: 1,
    },
    {
        label: "Sala de Trabalho",
        value: 2,
    },
    {
        label: "Sala de Vídeo",
        value: 3,
    },
    {
      label: "Sala de Palestras",
      value: 4,
    },
    {
      label: "Auditório",
      value: 5,
    },
];

  constructor(private salaService: SalaService, 
    private confirmationService: ConfirmationService,
    private equipamentoService: EquipamentoService,
    ) { 
      this.salaForm = new FormGroup({
        id: new FormControl(null),
        precoDiaria: new FormControl(),
        descricao: new FormControl(),
        capacidade: new FormControl(),
        disponivel: new FormControl(),
        idTipoSala: new FormControl(),
        tipoSala: new FormControl(),
        // equipamentos: new FormArray(new FormGroup(idEquipamento: new FormControl(),)),
    })

      this.salaEquipamentoForm =  new FormGroup({
        idSala: new FormControl(),
        idEquipamento: new FormControl(),
        quantidade: new FormControl(),
      })


  }

  

  ngOnInit(): void {
    this.salaService.getSalas().subscribe(resultado => {
      this.salas = resultado;

      this.salas.forEach(s => {
        return this.getTipoSala(s)
      })
    });

    this.equipamentoService.getEquipamentos().subscribe(resulta => {this.equipamentos = resulta.map(e=>{return {label: e.nome, value:e}})}
    );

    this.equipamentoService.getEquipamentos().subscribe(resulta => {this.salaEquipamentos = resulta.map(e=>{return {label: e.nome, value:e.id}})}
    );
  }

  deletar(sala) {                                                                         
    this.salaService.deleteSala(sala.id).subscribe();
    this.salas = this.salas.filter(val => val.id !== sala.id);
  }

  atualizar(sala) {
    this.salaService.putSala(sala).subscribe();
  }

  showForm() {

    this.displayForm = true;
  }

  showFormEquipamentos(){
    this.displayEquipamentoForm = true;
  }

  getTipoSala(sala) {
    const { label } = this.tiposDeSala.find(
        (t) => t.value === sala.idTipoSala
    );

    sala.tipoSala = label;
    return sala;
}

  handleSubmit(value) {
    console.log(value);
    this.salaService.postSala(value).subscribe();
    value = this.getTipoSala(value);
    if (!value.id) {
        this.salas.push(value);
        console.log('ok');
    } else {
        const index = this.salas.findIndex((e) => e.id === value.id);
        this.salas[index] = value;
    }
    this.displayForm = false;

}

handleEquipamentoSubmit(value) {
  this.salaEquipamentoForm.idSala = this.salaForm.id;
  this.salaForm.equipamentos.push(this.salaEquipamentoForm);
  this.displayEquipamentoForm = false;

}

handleEdit(sala) {
  this.salaForm.setValue({
      id: sala.id,
      precoDiaria: sala.precoDiaria,
      descricao: sala.descricao,
      capacidade: sala.capacidade,
      disponivel: sala.disponivel,
      idTipoSala: sala.idTipoSala,
      equipamentos: sala.equipamentos,
  });
}


handleDelete(sala) {
  this.confirmationService.confirm({
      message:
          "Tem certeza que desejar excluir a sala " +
          sala.descricao,
      header: "Confirmar exclusão",
      icon: "pi pi-exclamation-triangle",
      accept: () => {
          this.salaService
              .deleteSala(sala.id)
              .subscribe();
          this.salas = this.salas.filter(
              (val) => val.id !== sala.id
          );
      },
  });
}


onSubmit(){}
}
