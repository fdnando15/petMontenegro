package com.tgd.petMontenegro.vet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tgd.petMontenegro.clinicOwner.ClinicOwner;


@Repository
public interface VetRepostitory extends JpaRepository<Vet,Long>{

    Vet findByEmailAndPassword(String email, String password);

    Vet findByEmail(String email);

}
