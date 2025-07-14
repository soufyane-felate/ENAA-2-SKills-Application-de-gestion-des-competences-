package org.enaaskills.renduservice.Service;

import org.enaaskills.renduservice.Model.Rendu;
import org.enaaskills.renduservice.Repository.RenduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class RenduService {

    @Autowired
    private RenduRepository renduRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String APPRENANT_SERVICE_URL = "http://localhost:8082/apprenants/";
    private final String FORMATEUR_SERVICE_URL = "http://localhost:8084/formateurs/";
    private final String COMPETENCE_SERVICE_URL = "http://localhost:8060/api/competences/";
    private final String VALIDATION_SERVICE_URL = "http://localhost:8085/validations/";

    public List<Rendu> getAllRendus() {
        return renduRepository.findAll();
    }

    public Optional<Rendu> getRenduById(Long id) {
        return renduRepository.findById(id);
    }

    public Rendu saveRendu(Rendu rendu) {
        return renduRepository.save(rendu);
    }

    public void deleteRendu(Long id) {
        renduRepository.deleteById(id);
    }

    public Rendu updateRendu(Long id, Rendu updatedRendu) {
        return renduRepository.findById(id)
                .map(rendu -> {
                    rendu.setDateDepot(updatedRendu.getDateDepot());
                    rendu.setLien(updatedRendu.getLien());
                    rendu.setApprenantId(updatedRendu.getApprenantId());
                    rendu.setFormateurId(updatedRendu.getFormateurId());
                    rendu.setCompetenceId(updatedRendu.getCompetenceId());
                    rendu.setValidationId(updatedRendu.getValidationId());
                    return renduRepository.save(rendu);
                })
                .orElse(null);
    }

    public Map<String, Object> getApprenantDetails(Long apprenantId) {
        return restTemplate.getForObject(APPRENANT_SERVICE_URL + apprenantId, Map.class);
    }

    public Map<String, Object> getFormateurDetails(Long formateurId) {
        return restTemplate.getForObject(FORMATEUR_SERVICE_URL + formateurId, Map.class);
    }

    public Map<String, Object> getCompetenceDetails(Long competenceId) {
        return restTemplate.getForObject(COMPETENCE_SERVICE_URL + competenceId, Map.class);
    }

    public Map<String, Object> getValidationDetails(Long validationId) {
        return restTemplate.getForObject(VALIDATION_SERVICE_URL + validationId, Map.class);
    }
}
