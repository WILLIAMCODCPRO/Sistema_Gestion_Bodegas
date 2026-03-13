package com.s1.gestion_bodegas.auth;

import com.s1.gestion_bodegas.config.JwtService;
import com.s1.gestion_bodegas.exception.BusinessRuleException;
import com.s1.gestion_bodegas.model.Usuario;
import com.s1.gestion_bodegas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(request.nombreUsuario()).orElseThrow(() -> new BusinessRuleException("Usuario no encontrado"));
        if (!usuario.getPassword().equals(request.password())) {
            throw new BusinessRuleException("Credenciales inválidas");


        }
        String token = jwtService.generateToken(usuario.getNombreUsuario());

        return Map.of("token", token);
    }
}
