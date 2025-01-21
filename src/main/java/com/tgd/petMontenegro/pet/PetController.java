package com.tgd.petMontenegro.pet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<Void> registerPet(@RequestParam("name") String name,
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
        Pet pet = new Pet();
        pet.setName(name);
        pet.setBirthDay(birthDay);
        pet.setUrl("/img/uploads/" + fileName); // Guardar la URL de la imagen relativa a la carpeta static
        pet.setPetOwner(petOwner);
        petService.registerPet(pet);

        return ResponseEntity.ok().build();

            } catch (Exception e) {
                // Manejar errores genéricos o personalizar según el tipo de excepción
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
    }

}
