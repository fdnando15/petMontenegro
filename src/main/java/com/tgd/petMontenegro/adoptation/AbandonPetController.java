package com.tgd.petMontenegro.adoptation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.petOwner.PetOwnerService;
import com.tgd.petMontenegro.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AbandonPetController {

    private AbandonPetService abandonPetService;
    private PetOwnerService petOwnerService;

    public AbandonPetController(AbandonPetService abandonPetService, PetOwnerService petOwnerService) {
        this.abandonPetService = abandonPetService;
        this.petOwnerService = petOwnerService;
    }

    // @PostMapping("/abandonPet")



    @GetMapping("api/abandonPets")
    public ResponseEntity<List<AbandonPet>> getAbandonPets(HttpServletRequest request) {
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
                System.out.println("Email: " + email);
                Long id = petOwnerService.getPetOwnerByEmail(email).getId();
                
                

                // Verificar si el rol es el adecuado
                if (!"PET_OWNER".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }

                // Recuperar y devolver los PetOwners asociados
                List<AbandonPet> abandonPets = abandonPetService.getAbandonPets();

                
                return ResponseEntity.ok(abandonPets);

            } catch (Exception e) {
                // Manejo de errores generales
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

}
