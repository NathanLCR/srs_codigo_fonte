import Equipamento from "./Equipamento";

export default interface ReservaEquipamento{
    idReserva: number;
    idEquipamento: number;
    quantidade: number;
    equipamento: Equipamento; 
}