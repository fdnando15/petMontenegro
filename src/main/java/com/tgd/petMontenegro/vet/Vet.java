package com.tgd.petMontenegro.vet;

import com.tgd.petMontenegro.clinicOwner.ClinicOwner;
import com.tgd.petMontenegro.utils.BaseClass;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "vets")
public class Vet extends BaseClass{

    @ManyToOne(optional = false)
    @JoinColumn(name = "clinic_owner_id")
    private ClinicOwner clinicOwner;


}
