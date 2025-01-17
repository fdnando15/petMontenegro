package com.tgd.petMontenegro.vet;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class VetController {

    private VetService vetService;

    public VetController(VetService vetService){
        this.vetService = vetService;
    }

}
