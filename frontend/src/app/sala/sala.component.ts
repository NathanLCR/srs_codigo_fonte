import { EquipamentoService } from "./../equipamento/equipamento.service";
import { SalaService } from "./sala.service";
import { ConfirmationService } from "primeng/api";
import { Component, OnInit } from "@angular/core";
import { FormArray, FormBuilder, FormControl, FormGroup } from "@angular/forms";
import Sala from "../models/Sala";
import TiposdeSala from "src/app/models/TiposDeSala";

@Component({
    selector: "app-sala",
    templateUrl: "./sala.component.html",
    styleUrls: ["./sala.component.css"],
    providers: [ConfirmationService],
})
export class SalaComponent implements OnInit {
    salas: Sala[];

    displayForm = false;

    displayEquipamentoForm = false;

    salaForm;

    salaEquipamentoForm;

    tiposDeSala = new TiposdeSala();

    equipamentos;

    constructor(
        private salaService: SalaService,
        private formBuilder: FormBuilder,
        private confirmationService: ConfirmationService,
        private equipamentoService: EquipamentoService
    ) {}

    ngOnInit(): void {
        this.salaForm = new FormGroup({
            id: new FormControl(null),
            precoDiaria: new FormControl(null),
            descricao: new FormControl(null),
            capacidade: new FormControl(null),
            idTipoSala: new FormControl(null),
            equipamentos: new FormArray([]),
        });

        this.salaEquipamentoForm = this.formBuilder.group({
            idSala: null,
            idEquipamento: "",
            quantidade: "",
            equipamento: null,
        });

        this.salaService.getSalas().subscribe((resultado) => {
            this.salas = resultado;

            this.salas.forEach((s) => {
                return this.tiposDeSala.getTipoSala(s);
            });
        });

        this.equipamentoService.getEquipamentos().subscribe((resulta) => {
            this.equipamentos = resulta.map((e) => {
                return { label: e.nome, value: e };
            });
        });
    }

    get equipamentoForm() {
        return this.salaForm.get("equipamentos") as FormArray;
    }

    addEquipamento(value) {
        this.displayEquipamentoForm = false;
        value.idEquipamento = value.equipamento.id;
        this.equipamentoForm.value.push(value);
        console.log(value);
        console.log(this.equipamentoForm.value);
    }

    deletar(sala) {
        this.salaService.deleteSala(sala.id).subscribe();
        this.salas = this.salas.filter((val) => val.id !== sala.id);
    }

    atualizar(sala) {
        this.salaService.putSala(sala).subscribe();
    }

    showForm() {
        this.displayForm = true;
    }

    showEquipamentoForm() {
        this.displayEquipamentoForm = true;
    }

    handleSubmit(value: Sala) {
        if (!value.id) {
            this.addSala(value);
        } else {
            this.editSala(value);
        }
    }

    addSala(sala: Sala) {
        this.salaService.postSala(sala).subscribe((response: Sala) => {
            this.salas.push(this.tiposDeSala.getTipoSala(response));

            this.displayForm = false;
        });
    }

    editSala(sala: Sala) {
        this.salaService.putSala(sala).subscribe((response: Sala) => {
            const index = this.salas.findIndex((e) => e.id === sala.id);
            this.salas[index] = this.tiposDeSala.getTipoSala(response);

            this.displayForm = false;
        });
    }

    handleEdit(sala: Sala) {
        console.log(sala);
        this.salaForm.setValue({
            id: sala.id,
            precoDiaria: sala.precoDiaria,
            descricao: sala.descricao,
            capacidade: sala.capacidade,
            idTipoSala: sala.idTipoSala,
            equipamentos: sala.equipamentos,
        });
        console.log(this.salaForm.controls.value);
    }

    handleDelete(sala) {
        this.confirmationService.confirm({
            message: "Tem certeza que desejar excluir a sala " + sala.descricao,
            header: "Confirmar exclusão",
            icon: "pi pi-exclamation-triangle",
            accept: () => {
                this.salaService.deleteSala(sala.id).subscribe();
                this.salas = this.salas.filter((val) => val.id !== sala.id);
            },
        });
    }

    getEquipamento(id) {
        return this.equipamentoService.getEquipamento(id);
    }
}
