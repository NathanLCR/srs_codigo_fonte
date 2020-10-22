import { Component, OnInit } from "@angular/core";
import Equipamento from "../models/Equipamento";
import { EquipamentoService } from "./equipamento.service";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ConfirmationService, MessageService } from "primeng/api";
import TiposDeEquipamento from "../models/TiposDeEquipamento";

@Component({
    selector: "app-equipamento",
    templateUrl: "./equipamento.component.html",
    styleUrls: ["./equipamento.component.css"],
    providers: [ConfirmationService],
})
export class EquipamentoComponent implements OnInit {
    equipamentos: Equipamento[];

    displayForm = false;

    equipamentoForm;

    tiposDeEquipamento = new TiposDeEquipamento();

    constructor(
        private equipamentoService: EquipamentoService,
        private confirmationService: ConfirmationService,
        private messageService: MessageService
    ) {}

    ngOnInit(): void {
        this.equipamentoForm = new FormGroup({
            id: new FormControl(null),
            nome: new FormControl(null, [Validators.required]),
            idTipoEquipamento: new FormControl(null, Validators.required),
            precoDiaria: new FormControl(null, [
                Validators.required,
                Validators.min(0),
            ]),
        });
        this.equipamentoService
            .getEquipamentos()
            .subscribe((resultado: Equipamento[]) => {
                this.equipamentos = resultado;
                this.equipamentos.forEach((e) =>
                    this.tiposDeEquipamento.getTipoEquipamento(e)
                );
            });
    }

    get equipamentoFormControl() {
        return this.equipamentoForm.controls;
    }

    handleDelete(equipamento: Equipamento) {
        this.confirmationService.confirm({
            message:
                "Tem certeza que desejar excluir o equipamento " +
                equipamento.nome,
            header: "Confirmar exclusão",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                this.equipamentoService
                    .deleteEquipamento(equipamento.id)
                    .subscribe(
                        () => {
                            this.equipamentos = this.equipamentos.filter(
                                (val) => val.id !== equipamento.id
                            );
                            this.addToast(
                                "success",
                                "Deletado",
                                "Equipamento deletado com sucesso"
                            );
                        },
                        (error) => this.addErrorToast(error)
                    );
            },
        });
    }

    showForm() {
        this.equipamentoForm.reset();

        this.displayForm = true;
    }

    handleEdit(equipamento: Equipamento) {
        this.equipamentoForm.setValue({
            id: equipamento.id,
            nome: equipamento.nome,
            idTipoEquipamento: equipamento.idTipoEquipamento,
            precoDiaria: equipamento.precoDiaria,
        });
    }

    handleSubmit(value: Equipamento) {
        if (!value.id) {
            this.addEquipamento(value);
        } else {
            this.editEquipamento(value);
        }
    }

    addEquipamento(equipamento: Equipamento) {
        this.equipamentoService.postEquipamento(equipamento).subscribe(
            (response: Equipamento) => {
                this.addToast(
                    "success",
                    "Cadastrado",
                    "Equipamento cadastrado com sucesso"
                );

                this.equipamentos.push(
                    this.tiposDeEquipamento.getTipoEquipamento(response)
                );

                this.displayForm = false;

                this.equipamentoForm.reset();
            },
            (error) => {
                this.addErrorToast(error);
            }
        );
    }

    editEquipamento(equipamento: Equipamento) {
        this.equipamentoService.putEquipamento(equipamento).subscribe(
            (response: Equipamento) => {
                this.addToast(
                    "success",
                    "Alterado",
                    "Equipamento alterado com sucesso"
                );
                const index = this.equipamentos.findIndex(
                    (e) => e.id === equipamento.id
                );
                this.equipamentos[
                    index
                ] = this.tiposDeEquipamento.getTipoEquipamento(response);

                this.displayForm = false;

                this.equipamentoForm.reset();
            },
            (error) => this.addErrorToast(error)
        );
    }

    addToast(severity, summary, detail) {
        this.messageService.add({
            severity: severity,
            summary: summary,
            detail: detail,
        });
    }

    addErrorToast(error) {
        this.messageService.add({
            severity: "error",
            summary: "Error no servidor",
            detail: "Error no servidor, favor tentar mais tarde",
        });
        console.log(error);
    }
}
