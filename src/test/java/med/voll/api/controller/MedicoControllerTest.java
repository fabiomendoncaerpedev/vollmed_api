package med.voll.api.controller;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;
    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;
    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações inválidas")
    @WithMockUser
    void cadastrar_cenario1() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas")
    @WithMockUser
    void agendar_cenario2() throws Exception {
        Endereco endereco = new Endereco(
                "AV Deputado Pedro valadares",
                "Jardins",
                "49025090",
                "875",
                "Edf Manhattan",
                "Aracaju",
                "Sergipe"
        );
        LocalDateTime data = LocalDateTime.now().plusHours(1);
        Especialidade especialidade = Especialidade.CARDIOLOGIA;
        DadosDetalhamentoMedico dadosDetalhamento = new DadosDetalhamentoMedico(
                null,
                "Phavio",
                "phavio@voll.med",
                "123456",
                "79991935136",
                especialidade,
                endereco
        );
        MockHttpServletResponse response = mvc
                .perform(
                        post("/medicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCadastroMedicoJson.write(
                                        new DadosCadastroMedico(
                                                "Phavio",
                                                "phavio@voll.med",
                                                "79991935136",
                                                "123456",
                                                especialidade,
                                                new DadosEndereco(
                                                        "AV Deputado Pedro valadares",
                                                        "Jardins",
                                                        "49025090",
                                                        "Aracaju",
                                                        "Sergipe",
                                                        "Edf Manhattan",
                                                        "875"
                                                )
                                        )
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        String jsonEsperado = dadosDetalhamentoMedicoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}