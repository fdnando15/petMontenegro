package com.tgd.petMontenegro.petOwner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.pet.PetService;

@RestController
public class PetOwnerController {

    private PetService petService;

    private PetOwnerService petOwnerService;

    @Autowired
    public PetOwnerController(PetService petService, PetOwnerService petOwnerService) {
        this.petService = petService;
        this.petOwnerService = petOwnerService;
    }


    @GetMapping("/api/petOwners/{ownerId}/pets")
    public List<Pet> getPetsForOwner(@PathVariable Long ownerId) {
        return petService.getPetsByOwnerId(ownerId);
    }

    @PostMapping("api/petOwners")
    public void registerPetOwner(@RequestBody PetOwner petOwner) {
        petOwnerService.registerPetOwner(petOwner);
    }

}
