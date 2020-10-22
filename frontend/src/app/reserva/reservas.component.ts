import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MessageService } from "primeng/api";
import { EditarReservaModel } from "../models/editar-reserva.model";
import { ReservaService } from "./reserva.service";

@Component({
    selector: "app-listar-reservas",
    templateUrl: "./reservas.component.html",
    styleUrls: ["./reservas.component.css"],
})
export class ReservasComponent implements OnInit {
    listaReservas: EditarReservaModel[];
    displayForm = false;
    formReserva: FormGroup;
    reserva: EditarReservaModel;
    reservaForm;

    constructor(
        private reservaService: ReservaService,
        private formBuilder: FormBuilder,
        private messageService: MessageService
    ) {
        this.reservaForm = this.formBuilder.group({
            id: null,
            idCliente: null,
            idSala: null,
            dataInicio: "",
            dataFim: "",
            total: null,
        });
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
    }

    listarReservas() {
        this.reservaService.listarReservas().subscribe((listaReservas) => {
            this.listaReservas = listaReservas;
        });
    }

    showForm() {
        this.displayForm = true;
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
