package com.tgd.petMontenegro.utils;

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
    public AuthPayload verifyEmailPasswordClinicOwner(@RequestBody Auth clinicOwner) {
        return authService.verifyEmailPasswordClinicOwner(clinicOwner);
    }

    @PostMapping("/api/loginPetOwner")
    public AuthPayload verifyEmailPasswordPetOwner(@RequestBody Auth petOwner) {
        return authService.verifyEmailPasswordPetOwner(petOwner);
    }

}
