package com.tgd.petMontenegro.consultation;

import java.time.LocalDate;

import com.tgd.petMontenegro.pet.Pet;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consultation")
@Getter
@Setter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private LocalDate date;
    private String description;

    @Enumerated(EnumType.STRING)
    private SlotTime slotTime;

    @ManyToOne
    private Pet pet;

}
