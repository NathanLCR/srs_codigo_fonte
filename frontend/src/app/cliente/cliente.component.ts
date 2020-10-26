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
            id: new FormControl(null),
            nome: new FormControl(null, [Validators.required]),
            cpf: new FormControl(null, Validators.required),
            rg: new FormControl(null, Validators.required),
            dataNascimento: new FormControl(null, Validators.required),
            endereco: new FormControl(null, Validators.required),
            email: new FormControl(null, Validators.required),
            telefone: new FormControl(null,Validators.required),
        });
        this.clienteService.getClientes().subscribe((resultado) => {
            this.clientes = resultado;
        });
    }

    get clienteFormControl() {
        return this.clienteForm.controls;
    }

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
    }
    editarCliente(value) {
        this.clienteService.putCliente(value).subscribe(
            (value: Cliente) => {
                this.addToast(
                    "success",
                    "Alterado",
                    "Cliente alterado com sucesso"
                );
                const index = this.clientes.findIndex(
                    (e) => e.id === value.id);
                
                this.clientes[index] = value;
                        
                this.displayForm = false;
        
                this.clienteForm.reset();
            },
            (error) => this.addErrorToast(error)
        );

    }


    addCliente(cliente: Cliente) {
        this.clienteService.postCliente(cliente).subscribe(
            (cliente: Cliente) => {
                this.addToast(
                    "success",
                    "Cadastrado",
                    "Equipamento cadastrado com sucesso"
                );
                this.clientes.push(cliente);

                this.displayForm = false;

                this.clienteForm.reset();
            },
            (error) => {
                this.addErrorToast(error);
            }
        );
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
