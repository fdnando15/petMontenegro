package com.tgd.petMontenegro.consultation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.pet.PetRepository;
import com.tgd.petMontenegro.vet.VetRepostitory;

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

    public List<Consultation> getConsultationsByVetId(Long id){
        return consultationRepository.findAllByVetId(id);
    }

    public List<String> getAvailableSlots(Long vetId, LocalDate date) {

        return consultationRepository.findAvailableSlots(vetId, date);
        
    }


    

}
