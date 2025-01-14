package com.tgd.petMontenegro.clinicOwner;

import org.springframework.stereotype.Service;


@Service
public class AuthClinicOwnerService {

    private ClinicOwnerRepository clinicOwnerRepository;

    public AuthClinicOwnerService(ClinicOwnerRepository clinicOwnerRepository) {
        this.clinicOwnerRepository = clinicOwnerRepository;
    }

    public AuthPayload verifyEmailPassword(AuthClinicOwner clinicOwner) {
        ClinicOwner owner = clinicOwnerRepository.findByEmailAndPassword(clinicOwner.getEmail(), clinicOwner.getPassword());
        if(owner == null) {
            throw new RuntimeException("Invalid email or password");
        }else {
            String token = JwtUtil.generateToken(owner.getId()+"");
            AuthPayload authPayload = new AuthPayload();
            authPayload.setJwt(token);

            return authPayload;

        }
    }

}
