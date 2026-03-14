package com.s1.gestion_bodegas.auth;

import com.s1.gestion_bodegas.config.JwtService;
import com.s1.gestion_bodegas.exception.BusinessRuleException;
import com.s1.gestion_bodegas.model.Usuario;
import com.s1.gestion_bodegas.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request){

        Usuario usuario = usuarioRepository
                .findByNombreUsuario(request.nombreUsuario())
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado"));

        if(!usuario.getPassword().equals(request.password())){
            throw new BusinessRuleException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(usuario.getNombreUsuario());

        return new LoginResponse(token);
    }

    public Usuario obtenerUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String nombreUsuario = authentication.getName();

        return usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }


}
