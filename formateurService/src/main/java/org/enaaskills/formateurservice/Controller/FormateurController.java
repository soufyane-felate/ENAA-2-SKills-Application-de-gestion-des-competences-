package org.enaaskills.formateurservice.Controller;

import org.enaaskills.formateurservice.Model.Formateur;
import org.enaaskills.formateurservice.Service.FormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formateurs")
public class FormateurController {

    @Autowired
    private FormateurService formateurService;

    @GetMapping
    public List<Formateur> getAllFormateurs() {
        return formateurService.getAllFormateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formateur> getFormateurById(@PathVariable Long id) {
        return formateurService.getFormateurById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Formateur createFormateur(@RequestBody Formateur formateur) {
        return formateurService.saveFormateur(formateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formateur> updateFormateur(@PathVariable Long id, @RequestBody Formateur formateur) {
        return formateurService.getFormateurById(id)
                .map(existingFormateur -> {
                    existingFormateur.setNom(formateur.getNom());
                    existingFormateur.setPrenom(formateur.getPrenom());
                    return ResponseEntity.ok(formateurService.saveFormateur(existingFormateur));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormateur(@PathVariable Long id) {
        if (formateurService.getFormateurById(id).isPresent()) {
            formateurService.deleteFormateur(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
