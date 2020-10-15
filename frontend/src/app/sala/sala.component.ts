import { SalaService } from './sala.service';
import { ConfirmationService } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import Sala from '../models/Sala';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-sala',
  templateUrl: './sala.component.html',
  styleUrls: ['./sala.component.css'],
  providers: [ConfirmationService]
})
export class SalaComponent implements OnInit {

  salas: Sala[];

  displayForm = false;

  salaForm;

  constructor(private salaService: SalaService, 
    private formBuilder: FormBuilder,
    private confirmationService: ConfirmationService
    ) { 
      this.salaForm = this.formBuilder.group({
        precoDiaria: "",
        descricao: "",
        capacidade: "",
        disponivel: "",
        idTipoSala: "",
        equipamentos: "",
    })
  }

  ngOnInit(): void {
    this.salaService.getSalas().subscribe(resultado => {
      this.salas = resultado;
    });
  }

  showForm() {
    this.displayForm = true;
  }
}
