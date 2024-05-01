package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.MotivoCancelamento;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMotivoValido implements ValidadorCancelamentoDeConsulta {
    public void validar(DadosCancelamentoConsulta dados) {
        MotivoCancelamento.fromDescricaoMotivo(dados.motivoCancelamento());
    }
}
