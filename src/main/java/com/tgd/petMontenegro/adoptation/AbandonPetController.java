package com.tgd.petMontenegro.adoptation;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbandonPetController {

    private AbandonPetService abandonPetService;

    public AbandonPetController(AbandonPetService abandonPetService) {
        this.abandonPetService = abandonPetService;
    }

    // @PostMapping("/abandonPet")


    // @GetMapping("/abandonPet")

}
