package com.tgd.petMontenegro.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.petMontenegro.clinicOwner.ClinicOwner;
import com.tgd.petMontenegro.consultation.ConsultationService;
import com.tgd.petMontenegro.petOwner.PetOwner;
import com.tgd.petMontenegro.petOwner.PetOwnerService;
import com.tgd.petMontenegro.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PetController {

    private PetService petService;
    private ConsultationService consultationService;
    private PetOwnerService petOwnerService;

    @Autowired
    public PetController(PetService petService, ConsultationService consultationService, PetOwnerService petOwnerService) {
        this.petService = petService;
        this.consultationService = consultationService;
        this.petOwnerService = petOwnerService;
    }

    @PostMapping("/api/registerPet")
    public ResponseEntity<Void> registerPet(@RequestBody Pet pet, HttpServletRequest request) {
        try {
                // Obtener el token del encabezado de autorización
                String authorizationHeader = request.getHeader("Authorization");
                if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // No autorizado si no hay token
                }
                String token = authorizationHeader.replace("Bearer ", "");
                // Extraer rol y username (ID) del token
                String role = JwtUtil.extractRole(token);
                //Long id = Long.parseLong(JwtUtil.extractUsername(token));
                String email = JwtUtil.extractUsername(token);
                Long id = petOwnerService.getPetOwnerId(email);
                // Verificar si el rol es el adecuado
                if (!"PET_OWNER".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }
                
                PetOwner petOwner = petOwnerService.getPetOwner(id);
                pet.setPetOwner(petOwner);
                System.out.println(petOwner);
                petService.registerPet(pet);
                return ResponseEntity.noContent().build();

            } catch (Exception e) {
                // Manejar errores genéricos o personalizar según el tipo de excepción
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }

}
