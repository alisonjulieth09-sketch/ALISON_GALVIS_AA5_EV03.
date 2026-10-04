package co.ayb.auth.controller;

import co.ayb.auth.dto.ApiResponse;
import co.ayb.auth.dto.AuthRequest;
import co.ayb.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Endpoint para registrar nuevos usuarios.
    @PostMapping("/registro")
    public ResponseEntity<ApiResponse> registrar(
            @Valid @RequestBody AuthRequest request) {

        ApiResponse response = authService.registrar(request);

        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Endpoint para validar usuario y contraseña.
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @Valid @RequestBody AuthRequest request) {

        ApiResponse response = authService.iniciarSesion(request);

        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
