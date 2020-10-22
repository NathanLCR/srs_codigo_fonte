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
            tipoSala: new FormControl(null),
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

            console.log(resultado);
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

    handleSubmit(value) {
        this.salaService.postSala(value).subscribe();
        value = this.tiposDeSala.getTipoSala(value);
        if (!value.id) {
            this.salas.push(value);
            console.log("ok");
        } else {
            const index = this.salas.findIndex((e) => e.id === value.id);
            this.salas[index] = value;
        }
        this.displayForm = false;

        this.salaForm.reset();
    }

    handleEdit(sala) {
        this.salaForm.setValue({
            id: sala.id,
            precoDiaria: sala.precoDiaria,
            descricao: sala.descricao,
            capacidade: sala.capacidade,
            idTipoSala: sala.idTipoSala,
            equipamentos: sala.equipamentos,
        });
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
