import Sala from "./Sala";

export default class TiposDeSala {
    labels = [
        {
            label: "Sala de Reunião",
            value: 1,
        },
        {
            label: "Sala de Trabalho",
            value: 2,
        },
        {
            label: "Sala de Vídeo",
            value: 3,
        },
        {
            label: "Sala de Palestras",
            value: 4,
        },
        {
            label: "Auditório",
            value: 5,
        },
    ];

    getTipoSala(sala: Sala) {
        const { label } = this.labels.find((t) => t.value === sala.idTipoSala);

        sala.tipoSala = label;
        return sala;
    }
}
