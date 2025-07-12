package org.enaaskills.apprenentservice.Service;


import org.enaaskills.apprenentservice.Model.Apprenant;
import org.enaaskills.apprenentservice.Repository.ApprenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class ApprenantService {

    @Autowired
    private ApprenantRepository apprenantRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String RENDU_SERVICE_URL = "http://localhost:8083/rendus/";

    public Apprenant saveApprenant(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    public List<Apprenant> getAllApprenants() {
        return apprenantRepository.findAll();
    }

    public Apprenant getApprenantById(Long id) {
        return apprenantRepository.findById(id).orElse(null);
    }

    public void deleteApprenant(Long id) {
        apprenantRepository.deleteById(id);
    }

    public Apprenant updateApprenant(Long id, Apprenant updatedApprenant) {
        Apprenant existingApprenant = apprenantRepository.findById(id).orElse(null);

        if (existingApprenant != null) {
            existingApprenant.setFirstName(updatedApprenant.getFirstName());
            existingApprenant.setLastName(updatedApprenant.getLastName());
            existingApprenant.setEmail(updatedApprenant.getEmail());
            existingApprenant.setRenduId(updatedApprenant.getRenduId());

            return apprenantRepository.save(existingApprenant);
        } else {
            return null;
        }
    }

    public Map<String, Object> getRenduDetails(Long renduId) {
        return restTemplate.getForObject(RENDU_SERVICE_URL + renduId, Map.class);
    }
}
