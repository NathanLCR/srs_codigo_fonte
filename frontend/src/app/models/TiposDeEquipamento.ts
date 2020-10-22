import Equipamento from "./Equipamento";

export default class TiposDeEquipamento {
    labels = [
        {
            label: "Móvel",
            value: 1,
        },
        {
            label: "Eletrodoméstico",
            value: 2,
        },
        {
            label: "Informática",
            value: 3,
        },
    ];
    getTipoEquipamento(equipamento: Equipamento) {
        const { label } = this.labels.find(
            (t) => t.value === equipamento.idTipoEquipamento
        );
        equipamento.tipoEquipamento = label;

        return equipamento;
    }
}
