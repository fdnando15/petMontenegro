package com.tgd.petMontenegro.consultation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.petMontenegro.pet.Pet;
import com.tgd.petMontenegro.petOwner.PetOwnerService;
import com.tgd.petMontenegro.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ConsultationController {


    private ConsultationService consultationService;
    private PetOwnerService petOwnerService;

    @Autowired
    public ConsultationController(ConsultationService consultationService, PetOwnerService petOwnerService) {
        this.consultationService = consultationService;
        this.petOwnerService = petOwnerService;
    }


    @GetMapping("/api/consultations/{petId}")
    public ResponseEntity<List<Consultation>> getConsultations(HttpServletRequest request, @PathVariable Long petId) {


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
                Long petOwnerId = petOwnerService.getPetOwnerByEmail(email).getId();
                System.out.println("Aqui funciona2" + petOwnerId);
                
                

                // Verificar si el rol es el adecuado
                if (!"PET_OWNER".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }

                // Recuperar y devolver los PetOwners asociados
                List<Consultation> consultations = consultationService.getConsultationsByOwnerIdandPetId(petOwnerId, petId);

                
                return ResponseEntity.ok(consultations);

            } catch (Exception e) {
                // Manejo de errores generales
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        
    

    @GetMapping("api/availableSlots/{VetId}/{date}")
    public List<String> getAvailableSlots(@PathVariable Long VetId, @PathVariable LocalDate date) {
        return consultationService.getAvailableSlots(VetId, date);
    }


    @PostMapping("/api/newConsultation/{petId}")
    public ResponseEntity<Void> newConsultation(HttpServletRequest request, @PathVariable Long petId, @RequestBody Consultation consultation) {


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
                Long petOwnerId = petOwnerService.getPetOwnerByEmail(email).getId();
                System.out.println("Aqui funciona2" + petOwnerId);
                
                

                // Verificar si el rol es el adecuado
                if (!"PET_OWNER".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }

                // Recuperar y devolver los PetOwners asociados
               
                consultationService.newConsultation(consultation, petId);
                
                return ResponseEntity.noContent().build();

            } catch (Exception e) {
                // Manejo de errores generales
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }


}