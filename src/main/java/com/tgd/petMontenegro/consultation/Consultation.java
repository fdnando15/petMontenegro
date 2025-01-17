package com.tgd.petMontenegro.consultation;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.vet.Vet;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consultations")
@Getter
@Setter
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;

    private LocalDate date;
    private String description;

    @Enumerated(EnumType.STRING)
    private SlotTime slotTime;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pet pet;

    @ManyToOne
    private Vet vet;

}
