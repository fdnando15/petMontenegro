package com.tgd.petMontenegro.petOwner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PetOwnerRepository extends JpaRepository<PetOwner,Long>{

    List<PetOwner> findByClinicOwnerId(Long clinicOwnerId);

    PetOwner findByEmailAndPassword(String email, String password);

    PetOwner findByEmail(String email);



}
