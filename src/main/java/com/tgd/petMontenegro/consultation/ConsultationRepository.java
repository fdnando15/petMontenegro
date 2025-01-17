package com.tgd.petMontenegro.consultation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgd.petMontenegro.petOwner.PetOwner;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    List<Consultation> findByPetId(Long petId);

}
