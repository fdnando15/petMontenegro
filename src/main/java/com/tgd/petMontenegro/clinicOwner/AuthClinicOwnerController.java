package com.tgd.petMontenegro.clinicOwner;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthClinicOwnerController {

    private AuthClinicOwnerService authClinicOwnerService;

    public AuthClinicOwnerController(AuthClinicOwnerService authClinicOwnerService) {
        this.authClinicOwnerService = authClinicOwnerService;
    }

    @PostMapping("/api/loginClinicOwner")
    public AuthPayload verifyEmailPassword(@RequestBody AuthClinicOwner clinicOwner) {
        return authClinicOwnerService.verifyEmailPassword(clinicOwner);
    }

}
