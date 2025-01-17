package com.tgd.petMontenegro.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthService authService;
   

    public AuthController(AuthService authService) {
        this.authService = authService;
       
    }

    @PostMapping("/api/loginClinicOwner")
    public ResponseEntity<AuthPayload> verifyEmailPasswordClinicOwner(@RequestBody Auth clinicOwner) {
        try {
            AuthPayload authPayload = authService.authenticateAndGenerateToken(clinicOwner);
            if (authPayload != null) {
                return ResponseEntity.ok(authPayload); // Responde con 200 OK si todo es exitoso
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Responde con 401 si las credenciales son incorrectas
            }
        } catch (Exception e) {
            // Manejo de errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Responde con 500 si ocurre un error
        }
    }


    
    @PostMapping("/api/loginPetOwner")
    public ResponseEntity<AuthPayload> verifyEmailPasswordPetOwner(@RequestBody Auth petOwner) {
        try {
            AuthPayload authPayload = authService.authenticateAndGenerateToken(petOwner);
            if (authPayload != null) {
                return ResponseEntity.ok(authPayload); // Responde con 200 OK si las credenciales son correctas
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Responde con 401 si las credenciales son incorrectas
            }
        } catch (Exception e) {
            // Manejo de errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Responde con 500 si ocurre un error
        }
    }

    @PostMapping("/api/loginVet")
    public ResponseEntity<AuthPayload> verifyEmailPasswordVet(@RequestBody Auth vet) {
        try {
            AuthPayload authPayload = authService.authenticateAndGenerateToken(vet);
            if (authPayload != null) {
                return ResponseEntity.ok(authPayload); // Responde con 200 OK si las credenciales son correctas
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Responde con 401 si las credenciales son incorrectas
            }
        } catch (Exception e) {
            // Manejo de errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Responde con 500 si ocurre un error
        }
    }


}
