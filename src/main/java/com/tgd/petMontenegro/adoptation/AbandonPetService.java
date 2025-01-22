package com.tgd.petMontenegro.adoptation;

import org.springframework.stereotype.Service;

@Service
public class AbandonPetService {

    private AbandoPetRepository abandoPetRepository;

    public AbandonPetService(AbandoPetRepository abandoPetRepository) {
        this.abandoPetRepository = abandoPetRepository;
    }

}
