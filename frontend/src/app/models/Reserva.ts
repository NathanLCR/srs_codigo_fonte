import Cliente from "./Cliente";
import Equipamento from "./Equipamento";
import Sala from "./Sala";

export default interface Reserva {
    id?: number;
    idCliente: number;
    cliente?: Cliente;
    idSala: number;
    sala?: Sala;
    equipamentos: Equipamento[];
    dataInicio: Date;
    dataFim: Date;
}
