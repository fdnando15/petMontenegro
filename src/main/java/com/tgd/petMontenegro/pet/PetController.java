package com.tgd.petMontenegro.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.petMontenegro.consultation.ConsultationService;

@RestController
public class PetController {

    private PetService petService;

    private ConsultationService consultationService;

    @Autowired
    public PetController(PetService petService, ConsultationService consultationService) {
        this.petService = petService;
        this.consultationService = consultationService;
    }

}
