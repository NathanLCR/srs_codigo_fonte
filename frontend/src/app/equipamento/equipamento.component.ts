import { Component, OnInit } from "@angular/core";
import Equipamento from "../models/Equipamento";
import { EquipamentoService } from "./equipamento.service";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ConfirmationService } from "primeng/api";
import { CaptchaModule } from "primeng";

@Component({
    selector: "app-equipamento",
    templateUrl: "./equipamento.component.html",
    styleUrls: ["./equipamento.component.css"],
    providers: [ConfirmationService],
})
export class EquipamentoComponent implements OnInit {
    equipamentos: Equipamento[];

    displayForm = false;

    tiposDeEquipamento = [
        {
            label: "Móvel",
            value: 1,
        },
        {
            label: "Eletrodoméstico",
            value: 2,
        },
        {
            label: "Informática",
            value: 3,
        },
    ];

    equipamentoForm;

    constructor(
        private equipamentoService: EquipamentoService,
        private confirmationService: ConfirmationService
    ) {}

    ngOnInit(): void {
        this.equipamentoForm = new FormGroup({
            id: new FormControl(null),
            nome: new FormControl(null, [Validators.required]),
            idTipoEquipamento: new FormControl(1, Validators.required),
            precoDiaria: new FormControl(null, [
                Validators.required,
                Validators.min(0),
            ]),
            obrigatorio: new FormControl(false),
        });
        this.equipamentoService.getEquipamentos().subscribe((resultado) => {
            this.equipamentos = resultado;
            this.equipamentos.forEach((e) => this.getTipoEquipamento(e));
        });
    }

    getTipoEquipamento(equipamento) {
        const { label } = this.tiposDeEquipamento.find(
            (t) => t.value === equipamento.idTipoEquipamento
        );
        equipamento.tipoEquipamento = label;

        return equipamento;
    }

    get equipamentoFormControl() {
        return this.equipamentoForm.controls;
    }

    handleDelete(equipamento) {
        this.confirmationService.confirm({
            message:
                "Tem certeza que desejar excluir o equipamento " +
                equipamento.nome,
            header: "Confirmar exclusão",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                this.equipamentoService
                    .deleteEquipamento(equipamento.id)
                    .subscribe();
                this.equipamentos = this.equipamentos.filter(
                    (val) => val.id !== equipamento.id
                );
            },
        });
    }

    showForm() {
        this.equipamentoForm.reset();

        this.displayForm = true;
    }

    handleEdit(equipamento) {
        this.equipamentoForm.setValue({
            id: equipamento.id,
            nome: equipamento.nome,
            idTipoEquipamento: equipamento.idTipoEquipamento,
            precoDiaria: equipamento.precoDiaria,
            obrigatorio: equipamento.obrigatorio,
        });
    }

    handleSubmit(value) {
        value.obrigatorio = value.obrigatorio ? 1 : 0;
        this.equipamentoService.postEquipamento(value).subscribe();
        value = this.getTipoEquipamento(value);
        if (!value.id) {
            this.equipamentos.push(value);
        } else {
            const index = this.equipamentos.findIndex((e) => e.id === value.id);
            this.equipamentos[index] = value;
        }
        this.displayForm = false;

        this.equipamentoForm.reset();
    }
}
