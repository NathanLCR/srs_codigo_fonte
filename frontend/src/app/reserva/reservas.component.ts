import { EquipamentoService } from './../equipamento/equipamento.service';
import { ClienteService } from './../cliente/cliente.service';
import { Component, OnInit } from "@angular/core";
import { FormArray, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MessageService } from "primeng/api";
import { ReservaService } from "./reserva.service";
import Cliente from '../models/Cliente';
import { SalaService } from '../sala/sala.service';
import { EditarReservaModel } from '../models/editar-reserva.model';

@Component({
    selector: "app-listar-reservas",
    templateUrl: "./reservas.component.html",
    styleUrls: ["./reservas.component.css"],
})
export class ReservasComponent implements OnInit {

    listaReservas: EditarReservaModel[];
    
    formReserva: FormGroup;
    reserva: EditarReservaModel;
    reservaForm;

    clientes;
    clienteForm;

    salas;
    salaForm;

    equipamentos;
    equipamentoForm;

    reservaEquipamentoForm;

    displayForm = false;
    displayClienteForm = false;
    displaySalaForm = false;
    displayEquipamentoForm = false;

    constructor(
        private reservaService: ReservaService,
        private formBuilder: FormBuilder,
        private messageService: MessageService,
        private clienteService: ClienteService,
        private salaService: SalaService,
        private equipamentoService: EquipamentoService,
    ) {
        this.reservaForm = this.formBuilder.group({
            id: null,
            idCliente: null,
            idSala: null,
            dataInicio: "",
            dataFim: "",
            total: null,
        });

        this.clienteForm = this.formBuilder.group({
            id: null,
            nome: null,
            rg: null,
            cpf: null,
            dataNascimento: null,
            endereco: null,
        });

        this.salaForm = this.formBuilder.group({
            id: null,
            precoDiaria: null,
            descricao: null,
            capacidade: null,
            disponivel: null,
            idTipoSala: null,
            tipoSala: null,
            equipamentos: null
        });

        this.equipamentoForm = this.formBuilder.group({
            id: null,
            nome: null,
            idTipoEquipamento: null,
            precoDiaria: null,
            tipoEquipamento: null,
        })


    }

    get clientesForm() {
        return this.clienteForm.get("clientes") as FormArray;
    }

    get salasForm(){
        return this.salaForm.get("salas") as FormArray;
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
            severity: "info",
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

    ngOnInit(): void {
        this.listarReservas();

        this.clienteService.getClientes().subscribe((resulta) => {
            this.clientes = resulta.map((e) => {
                return { label: e.nome, value: e };
            });
        });

        this.salaService.getSalas().subscribe((resulta) => {
            this.salas= resulta.map((e) => {
                return { label: e.descricao, value: e };
            });
        });

        this.equipamentoService.getEquipamentos().subscribe((resulta) => {
            this.equipamentos= resulta.map((e) => {
                return { label: e.nome, value: e };
            });
        });
    }

    addCliente(value) {
        this.displayClienteForm = false;
        this.reservaForm.idCliente = value.id;
    }

    addSala(value) {
        this.displaySalaForm = false;
        this.reservaForm.idSala = value.id;
    }

    addEquipamento(value) {
        this.displayEquipamentoForm = false;
        value.idReserva = value.reserva.id;
        this.reservaEquipamentoForm.idEquipamento = value.id;
    }

    listarReservas() {
        this.reservaService.listarReservas().subscribe((listaReservas) => {
            this.listaReservas = listaReservas;
        });
    }

    showForm() {
        this.displayForm = true;
    }

    showClienteForm() {
        this.displayClienteForm = true;
    }

    showSalaForm() {
        this.displaySalaForm = true;
    }

    showEquipamentoForm() {
        this.displayEquipamentoForm = true;
    }

    direcionarDeletarReserva(value) {
        this.reservaService.deletarReserva(value.id).subscribe(
            () => {
                this.reservaForm.reset();
                this.addDelete();
            },
            () => {
                this.addError();
            }
        );
        this.listaReservas = this.listaReservas.filter(
            (val) => val.id !== value.id
        );
    }

    recuperarReserva(id: number) {
        this.reservaService.recuperarReserva(id).subscribe((reserva) => {
            this.reserva = reserva;
            this.formReserva.patchValue(reserva);
        });
    }

    editarReserva(reserva) {
        this.displayForm = true;
        this.reservaForm.setValue({
            id: reserva.id,
            idCliente: reserva.idCliente,
            idSala: reserva.idSala,
            dataInicio: reserva.dataInicio,
            dataFim: reserva.dataFim,
            total: reserva.total,
        });
    }

    cadastrarReserva(value) {
        this.displayForm = false;
        this.reservaForm.reset();
        this.reservaService.cadastrarReserva(value).subscribe(
            () => {
                this.listaReservas.push(value);
                this.addSucess();
                this.listarReservas();
            },
            () => {
                this.addError();
            }
        );
    }
}
