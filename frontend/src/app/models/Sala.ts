import Equipamento from "./Equipamento";

export default interface Sala {
    id: number;
    precoDiaria: string;
    descricao: string;
    capacidade: number;
    idTipoSala: number;
    tipoSala?: string;
    equipamentos: Equipamento;
}
