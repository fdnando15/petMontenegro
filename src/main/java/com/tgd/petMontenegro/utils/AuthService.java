package com.tgd.petMontenegro.utils;

import org.springframework.stereotype.Service;

import com.tgd.petMontenegro.clinicOwner.ClinicOwner;
import com.tgd.petMontenegro.clinicOwner.ClinicOwnerRepository;
import com.tgd.petMontenegro.petOwner.PetOwner;
import com.tgd.petMontenegro.petOwner.PetOwnerRepository;


@Service
public class AuthService {

    private ClinicOwnerRepository clinicOwnerRepository;
    private PetOwnerRepository petOwnerRepository;

    public AuthService(ClinicOwnerRepository clinicOwnerRepository, PetOwnerRepository petOwnerRepository) {
        this.clinicOwnerRepository = clinicOwnerRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    public AuthPayload authenticateAndGenerateToken(Auth auth) {
        // Buscar en ClinicOwner
        ClinicOwner clinicOwner = clinicOwnerRepository.findByEmailAndPassword(auth.getEmail(), auth.getPassword());
        if (clinicOwner != null) {
            return createAuthPayload(clinicOwner);
        }

        // Buscar en PetOwner
        PetOwner petOwner = petOwnerRepository.findByEmailAndPassword(auth.getEmail(), auth.getPassword());
        if (petOwner != null) {
            return createAuthPayload(petOwner);
        }

        // Si no se encuentra en ninguna tabla
        throw new RuntimeException("Invalid email or password");
    }

    private AuthPayload createAuthPayload(BaseClass user) {
        // Generar el token con el rol del usuario
        String token = JwtUtil.generateToken(user.getEmail()+"", user.getRole().toString());
        AuthPayload authPayload = new AuthPayload();
        authPayload.setJwt(token);
        return authPayload;
    }
}

    
    

