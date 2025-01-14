package com.tgd.petMontenegro.petOwner;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetOwnerService {

    private PetOwnerRepository petOwnerRepository;

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository) {
        this.petOwnerRepository = petOwnerRepository;
    }

    /*public List<PetOwner> getAllPetOwners(){
        return petOwnerRepository.findAll();
    }*/

    /*public void deletePetOwner(Long petOwnerId) {
        Optional<PetOwner> petOwner = petOwnerRepository.findById(petOwnerId);
        petOwnerRepository.delete(petOwner.get());
    }*/

    public PetOwner registerPetOwner(PetOwner petOwner) {
        return petOwnerRepository.save(petOwner);
    }

    

}
