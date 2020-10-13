import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-crud-equipamento',
  templateUrl: './crud-equipamento.component.html',
  styleUrls: ['./crud-equipamento.component.css'],
  providers: [MessageService,ConfirmationService]
})
export class CrudEquipamentoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
