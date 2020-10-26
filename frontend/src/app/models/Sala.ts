import Equipamento from "./Equipamento";
import SalaEquipamento from "./SalaEquipamento";

export default interface Sala {
    id: number;
    precoDiaria: number;
    descricao: string;
    capacidade: number;
    idTipoSala: number;
    tipoSala?: string;
    equipamentos: SalaEquipamento[];
}
