package com.tgd.petMontenegro.pet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByPetOwnerId(Long ownerId);

}
