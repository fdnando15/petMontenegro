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

    public AuthPayload verifyEmailPasswordClinicOwner(Auth clinicOwner) {
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

    public AuthPayload verifyEmailPasswordPetOwner(Auth petOwner) {
        PetOwner owner = petOwnerRepository.findByEmailAndPassword(petOwner.getEmail(), petOwner.getPassword());
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
