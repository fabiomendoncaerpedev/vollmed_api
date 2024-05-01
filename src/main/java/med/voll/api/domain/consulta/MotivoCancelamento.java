package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;

public enum MotivoCancelamento {
    PACIENTE_DESISTIU("Paciente desistiu"),
    MEDICO_CANCELOU("Medico cancelou"),
    OUTROS("Outros");

    private String descricaoMotivo;

    MotivoCancelamento(String descricaoMotivo) {
        this.descricaoMotivo = descricaoMotivo;
    }

    public static MotivoCancelamento fromDescricaoMotivo(String busca) {
        for (MotivoCancelamento motivoCancelamento: MotivoCancelamento.values()) {
            if (motivoCancelamento.descricaoMotivo.equalsIgnoreCase(busca))
                return motivoCancelamento;
        }

        throw new ValidacaoException("Motivo do Cancelamento inválido. Verifique os motivos de cancelamento disponíveis.");
    }
}
