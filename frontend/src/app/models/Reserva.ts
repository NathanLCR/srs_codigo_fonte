import Equipamento from "./Equipamento";

export default interface Reserva {
    id: number;
    idCliente: number;
    idSala: number;
    equipamentos: Equipamento;
    dataInicio: Date;
    dataFim: Date;
}
