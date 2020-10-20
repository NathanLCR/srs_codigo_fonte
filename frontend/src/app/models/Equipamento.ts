export default interface Equipamento {
    id: number;
    nome: String;
    idTipoEquipamento: number;
    precoDiaria: number;
    obrigatorio: number;
    tipoEquipamento?: String;
}
