package com.tgd.petMontenegro.consultation;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DTOconsultation {

    private Long id;
    private String subject;

    private LocalDate date;
    private String description;

    private SlotTime slotTime;

    private Integer petId;



}
