import { EquipamentoService } from './../equipamento/equipamento.service';
import { ClienteService } from './../cliente/cliente.service';
import { Component, OnInit } from "@angular/core";
import { FormArray, FormBuilder, FormControl, FormGroup } from "@angular/forms";
import { MessageService } from "primeng/api";
import { ReservaService } from "./reserva.service";
import { SalaService } from '../sala/sala.service';
import Reserva from '../models/Reserva';
import Sala from '../models/Sala';
import Equipamento from '../models/Equipamento';

@Component({
    selector: "app-listar-reservas",
    templateUrl: "./reservas.component.html",
    styleUrls: ["./reservas.component.css"],
})
export class ReservasComponent implements OnInit {

    reservas: Reserva[];
    reserva: Reserva;

    reservaForm: FormGroup;

    clientes;

    rangeDates: Date[];

    salas;

    equipamentos;

    reservaEquipamentoForm: FormGroup;

    displayForm = false;
    displayEquipamentoForm = false;

    constructor(
        private reservaService: ReservaService,
        private formBuilder: FormBuilder,
        private messageService: MessageService,
        private clienteService: ClienteService,
        private salaService: SalaService,
        private equipamentoService: EquipamentoService,
    ) {
        this.reservaForm = new FormGroup({
            id: new FormControl(null),
            idCliente: new FormControl(null),
            cliente: new FormControl(null),
            idSala: new FormControl(null),
            sala: new FormControl(null),
            dataInicio: new FormControl(null),
            dataFim: new FormControl(null),
            equipamentos: new FormArray([]),
        });

        this.reservaEquipamentoForm = this.formBuilder.group({
            idReserva: null,
            idEquipamento: "",
            quantidade: "",
            equipamento: null
        })
       
    }

    ngOnInit(): void {
        this.getAllReservas();     

        this.clienteService.getClientes().subscribe((resulta) => {
            this.clientes = resulta.map((e) => {
                return { label: e.nome, value: e };
            });
        });

        this.salaService.getSalas().subscribe((response) => {
            this.salas= response.map((e:Sala) => {
                return { label: e.descricao, value: e };
            });
        });

        this.equipamentoService.getEquipamentos().subscribe((response) => {
            this.equipamentos= response.map((e: Equipamento) => {
                return { label: e.nome, value: e };
            });
        });
    }

    get equipamentoForm(){
        return this.reservaForm.get('equipamentos') as FormArray;
    }

    addEquipamento(value){
        this.displayEquipamentoForm = false,
        value.idEquipamento = value.equipamento.id;
        this.equipamentoForm.value.push(value);
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
            severity: "success",
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
    getAllReservas() {
        this.reservaService.getReservas().subscribe((reservas) => {
            this.reservas = reservas;
            reservas.forEach((r: Reserva) => {
                this.salaService.getSalaById(r.idSala).subscribe((response: Sala)=>{
                    r.sala = response;
                });
                this.clienteService.getClienteById(r.idCliente).subscribe((response)=>{
                    r.cliente = response;
                });
                return r;
            })
        });
    }

    showForm() {
        this.displayForm = true;
    }

    showEquipamentoForm() {
        this.displayEquipamentoForm = true;
    }

    handleDelete(value) {
        this.reservaService.deleteReserva(value.id).subscribe(
            () => {
                this.reservaForm.reset();
                this.addDelete();
            },
            () => {
                this.addError();
            }
        );
        this.reservas = this.reservas.filter(
            (val) => val.id !== value.id
        );
    }

    handleEdit(reserva) {
        this.displayForm = true;
        this.reservaForm.setValue({
            id: reserva.id,
            idCliente: reserva.idCliente,
            cliente: reserva.cliente,
            idSala: reserva.idSala,
            sala: reserva.sala,
            dataInicio: reserva.dataInicio,
            dataFim: reserva.dataFim,
            equipamentos: reserva.equipamentos
        });
    }

    handleSubmit(value) {
        console.log(this.rangeDates)
        value.idSala = value.sala.id;
        value.idCliente = value.cliente.id;
        this.displayForm = false;
        this.reservaForm.reset();
        if (!value.id){
            this.addReserva(value);
        }else{
            this.editReserva(value);
        }    
    }

    addReserva(reserva: Reserva){
        this.reservaService.postReserva(reserva).subscribe((response: Reserva)=>{
            reserva = this.getClienteESala(response)
            
            this.reservas.push(reserva);
            this.addSucess();
            
        },() => this.addError())
    };

    editReserva(reserva: Reserva){
        this.reservaService.putReserva(reserva).subscribe((response)=>{
            const index = this.reservas.findIndex(r => r.id === reserva.id);
            this.reservas[index] = response;
            this.addSucess();           
        },() => this.addError())
    };

    getClienteESala(reserva: Reserva){
        this.clienteService.getClienteById(reserva.idCliente).subscribe((r)=>{
            reserva.cliente = r
        })
        this.salaService.getSalaById(reserva.idSala).subscribe(s => {
            reserva.sala = s
        })
        return reserva;
    }
}