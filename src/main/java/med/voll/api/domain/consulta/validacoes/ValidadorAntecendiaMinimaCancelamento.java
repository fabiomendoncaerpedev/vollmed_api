package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorAntecendiaMinimaCancelamento implements ValidadorCancelamentoDeConsulta{
    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosCancelamentoConsulta dados) {
        Consulta consulta = repository.getReferenceById(dados.idConsulta());
        LocalDateTime horaMinimaDeCancelamento = LocalDateTime.now();

        Duration between = Duration.between(horaMinimaDeCancelamento, consulta.getData());
        if (between.toHours() < 24)
            throw new ValidacaoException("Somente é possível cancelar consulta com antecedência mínima de 24 horas");
    }
}
