export default interface Equipamento{
    id: Number;
    nome: String;
    idTipoEquipamento: Number;
    precoDiaria: Number;
    obrigatorio: Number;
    tipoEquipamento?: String;
}