package org.enaaskills.brief_service.services;

import org.enaaskills.brief_service.model.Brief;
import org.enaaskills.brief_service.Repository.BriefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class BriefService {

    @Autowired
    private BriefRepository briefRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String FORMATEUR_SERVICE_URL = "http://localhost:8084/formateurs/";

    public List<Brief> getAllBriefs() {
        return briefRepository.findAll();
    }

    public Optional<Brief> getBriefById(Long id) {
        return briefRepository.findById(id);
    }

    public Brief saveBrief(Brief brief) {
        return briefRepository.save(brief);
    }

    public void deleteBrief(Long id) {
        briefRepository.deleteById(id);
    }

    public Brief updateBrief(Long id, Brief updatedBrief) {
        return briefRepository.findById(id)
                .map(brief -> {
                    brief.setTitle(updatedBrief.getTitle());
                    brief.setDescription(updatedBrief.getDescription());
                    brief.setFormateurId(updatedBrief.getFormateurId());
                    return briefRepository.save(brief);
                })
                .orElse(null);
    }

    public Map<String, Object> getFormateurDetails(Long formateurId) {
        return restTemplate.getForObject(FORMATEUR_SERVICE_URL + formateurId, Map.class);
    }
}