package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.medico.DadosEndereco;
import org.hibernate.validator.constraints.br.CPF;


public record DadosCadastroPaciente(
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @CPF
        String cpf,
        @Valid
        DadosEndereco endereco
) {
}
