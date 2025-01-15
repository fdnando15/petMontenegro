package com.tgd.petMontenegro.clinicOwner;


import com.tgd.petMontenegro.utils.BaseClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ClinicOwner extends BaseClass{

    private String address;

    public ClinicOwner() {
        this.setRole(UserType.CLINIC_OWNER); // Asigna el tipo de usuario
    }

}
