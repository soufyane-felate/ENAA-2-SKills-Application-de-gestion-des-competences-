package org.enaaskills.brief_service.controller;

import org.enaaskills.brief_service.model.Brief;
import org.enaaskills.brief_service.services.BriefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BriefController {

    @Autowired
    private BriefService briefService;

    @GetMapping
    public List<Brief> getAllBriefs() {
        return briefService.getAllBriefs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brief> getBriefById(@PathVariable Long id) {
        return briefService.getBriefById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Brief createBrief(@RequestBody Brief brief) {
        return briefService.saveBrief(brief);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brief> updateBrief(@PathVariable Long id, @RequestBody Brief brief) {
        Brief updated = briefService.updateBrief(id, brief);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrief(@PathVariable Long id) {
        briefService.deleteBrief(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/formateurDetails")
    public ResponseEntity<Map<String, Object>> getFormateurDetails(@PathVariable Long id) {
        Optional<Brief> brief = briefService.getBriefById(id);
        if (brief.isPresent() && brief.get().getFormateurId() != null) {
            Map<String, Object> formateurDetails = briefService.getFormateurDetails(brief.get().getFormateurId());
            if (formateurDetails != null) {
                return ResponseEntity.ok(formateurDetails);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}