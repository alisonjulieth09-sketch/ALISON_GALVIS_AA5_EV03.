package co.ayb.auth;

import co.ayb.auth.dto.AuthRequest;
import co.ayb.auth.repository.UsuarioRepository;
import co.ayb.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest {

    private AuthService authService;
    private UsuarioRepository repository;

    @BeforeEach
    void setUp() {
        // Las pruebas principales de integración se pueden ampliar con MockMvc.
        // Esta clase deja documentada la intención de probar registro y login.
        repository = org.mockito.Mockito.mock(UsuarioRepository.class);
        authService = new AuthService(repository, new BCryptPasswordEncoder());
    }

    @Test
    void servicioDebeCrearRespuestaDeErrorCuandoUsuarioNoExiste() {
        org.mockito.Mockito.when(repository.findByUsuario("alison"))
                .thenReturn(java.util.Optional.empty());

        AuthRequest request = new AuthRequest();
        request.setUsuario("alison");
        request.setPassword("1234");

        var response = authService.iniciarSesion(request);

        assertFalse(response.isSuccess());
        assertEquals("Error en la autenticación", response.getMessage());
    }
}
