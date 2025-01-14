package com.tgd.petMontenegro.clinicOwner;


import org.springframework.data.jpa.repository.JpaRepository;


public interface ClinicOwnerRepository extends JpaRepository<ClinicOwner, Long> {

    ClinicOwner findByEmailAndPassword(String email, String password);

}
