import Equipamento from "./Equipamento";

export default interface SalaEquipamento {
    idSala: number;
    idEquipamento: number;
    quantidade: number;
    equipamento: Equipamento;
}