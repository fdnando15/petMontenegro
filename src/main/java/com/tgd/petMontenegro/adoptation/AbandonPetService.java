package com.tgd.petMontenegro.adoptation;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AbandonPetService {

    private AbandoPetRepository abandoPetRepository;

    public AbandonPetService(AbandoPetRepository abandoPetRepository) {
        this.abandoPetRepository = abandoPetRepository;
    }

    public List<AbandonPet> getAbandonPets() {
        return abandoPetRepository.findAll();
    }

}
