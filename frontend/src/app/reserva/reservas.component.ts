import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { ConfirmationService, MessageService } from "primeng/api";
import Reserva from "../models/Reserva";
import { ReservaService } from "./reserva.service";
import { EquipamentoService } from "../equipamento/equipamento.service";
import { ClienteService } from "../cliente/cliente.service";

@Component({
    selector: "app-listar-reservas",
    templateUrl: "./reservas.component.html",
    styleUrls: ["./reservas.component.css"],
    providers: [ConfirmationService]
})
export class ReservasComponent implements OnInit {
    displayForm = false;
    formReserva: FormGroup;
    reservaForm;

    reservas: Reserva[];

    constructor(
        private equipamentoService: EquipamentoService,
        private clienteService: ClienteService,
        private reservaService: ReservaService,
        private formBuilder: FormBuilder,
        private confirmationService: ConfirmationService,
        private messageService: MessageService
    ) {
        this.reservaForm = this.formBuilder.group({
            id: null,
            idCliente: null,
            idSala: null,
            dataInicio: "",
            dataFim: "",
        });
    }

    ngOnInit(): void {
        this.reservaService
            .getReservas()
            .subscribe((response: Reserva[]) => (this.reservas = response));
    }

    getClientes() {
        const clientes = this.clienteService
            .getClientes()
            .subscribe((response) => {
                console.log(response);
            });

        console.log(clientes);
        return clientes;
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

    showForm() {
        this.displayForm = true;
    }

    handleDelete(reserva: Reserva) {
        this.reservaService.deleteReserva(reserva.id).subscribe(() => {
            this.reservas = this.reservas.filter(
                (val) => val.id !== reserva.id
            );

        });
        this.confirmationService.confirm({
            message: "Tem certeza que deseja excluir essa reserva",
            header: "Confirmar exclusão",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                this.reservaService.deleteReserva(reserva.id).subscribe(() => {
                    this.reservas = this.reservas.filter(
                        (val) => val.id !== reserva.id
                    );

                });
            },
        });
    }

    handleEdit(reserva) {
        this.displayForm = true;
        this.reservaForm.setValue({
            id: reserva.id,
            idCliente: reserva.idCliente,
            idSala: reserva.idSala,
            dataInicio: reserva.dataInicio,
            dataFim: reserva.dataFim,
        });
    }

    handleSubmit(value) {
        this.displayForm = false;
        this.reservaForm.reset();
        this.reservaService.postReserva(value).subscribe(
            (response: Reserva) => {
                this.reservas.push(response);
                this.addSucess();
            },
            () => {
                this.addError();
            }
        );
    }
}
