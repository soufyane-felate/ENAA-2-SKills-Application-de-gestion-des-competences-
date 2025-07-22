package org.enaaskills.validationservice.Controller;

import org.enaaskills.validationservice.Model.Validation;
import org.enaaskills.validationservice.Service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
//@RequestMapping("/validations")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @GetMapping
    public List<Validation> getAllValidations() {
        return validationService.getAllValidations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Validation> getValidationById(@PathVariable Long id) {
        return validationService.getValidationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Validation createValidation(@RequestBody Validation validation) {
        return validationService.saveValidation(validation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Validation> updateValidation(@PathVariable Long id, @RequestBody Validation validation) {
        Validation updated = validationService.updateValidation(id, validation);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValidation(@PathVariable Long id) {
        if (validationService.getValidationById(id).isPresent()) {
            validationService.deleteValidation(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<Map<String, Object>> getValidationDetails(@PathVariable Long id) {
        Optional<Validation> validationOptional = validationService.getValidationById(id);
        if (validationOptional.isPresent()) {
            Validation validation = validationOptional.get();
            Map<String, Object> details = new java.util.HashMap<>();
            details.put("validation", validation);

            if (validation.getRenduId() != null) {
                details.put("renduDetails", validationService.getRenduDetails(validation.getRenduId()));
            }
            if (validation.getFormateurId() != null) {
                details.put("formateurDetails", validationService.getFormateurDetails(validation.getFormateurId()));
            }
            return ResponseEntity.ok(details);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
