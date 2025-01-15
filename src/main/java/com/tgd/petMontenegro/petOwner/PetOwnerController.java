package com.tgd.petMontenegro.petOwner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.pet.PetService;
import com.tgd.petMontenegro.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PetOwnerController {

    private PetService petService;

    private PetOwnerService petOwnerService;

    @Autowired
    public PetOwnerController(PetService petService, PetOwnerService petOwnerService) {
        this.petService = petService;
        this.petOwnerService = petOwnerService;
    }


    /*@GetMapping("/api/petOwners/{ownerId}/pets")
    public List<Pet> getPetsForOwner(@PathVariable Long ownerId) {
        return petService.getPetsByOwnerId(ownerId);
    }*/

    @PostMapping("api/petOwners")
    public void registerPetOwner(@RequestBody PetOwner petOwner) {
        petOwnerService.registerPetOwner(petOwner);
    }

    @GetMapping("api/petOwners/pets")
    public ResponseEntity<List<Pet>> getPetsForOwner(HttpServletRequest request) {
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
                System.out.println("Aqui funciona1 " + email);
                Long id = petOwnerService.getPetOwnerByEmail(email).getId();
                System.out.println("Aqui funciona2" + id);
                
                

                // Verificar si el rol es el adecuado
                if (!"PET_OWNER".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }

                // Recuperar y devolver los PetOwners asociados
                List<Pet> pets = petOwnerService.getPetsByOwnerId(id);

                
                return ResponseEntity.ok(pets);

            } catch (Exception e) {
                // Manejo de errores generales
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


}
