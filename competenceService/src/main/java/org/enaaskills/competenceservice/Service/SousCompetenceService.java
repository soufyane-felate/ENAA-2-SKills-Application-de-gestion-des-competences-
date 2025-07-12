package org.enaaskills.competenceservice.Service;

import org.enaaskills.competenceservice.Model.SousCompetence;
import org.enaaskills.competenceservice.Repository.SousCompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SousCompetenceService {

    @Autowired
    private SousCompetenceRepository sousCompetenceRepository;

    public List<SousCompetence> getAllSousCompetences() {
        return sousCompetenceRepository.findAll();
    }

    public Optional<SousCompetence> getSousCompetenceById(Long id) {
        return sousCompetenceRepository.findById(id);
    }

    public SousCompetence saveSousCompetence(SousCompetence sousCompetence) {
        return sousCompetenceRepository.save(sousCompetence);
    }

    public void deleteSousCompetence(Long id) {
        sousCompetenceRepository.deleteById(id);
    }
}
