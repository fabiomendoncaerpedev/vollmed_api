package med.voll.api.controller;

import med.voll.api.domain.usuario.DadosAutenticacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication var_authentication = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
