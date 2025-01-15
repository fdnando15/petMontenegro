package com.tgd.petMontenegro.clinicOwner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.petMontenegro.petOwner.PetOwner;
import com.tgd.petMontenegro.petOwner.PetOwnerService;
import com.tgd.petMontenegro.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class ClinicOwnerController {

    private PetOwnerService petOwnerService;

    private ClinicOwnerService clinicOwnerService;

    @Autowired
    public ClinicOwnerController(PetOwnerService petOwnerService, ClinicOwnerService clinicOwnerService) {
        this.petOwnerService = petOwnerService;
        this.clinicOwnerService = clinicOwnerService;
    }
    
    
    @GetMapping("/api/petOwners")
    public List<PetOwner> getPetOwners(HttpServletRequest request) {
        //Get the authorization token from the request header
        String token = request.getHeader("Authorization").split(" ")[1];
        //Check if the token is valid
        System.out.println(token);
        Long id = Long.parseLong(JwtUtil.extractUsername(token));

        return clinicOwnerService.getPetOwners(id);
    }


    @RequestMapping(value = "api/petOwners/{id}",method = RequestMethod.DELETE)
    public void deletePetOwner(@PathVariable Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1];
        //Check if the token is valid
        System.out.println(token);
        Long ClinicOwnerid = Long.parseLong(JwtUtil.extractUsername(token));
        clinicOwnerService.deletePetOwner(id, ClinicOwnerid);
    }

    @PostMapping("/api/registerClinicOwner")
    public void registerClinicOwner(@RequestBody ClinicOwner clinicOwner) {
        clinicOwnerService.registerClinicOwner(clinicOwner);
    }

    @GetMapping("/api/clinicOwners")
    public List<ClinicOwner> getClinicOwners() {
        return clinicOwnerService.getAllClinicOwners();
    }

}
