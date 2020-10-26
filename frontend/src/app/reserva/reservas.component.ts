import { EquipamentoService } from './../equipamento/equipamento.service';
import { ClienteService } from './../cliente/cliente.service';
import { Component, OnInit } from "@angular/core";
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from "@angular/forms";
import { MessageService } from "primeng/api";
import { ReservaService } from "./reserva.service";
import { SalaService } from '../sala/sala.service';
import Reserva from '../models/Reserva';
import Sala from '../models/Sala';
import Equipamento from '../models/Equipamento';
import ReservaEquipamento from '../models/ReservaEquipamento';
import Cliente from '../models/Cliente';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { CPFPipe } from '../pipes/cpf.pipe';

@Component({
    selector: "app-listar-reservas",
    templateUrl: "./reservas.component.html",
    styleUrls: ["./reservas.component.css"],
    animations: [
        trigger('rowExpansionTrigger', [
            state('void', style({
                transform: 'translateX(-10%)',
                opacity: 0
            })),
            state('active', style({
                transform: 'translateX(0)',
                opacity: 1
            })),
            transition('* <=> *', animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'))
        ])
    ]
})
export class ReservasComponent implements OnInit {

    reservas: Reserva[];

    reservaForm: FormGroup;

    clientes: { label: string, value: Cliente }[];

    salas: { label: string, value: Sala }[];

    equipamentos: { label: string, value: Equipamento }[];

    reservaEquipamentoForm: FormGroup;

    displayForm = false;
    displayEquipamentoForm = false;

    constructor(
        private reservaService: ReservaService,
        private formBuilder: FormBuilder,
        private messageService: MessageService,
        private clienteService: ClienteService,
        private salaService: SalaService,
        private equipamentoService: EquipamentoService,
    ) {
        this.reservaForm = new FormGroup({
            id: new FormControl(null),
            idCliente: new FormControl(null),
            cliente: new FormControl(null, [Validators.required]),
            idSala: new FormControl(null),
            sala: new FormControl(null, [Validators.required]),
            dataRange: new FormControl(null),
            dataInicio: new FormControl(null),
            dataFim: new FormControl(null),
            equipamentos: new FormArray([]),
        });

        this.reservaEquipamentoForm = this.formBuilder.group({
            idReserva: null,
            idEquipamento: null,
            quantidade: null,
            equipamento: null
        });
    }

    cpfPipe = new CPFPipe();

    ngOnInit(): void {
        this.getAllReservas();

        this.clienteService.getClientes().subscribe((resulta) => {
            this.clientes = resulta.map((e) => {
                return {

                    label: e.nome + " | " + this.cpfPipe.transform(e.cpf), value: e
                };
            });
        });


        this.salaService.getSalas().subscribe((response) => {
            this.salas = response.map((e) => {
                return { label: e.descricao, value: e };
            });
        });

        this.equipamentoService.getEquipamentos().subscribe((response) => {
            this.equipamentos = response.map((e: Equipamento) => {
                return { label: e.nome, value: e };
            });
        });
    }

    get reservaFormControl() {
        return this.reservaForm.controls;
    }

    get equipamentoForm() {
        return this.reservaForm.get('equipamentos') as FormArray;
    }

    addEquipamento(value) {
        this.reservaEquipamentoForm.reset();
        this.displayEquipamentoForm = false;
        value.idEquipamento = value.equipamento.id;
        this.equipamentoForm.value.push(value);
    }

    addEquipamentos(equipArray: ReservaEquipamento[]) {
        if (equipArray) {
            equipArray.forEach(e => this.addEquipamento(e));
        }
    }

    editEquipamento(reservaEquipamento) {
        this.deleteEquipamento(reservaEquipamento);
        this.reservaEquipamentoForm.setValue({
            idEquipamento: reservaEquipamento.idEquipamento,
            idReserva: reservaEquipamento.idReserva,
            equipamento: reservaEquipamento.equipamento,
            quantidade: reservaEquipamento.quantidade
        });
        this.showEquipamentoForm();
    }

    deleteEquipamento(reservaEquipamento) {
        const equipamentos = this.equipamentoForm.value.filter(r => r !== reservaEquipamento);
        this.equipamentoForm.reset();
        this.addEquipamentos(equipamentos);

    }

    addSucess() {
        this.messageService.add({
            severity: "success",
            summary: "Sucesso!",
            detail: "Reserva Cadastrada",
        });
    }
    addDelete() {
        this.messageService.add({
            severity: "success",
            summary: "Sucesso!",
            detail: "Reserva Cancelada",
        });
    }
    addError() {
        this.messageService.add({
            severity: "warn",
            summary: "Atenção!",
            detail: "Erro ao Chamar Serviço",
        });
    }
    addAtt() {
        this.messageService.add({
            severity: "info",
            summary: "Sucesso!",
            detail: "Reserva Atualizada",
        });
    }
    getAllReservas() {
        this.reservaService.getReservas().subscribe((reservas) => {
            this.reservas = reservas;
            reservas.forEach((r: Reserva) => {
                return this.getClienteESalaEEquipamentos(r);
            });

        });
    }

    showForm() {
        this.displayForm = true;
        this.reservaForm.reset();
    }

    showEquipamentoForm() {
        this.displayEquipamentoForm = true;
    }

    handleDelete(value) {
        this.reservaService.deleteReserva(value.id).subscribe(
            () => {
                this.reservaForm.reset();
                this.addDelete();
            },
            () => {
                this.addError();
            }
        );
        this.reservas = this.reservas.filter(
            (val) => val.id !== value.id
        );
    }

    handleEdit(reserva) {
        const data = [new Date(reserva.dataInicio), new Date(reserva.dataFim)];
        this.reservaForm.patchValue({
            id: reserva.id,
            idCliente: reserva.idCliente,
            cliente: reserva.cliente,
            idSala: reserva.idSala,
            sala: reserva.sala,
            dataInicio: reserva.dataInicio,
            dataFim: reserva.dataFim,
            dataRange: data
        });
        this.equipamentoForm.reset();
        reserva.equipamentos.forEach(e => {
            this.addEquipamento(e);
        });
        this.displayForm = true;
    }

    handleSubmit(formValue) {
        formValue.idSala = formValue.sala.id;
        formValue.idCliente = formValue.cliente.id;
        formValue.dataInicio = formValue.dataRange[0];
        formValue.dataFim = formValue.dataRange[1];
        if (formValue.dataRange[1] == null) {
            formValue.dataFim = formValue.dataRange[0];
        }
        console.log(formValue)
        if (this.isDataBooked(formValue)) {
            this.messageService.add({
                severity: "warn",
                summary: "Erro!",
                detail: "Data reservada",
            });
            return;
        }
        this.displayForm = false;
        this.reservaForm.reset();
        if (!formValue.id) {
            this.addReserva(formValue);
        } else {
            this.editReserva(formValue);
        }
    }

    addReserva(reserva: Reserva) {
        this.reservaService.postReserva(reserva).subscribe((response: Reserva) => {
            reserva = this.getClienteESalaEEquipamentos(response);
            this.reservas.push(reserva);
            this.addSucess();
        }, () => this.addError());
    }

    editReserva(reserva: Reserva) {
        console.log(reserva);
        this.reservaService.putReserva(reserva).subscribe((response) => {
            const index = this.reservas.findIndex(r => r.id === reserva.id);
            this.reservas[index] = this.getClienteESalaEEquipamentos(response);
            this.addSucess();
        }, () => this.addError());
    }

    getClienteESalaEEquipamentos(reserva: Reserva): Reserva {
        this.clienteService.getClienteById(reserva.idCliente).subscribe((r) => {
            reserva.cliente = r;
        });
        this.salaService.getSalaById(reserva.idSala).subscribe(s => {
            reserva.sala = s;
        });

        reserva.equipamentos.forEach(e => {
            this.equipamentoService.getEquipamentoById(e.idEquipamento).subscribe(response => {
                e.equipamento = response;
            });
            return e;
        });
        return reserva;
    }

    isDataBooked(reservaForm) {
        const reservasSala = this.reservas.filter(r => r.idSala === reservaForm.idSala && r.id !== reservaForm.id);



        const dataInicio = new Date(reservaForm.dataInicio);
        const dataFim = new Date(reservaForm.dataFim);

        let error = false;

        reservasSala.forEach(r => {
            const di = new Date(r.dataInicio);
            const df = new Date(r.dataFim);
            if (dataInicio.getTime() < df.getTime() || dataFim.getTime() > di.getTime()) {
                error = true;
            }
            if (dataInicio.getTime() < df.getTime() || dataFim.getTime() < di.getTime()) {
                error = false;
            }
            // if (dataInicio.getTime() > df.getTime() || dataFim.getTime() < di.getTime()) {
            //     error = false;
            // }
        });
        return error;
    }

}