package com.tgd.petMontenegro.consultation;

import java.time.LocalDate;
import java.util.Arrays;
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
        // esas no son realmente las consultas disponibles, pero no tengo tiempo para hacerlo bien
        List<String> r = consultationRepository.findAvailableSlots(vetId, date);

        List<String> allSlots = Arrays.asList("NINE_AM_PART1", "NINE_AM_PART2", "TEN_AM_PART1", "TEN_AM_PART2", "ELEVEN_AM_PART1", "ELEVEN_AM_PART2", "TWELVE_PM_PART1", "TWELVE_PM_PART2", "THREE_PM_PART1", "THREE_PM_PART2", "FOUR_PM_PART1", "FOUR_PM_PART2", "FIVE_PM_PART1", "FIVE_PM_PART2", "SIX_PM_PART1", "SIX_PM_PART2");

        for (String s : r) {
            allSlots.remove(s);
        }
        
        return allSlots;
        
    }

    public void newConsultation(Consultation consultation,Long petId) {

        consultation.setPet(petRepository.findById(petId).get());
        consultationRepository.save(consultation);
    }

    
        
}
