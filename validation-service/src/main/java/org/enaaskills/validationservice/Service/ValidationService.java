package org.enaaskills.validationservice.Service;

import org.enaaskills.validationservice.Model.Validation;
import org.enaaskills.validationservice.Repository.ValidationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class ValidationService {

    @Autowired
    private ValidationRepository validationRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String RENDU_SERVICE_URL = "http://localhost:8083/rendus/";
    private final String FORMATEUR_SERVICE_URL = "http://localhost:8084/formateurs/";

    public List<Validation> getAllValidations() {
        return validationRepository.findAll();
    }

    public Optional<Validation> getValidationById(Long id) {
        return validationRepository.findById(id);
    }

    public Validation saveValidation(Validation validation) {
        return validationRepository.save(validation);
    }

    public void deleteValidation(Long id) {
        validationRepository.deleteById(id);
    }

    public Validation updateValidation(Long id, Validation updatedValidation) {
        return validationRepository.findById(id)
                .map(validation -> {
                    validation.setRenduId(updatedValidation.getRenduId());
                    validation.setFormateurId(updatedValidation.getFormateurId());
                    return validationRepository.save(validation);
                })
                .orElse(null);
    }

    public Map<String, Object> getRenduDetails(Long renduId) {
        return restTemplate.getForObject(RENDU_SERVICE_URL + renduId, Map.class);
    }

    public Map<String, Object> getFormateurDetails(Long formateurId) {
        return restTemplate.getForObject(FORMATEUR_SERVICE_URL + formateurId, Map.class);
    }
}
