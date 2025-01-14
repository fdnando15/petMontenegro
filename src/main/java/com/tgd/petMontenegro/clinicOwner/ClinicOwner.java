package com.tgd.petMontenegro.clinicOwner;


import com.tgd.petMontenegro.models.BaseClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClinicOwner extends BaseClass{

    private String address;

}
