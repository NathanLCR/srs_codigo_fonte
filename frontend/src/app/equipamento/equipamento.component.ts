import { Component, OnInit } from '@angular/core';
import Equipamento from '../models/Equipamento';
import {EquipamentoService} from './equipamento.service';

@Component({
  selector: 'app-equipamento',
  templateUrl: './equipamento.component.html',
  styleUrls: ['./equipamento.component.css']
})
export class EquipamentoComponent implements OnInit {

  equipamentos: Equipamento[];

  displayForm = false;

  constructor(private equipamentoService: EquipamentoService) { }

  ngOnInit(): void {
    this.equipamentoService.getEquipamentos().subscribe(resultado => this.equipamentos = resultado);
  }

  deletar(equipamento) {
    this.equipamentoService.deleteEquipamento(equipamento.id).subscribe();
    this.equipamentos = this.equipamentos.filter(val => val.id !== equipamento.id);
  }

  showForm() {
    this.displayForm = true;
  }

}
