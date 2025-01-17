package com.tgd.petMontenegro.consultation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.pet.PetRepository;

@Service
public class ConsultationService {

    private ConsultationRepository consultationRepository;
    private PetRepository petRepository;

    @Autowired
    public ConsultationService(ConsultationRepository consultationRepository, PetRepository petRepository) {
        this.consultationRepository = consultationRepository;
        this.petRepository = petRepository;
    }

    public List<Consultation> getConsultationsByOwnerIdandPetId(Long petOwnerId, Long petId) {

            List<Pet> pets = petRepository.findByPetOwnerId(petOwnerId);
            Pet looking = null;

            for (Pet pet:pets){
                if(pet.getId()==petId){
                    looking = pet;
                }
            }
            return consultationRepository.findByPetId(looking.getId());
        
    }


    

}
