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

    public AbandonPet getAbandonPet(Long id){
        return abandoPetRepository.getById(id);
    }

    public void deleteAbandonPet(Long id) {
        abandoPetRepository.deleteById(id);
    }

    public void registerAbandonPet(AbandonPet abandonPetpet) {
        abandoPetRepository.save(abandonPetpet);
    }

}
