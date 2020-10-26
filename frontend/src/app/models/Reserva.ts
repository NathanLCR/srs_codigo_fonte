import Cliente from "./Cliente";
import ReservaEquipamento from "./ReservaEquipamento";
import Sala from "./Sala";

export default interface Reserva {
    id?: number;
    idCliente: number;
    cliente?: Cliente;
    idSala: number;
    sala?: Sala;
    equipamentos: ReservaEquipamento[];
    dataInicio: Date;
    dataFim: Date;
}
