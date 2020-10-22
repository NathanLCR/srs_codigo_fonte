import Cliente from "./Cliente";
import Sala from "./Sala";

export interface EditarReservaModel{
    id: number;
    idSala: number;
    sala: Sala;
    idCliente: number;
    cliente: Cliente;
    dataInicio: any;
    dataFim: any;
    total: number;
}