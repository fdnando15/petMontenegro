package com.tgd.petMontenegro.adoptation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbandoPetRepository extends JpaRepository<AbandonPet, Long> {

}
