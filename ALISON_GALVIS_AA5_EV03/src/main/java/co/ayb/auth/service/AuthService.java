package co.ayb.auth.service;

import co.ayb.auth.dto.ApiResponse;
import co.ayb.auth.dto.AuthRequest;
import co.ayb.auth.model.Usuario;
import co.ayb.auth.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ApiResponse registrar(AuthRequest request) {
        if (usuarioRepository.existsByUsuario(request.getUsuario())) {
            return new ApiResponse(false, "El usuario ya está registrado");
        }
        String passwordCifrada = passwordEncoder.encode(request.getPassword());
        usuarioRepository.save(new Usuario(request.getUsuario(), passwordCifrada));
        return new ApiResponse(true, "Registro realizado correctamente");
    }

    public ApiResponse iniciarSesion(AuthRequest request) {
        return usuarioRepository.findByUsuario(request.getUsuario())
                .filter(usuario -> passwordEncoder.matches(request.getPassword(), usuario.getPassword()))
                .map(usuario -> new ApiResponse(true, "Autenticación satisfactoria"))
                .orElseGet(() -> new ApiResponse(false, "Error en la autenticación"));
    }
}
