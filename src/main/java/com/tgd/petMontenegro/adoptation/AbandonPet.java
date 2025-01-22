package com.tgd.petMontenegro.adoptation;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import com.tgd.petMontenegro.petOwner.PetOwner;
import com.tgd.petMontenegro.vet.Vet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "abandonPets")
@Entity
public class AbandonPet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private String birthDay;

    @Value("https://www.ecestaticos.com/imagestatic/clipping/b93/4a7/b934a73f42cfe61d874e563914aedf17/estos-son-los-perros-mas-feos-del-mundo.jpg?mtime=1622868118")
    private String url;


}
