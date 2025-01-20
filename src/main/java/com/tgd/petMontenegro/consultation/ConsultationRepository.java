package com.tgd.petMontenegro.consultation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tgd.petMontenegro.petOwner.PetOwner;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    List<Consultation> findByPetId(Long petId);
    List<Consultation> findAllByVetId(Long vetId);

    @Query("SELECT c.slotTime FROM Consultation c WHERE c.vet.id = :vetId AND c.date = :date")
    List<String> findAvailableSlots(@Param("vetId") Long vetId, @Param("date") LocalDate date);
    


}
