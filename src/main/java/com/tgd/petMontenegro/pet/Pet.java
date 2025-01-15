package com.tgd.petMontenegro.pet;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.tgd.petMontenegro.petOwner.PetOwner;
import com.tgd.petMontenegro.utils.BaseClass;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pets")
public class Pet{

    //private PetType petType;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String birthDay;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PetOwner petOwner;

}
