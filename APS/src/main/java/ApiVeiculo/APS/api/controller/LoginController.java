package ApiVeiculo.APS.api.controller;

import ApiVeiculo.APS.api.model.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class LoginController {


    @Autowired
    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginRequest) {
        try {
            // Autentica o usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Se a autenticação for bem-sucedida, define o contexto de segurança
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Retorna uma mensagem ou um token de autenticação (JWT, por exemplo)
            return "Login successful!";
        } catch (AuthenticationException e) {
            return "Invalid credentials!";
        }
    }
}

