package com.tgd.petMontenegro.adoptation;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.pet.PetRepository;
import com.tgd.petMontenegro.petOwner.PetOwner;
import com.tgd.petMontenegro.petOwner.PetOwnerService;
import com.tgd.petMontenegro.utils.JwtUtil;
import com.tgd.petMontenegro.vet.Vet;
import com.tgd.petMontenegro.vet.VetRepostitory;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AbandonPetController {

    private AbandonPetService abandonPetService;
    private PetOwnerService petOwnerService;
    private VetRepostitory vetRepostitory;
    private PetRepository petRepository;
    public AbandonPetController(AbandonPetService abandonPetService, PetOwnerService petOwnerService, VetRepostitory vetRepostitory, PetRepository petRepository) {
        this.abandonPetService = abandonPetService;
        this.petOwnerService = petOwnerService;
        this.vetRepostitory = vetRepostitory;
        this.petRepository = petRepository;
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


        @RequestMapping(value = "api/abandonPets/{id}", method = RequestMethod.POST)
        public ResponseEntity<Void> deleteAbandonPet(@PathVariable Long id, HttpServletRequest request) {
            try {
                String authorizationHeader = request.getHeader("Authorization");
                if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // No autorizado si no hay token
                }
        
                String token = authorizationHeader.replace("Bearer ", "");
                AbandonPet petinitial = abandonPetService.getAbandonPet(id);
                String role = JwtUtil.extractRole(token);
                String email = JwtUtil.extractUsername(token);
                Pet petfinal = new Pet();
                PetOwner petOwner = petOwnerService.getPetOwnerByEmail(email);

                Vet vet = vetRepostitory.getById((long)1);

                petfinal.setName(petinitial.getName());
                petfinal.setBirthDay(petinitial.getBirthDay());
                petfinal.setPetOwner(petOwner);
                petfinal.setUrl(petinitial.getUrl());
                petfinal.setVet(vet);
                // Extraer rol y username (ID) del token

        
                // Verificar si el rol es el adecuado
                if (!"PET_OWNER".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }
        
                abandonPetService.deleteAbandonPet(id);

                petRepository.save(petfinal);

                return ResponseEntity.noContent().build(); // Éxito, 204 No Content
        
            } catch (Exception e) {
                // Manejar errores genéricos o personalizar según el tipo de excepción
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


        @PostMapping("/api/registerAbandonPet")
    public ResponseEntity<Void> registerAbandonPet(@RequestParam("name") String name,
        @RequestParam("birthDay") String birthDay,
        @RequestParam("url") MultipartFile photo,
        HttpServletRequest request) {
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
                String uploadDir = "petMontenegro/src/main/resources/static/img/uploads/";
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileName = photo.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(photo.getInputStream(), filePath);

        // Crear y guardar el pet
        AbandonPet abandonPetpet = new AbandonPet();
        abandonPetpet.setName(name);
        abandonPetpet.setBirthDay(birthDay);
        abandonPetpet.setUrl("/img/uploads/" + fileName); // Guardar la URL de la imagen relativa a la carpeta static
        abandonPetService.registerAbandonPet(abandonPetpet);

        return ResponseEntity.ok().build();

            } catch (Exception e) {
                // Manejar errores genéricos o personalizar según el tipo de excepción
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }


}



