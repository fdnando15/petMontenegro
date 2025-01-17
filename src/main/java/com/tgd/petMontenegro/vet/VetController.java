package com.tgd.petMontenegro.vet;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgd.petMontenegro.clinicOwner.ClinicOwner;
import com.tgd.petMontenegro.clinicOwner.ClinicOwnerService;
import com.tgd.petMontenegro.consultation.Consultation;
import com.tgd.petMontenegro.consultation.ConsultationService;
import com.tgd.petMontenegro.petOwner.PetOwner;
import com.tgd.petMontenegro.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class VetController {

    private VetService vetService;
    private ClinicOwnerService clinicOwnerService;
    private ConsultationService consultationService;

    public VetController(VetService vetService,ClinicOwnerService clinicOwnerService, ConsultationService consultationService ){
        this.vetService = vetService;
        this.clinicOwnerService = clinicOwnerService;
        this.consultationService = consultationService;
    }

    @GetMapping("/api/consultations")
    public ResponseEntity<List<Consultation>> getConsultations(HttpServletRequest request) {
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
                Long id = vetService.getVetId(email);
                // Verificar si el rol es el adecuado
                if (!"VET".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }
                // Recuperar y devolver los PetOwners asociados
                List<Consultation> consultations = consultationService.getConsultationsByVetId(id);
                return ResponseEntity.ok(consultations);

            } catch (Exception e) {
                // Manejo de errores generales
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
}
