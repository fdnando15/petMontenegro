package com.tgd.petMontenegro.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VetService {

    private VetRepostitory vetRepostitory;

    @Autowired
    public VetService(VetRepostitory vetRepostitory){
        this.vetRepostitory=vetRepostitory;
    }

}
