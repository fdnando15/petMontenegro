package com.tgd.petMontenegro.pet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    
    private PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByPetOwnerId(ownerId);
    }

}
