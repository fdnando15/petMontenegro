package com.tgd.petMontenegro.petOwner;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.pet.PetRepository;

@Service
public class PetOwnerService {

    private PetOwnerRepository petOwnerRepository;
    private PetRepository petRepository;

    @Autowired
    public PetOwnerService(PetOwnerRepository petOwnerRepository, PetRepository petRepository) {
        this.petOwnerRepository = petOwnerRepository;
        this.petRepository = petRepository;
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

    public PetOwner getPetOwnerByEmail(String email) {
        PetOwner s =  petOwnerRepository.findByEmail(email);
        System.out.println("Aqui el petOwner" +s.toString());
        return s;
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByPetOwnerId(ownerId);
    }

}
