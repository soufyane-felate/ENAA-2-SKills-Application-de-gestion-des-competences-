package org.enaaskills.competenceservice.Service;

import org.enaaskills.competenceservice.Model.Competence;
import org.enaaskills.competenceservice.Repository.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String RENDU_SERVICE_URL = "http://localhost:8083/rendus/";

    public List<Competence> getAllCompetences() {
        return competenceRepository.findAll();
    }

    public Optional<Competence> getCompetenceById(Long id) {
        return competenceRepository.findById(id);
    }

    public Competence saveCompetence(Competence competence) {
        return competenceRepository.save(competence);
    }

    public void deleteCompetence(Long id) {
        competenceRepository.deleteById(id);
    }

    public Competence updateCompetence(Long id, Competence updatedCompetence) {
        return competenceRepository.findById(id)
                .map(competence -> {
                    competence.setCode(updatedCompetence.getCode());
                    competence.setTitre(updatedCompetence.getTitre());
                    competence.setDescription(updatedCompetence.getDescription());
                    competence.setValidation(updatedCompetence.isValidated());
                    return competenceRepository.save(competence);
                })
                .orElse(null);
    }

    public Map<String, Object> getRenduDetails(Long renduId) {
        return restTemplate.getForObject(RENDU_SERVICE_URL + renduId, Map.class);
    }
}
