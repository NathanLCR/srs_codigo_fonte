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
    ) { }

    ngOnInit() {
        this.clienteForm = new FormGroup({
            id: new FormControl(""),
            nome: new FormControl("", [Validators.required]),
            cpf: new FormControl("", Validators.required),
            rg: new FormControl("", Validators.required),
            dataNascimento: new FormControl("", Validators.required),
            endereco: new FormControl("", Validators.required),
            email: new FormControl("", Validators.required),
            telefone: new FormControl("", Validators.required)
        });
        this.getAllClientes();
    }

    getAllClientes() {
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
    handleSubmit(cliente) {
        this.getAllClientes();
        const existByCpf = this.clientes.findIndex(e => e.cpf === cliente.cpf && e.id !== cliente.id);
        const existByEmail = this.clientes.findIndex(e => e.email === cliente.email && e.id !== cliente.id)

        if (existByCpf >= 0) {
            this.addCpfToast();
            return;
        }

        if (existByEmail >= 0) {
            this.addEmailToast();
            return;
        }

        if (!cliente.id) {
            this.addCliente(cliente);
        } else {
            this.editarCliente(cliente);
        }
    }
    editarCliente(value) {
        this.clienteService.putCliente(value).subscribe(
            (value: Cliente) => {
                this.addToast(
                    "info",
                    "Sucesso!",
                    "Cliente Atualizado com Sucesso"
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

    compareDates(data) {
        var dataSetada = data; //yyyy-mm-dd
        var hoje = new Date();

        data = new Date(dataSetada)
        let retorno = data >= hoje ? true : false

        return retorno
    }



    addCliente(cliente: Cliente) {


        if (!this.isValidCPF(cliente.cpf)) {
            this.addToast(
                "error",
                "Problema encontrado",
                "CPF Inválido"
            );
        }
        if (this.compareDates(cliente.dataNascimento)) {
            this.addToast(
                "error",
                "Problema encontrado",
                "Data de nascimento inválida"
            );

        } else {

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
            )
        }

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
                    this.clientes = this.clientes.filter(
                        (val) => val.id !== cliente.id
                    );
                },
                    (error) => this.addErrorToast(error));

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
            severity: "error",
            summary: "Erro inesperado",
            detail: "Erro no serviço, favor tentar novamente",
        });
        console.log(error);
    }

    addCpfToast() {
        this.messageService.add({
            severity: "error",
            summary: "Atenção!",
            detail: "Esse CPF já está vinculado a um outro cliente.",
        });
        console.log();
    }

    addEmailToast() {
        this.messageService.add({
            severity: "error",
            summary: "Atenção!",
            detail: "Esse email já está vinculado a um outro cliente.",
        });
        console.log();
    }
    isValidCPF(cpf) {
        if (typeof cpf !== "string") return false
        cpf = cpf.replace(/[\s.-]*/igm, '')
        if (cpf.length !== 11 || !Array.from(cpf).filter(e => e !== cpf[0]).length) {
            return false
        }
        var soma = 0
        var resto
        for (var i = 1; i <= 9; i++)
            soma = soma + parseInt(cpf.substring(i - 1, i)) * (11 - i)
        resto = (soma * 10) % 11
        if ((resto == 10) || (resto == 11)) resto = 0
        if (resto != parseInt(cpf.substring(9, 10))) return false
        soma = 0
        for (var i = 1; i <= 10; i++)
            soma = soma + parseInt(cpf.substring(i - 1, i)) * (12 - i)
        resto = (soma * 10) % 11
        if ((resto == 10) || (resto == 11)) resto = 0
        if (resto != parseInt(cpf.substring(10, 11))) return false
        return true
    }
}
