export default interface Equipamento {
    id: Number;
    nome: String;
    idTipoEquipamento: number;
    precoDiaria: Number;
    obrigatorio: Number;
    tipoEquipamento?: String;
}
