package com.tgd.petMontenegro.clinicOwner;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PetOwner>> getPetOwners(HttpServletRequest request) {
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
                Long id = clinicOwnerService.getClinicOwnerId(email);
                // Verificar si el rol es el adecuado
                if (!"CLINIC_OWNER".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }
                // Recuperar y devolver los PetOwners asociados
                List<PetOwner> petOwners = clinicOwnerService.getPetOwners(id);
                return ResponseEntity.ok(petOwners);

            } catch (Exception e) {
                // Manejo de errores generales
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        @RequestMapping(value = "api/petOwners/{id}", method = RequestMethod.DELETE)
        public ResponseEntity<Void> deletePetOwner(@PathVariable Long id, HttpServletRequest request) {
            try {
                String authorizationHeader = request.getHeader("Authorization");
                if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // No autorizado si no hay token
                }
        
                String token = authorizationHeader.replace("Bearer ", "");
                // Extraer rol y username (ID) del token
                String role = JwtUtil.extractRole(token);
                String email = JwtUtil.extractUsername(token);
                Long clinicOwnerId = clinicOwnerService.getClinicOwnerId(email);
        
                // Verificar si el rol es el adecuado
                if (!"CLINIC_OWNER".equals(role)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Prohibir acceso si no es ClinicOwner
                }
        
                clinicOwnerService.deletePetOwner(id, clinicOwnerId);
                return ResponseEntity.noContent().build(); // Éxito, 204 No Content
        
            } catch (Exception e) {
                // Manejar errores genéricos o personalizar según el tipo de excepción
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        

    /*@RequestMapping(value = "api/petOwners/{id}",method = RequestMethod.DELETE)
    public void deletePetOwner(@PathVariable Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").split(" ")[1];
        //Check if the token is valid
        System.out.println(token);
        Long ClinicOwnerid = Long.parseLong(JwtUtil.extractUsername(token));
        clinicOwnerService.deletePetOwner(id, ClinicOwnerid);
    }*/


    @PostMapping("/api/registerClinicOwner")
    public void registerClinicOwner(@RequestBody ClinicOwner clinicOwner) {
        clinicOwnerService.registerClinicOwner(clinicOwner);
    }

    @GetMapping("/api/clinicOwners")
    public List<ClinicOwner> getClinicOwners() {
        return clinicOwnerService.getAllClinicOwners();
    }

}
