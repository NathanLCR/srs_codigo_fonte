import { Component, OnInit } from "@angular/core";
import { ClienteService } from "./cliente.service";
import { ConfirmationService } from "primeng/api";
import { MessageService } from "primeng/api";
import Cliente from "../models/Cliente";
import { FormGroup, Validators, FormControl } from "@angular/forms";

@Component({
    selector: "app-cliente",
    templateUrl: "./cliente.component.html",
    styleUrls: ["./cliente.component.css"],
    providers: [ConfirmationService, MessageService],
})
export class ClienteComponent implements OnInit {
    clienteDialog: boolean;
    clientes: Cliente[];

    cliente: Cliente;

    displayForm = false;

    clienteForm;

    constructor(
        private clienteService: ClienteService,
        private messageService: MessageService,
        private confirmationService: ConfirmationService
    ) {}

    ngOnInit() {
        this.clienteForm = new FormGroup({
            id: new FormControl(""),
            nome: new FormControl("", [Validators.required]),
            cpf: new FormControl("", Validators.required),
            rg: new FormControl("", Validators.required),
            dataNascimento: new FormControl(""),
            endereco: new FormControl(""),
            email: new FormControl(""),
            telefone: new FormControl(""),
        });
        this.clienteService.getClientes().subscribe((resultado) => {
            this.clientes = resultado;
        });
    }

    // addErrorToast(error) {
    //     this.messageService.add({
    //         severity: "error",
    //         summary: "Error no servidor",
    //         detail: "Error no servidor, favor tentar mais tarde",
    //     });
    //     console.log(error);
    // }

    addToast(severity, summary, detail) {
        this.messageService.add({
            severity: severity,
            summary: summary,
            detail: detail,
        });
    }

    handleEdit(cliente) {
        this.displayForm = true;
        this.clienteForm.setValue({
            id: cliente.id,
            nome: cliente.nome,
            cpf: cliente.cpf,
            rg: cliente.rg,
            dataNascimento: cliente.dataNascimento,
            endereco: cliente.endereco,
            telefone: cliente.telefone,
            email: cliente.email,
        });
    }


    editCliente(cliente){
        this.clienteService.putCliente(cliente).subscribe((response:Cliente) => {
            const index = this.clientes.findIndex((e) => e.id === cliente.id);
            this.clientes[index] = response;
        });
    }

    postCliente(cliente){
        this.clienteService.postCliente(cliente).subscribe((response: Cliente) => {
            this.clientes.push(response);
        });
    }

    handleSubmit(cliente) {
        if (!cliente.id) {
            this.postCliente(cliente);
        } else {
            this.editCliente(cliente);
        }

        this.displayForm = false;

        this.clienteForm.reset();
    }

    deleteCliente(cliente) {
        this.confirmationService.confirm({
            message:
                "Tem certeza que desejar excluir o cliente " + cliente.nome,
            header: "Confirmar exclusão",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                this.clienteService.deleteCliente(cliente.id).subscribe(() => {
                    this.addSuccess(
                        "success",
                        "Deleção",
                        "Cliente apagado com Sucesso!"
                    );
                });
                this.clientes = this.clientes.filter(
                    (val) => val.id !== cliente.id
                );
            },
        });
    }

    showForm() {
        this.clienteForm.reset();
        this.displayForm = true;
    }
    addSuccess(severity, summary, detail) {
        this.messageService.add({
            severity: severity,
            summary: summary,
            detail: detail,
        });
    }

    addErrorToast(error) {
        this.messageService.add({
            severity: "Error",
            summary: "Error inesperado",
            detail: "Error no service",
        });
        console.log(error);
    }
}
