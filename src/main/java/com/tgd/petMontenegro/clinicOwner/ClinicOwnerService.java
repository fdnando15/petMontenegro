package com.tgd.petMontenegro.clinicOwner;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgd.petMontenegro.petOwner.PetOwner;
import com.tgd.petMontenegro.petOwner.PetOwnerRepository;

@Service
public class ClinicOwnerService {


    private ClinicOwnerRepository clinicOwnerRepository;
    private PetOwnerRepository petOwnerRepository;

    @Autowired
    public ClinicOwnerService(ClinicOwnerRepository clinicOwnerRepository, PetOwnerRepository petOwnerRepository) {
        this.clinicOwnerRepository = clinicOwnerRepository;
        this.petOwnerRepository = petOwnerRepository;
    }

    public List<ClinicOwner> getAllClinicOwners() {
        return clinicOwnerRepository.findAll();
    }

    public List<PetOwner> getPetOwners(Long id) {
        return petOwnerRepository.findByClinicOwnerId(id);
    }
    public void registerClinicOwner(ClinicOwner clinicOwner) {
        clinicOwnerRepository.save(clinicOwner);
    }

    public void deletePetOwner(Long id, Long clinicOwnerId) {
        PetOwner petOwner = petOwnerRepository.findById(id).orElseThrow(() -> new IllegalStateException("Pet owner with id " + id + " does not exist"));
        if (petOwner.getClinicOwner().getId().equals(clinicOwnerId)) {
            petOwnerRepository.delete(petOwner);
        } else {
            throw new IllegalStateException("Pet owner with id " + id + " does not belong to clinic owner with id " + clinicOwnerId);
        }
    }

    public Long getClinicOwnerId(String email) {
        // TODO Auto-generated method stub
        return clinicOwnerRepository.findByEmail(email).getId();
    }

}
