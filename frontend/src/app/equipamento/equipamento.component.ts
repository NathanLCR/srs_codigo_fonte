import { Component, OnInit } from '@angular/core';
import Equipamento from '../models/Equipamento';
import {EquipamentoService} from './equipamento.service';
import { FormBuilder } from '@angular/forms';
import {ConfirmationService} from 'primeng/api';

@Component({
  selector: 'app-equipamento',
  templateUrl: './equipamento.component.html',
  styleUrls: ['./equipamento.component.css'],
  providers: [ConfirmationService],
})
export class EquipamentoComponent implements OnInit {


  equipamentos: Equipamento[];

  displayForm = false;

  equipamentoForm;

  constructor(private equipamentoService: EquipamentoService, 
    private formBuilder: FormBuilder,
    private confirmationService: ConfirmationService
    ) {
    this.equipamentoForm = this.formBuilder.group({
      nome: "",
      idTipoEquipamento: "",
      precoDiaria: "",
      obrigatorio: "",
    })
  }

  ngOnInit(): void {
    this.equipamentoService.getEquipamentos().subscribe(resultado => {
      this.equipamentos = resultado;
      this.getTipoEquipamentos();
    });
  }

  ngOnChanges(): void {
    this.getTipoEquipamentos();
  }

  getTipoEquipamentos(){
    this.equipamentos.forEach(e =>{
      switch (e.idTipoEquipamento){
        case 1:
          e.tipoEquipamento = "Móvel";
          break;
        case 2:
          e.tipoEquipamento = "Eletrodomestico"
          break
        case 3:
          e.tipoEquipamento = "Informática"
          break
      }
      return e;
    })
  }

  handleDelete(equipamento) {
    this.confirmationService.confirm({
      message: 'Tem certeza que desejar excluir o equipamento ' + equipamento.nome,
      header: 'Confirmar exclusão',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.equipamentoService.deleteEquipamento(equipamento.id).subscribe();
        this.equipamentos = this.equipamentos.filter(val => val.id !== equipamento.id);
      }
  });
  }


  showForm() {
    this.displayForm = true;
  }

  onSubmit(value){
    value.obrigatorio = value.obrigatorio ? 1 : 0;
    console.log(value);
    this.equipamentoService.postEquipamento(value);
    this.equipamentos.push(value);
  }

}
