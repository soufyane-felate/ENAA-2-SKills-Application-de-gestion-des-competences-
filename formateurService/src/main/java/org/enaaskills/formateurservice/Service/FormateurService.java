package org.enaaskills.formateurservice.Service;

import org.enaaskills.formateurservice.Model.Formateur;
import org.enaaskills.formateurservice.Repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormateurService {

    @Autowired
    private FormateurRepository formateurRepository;

    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }

    public Optional<Formateur> getFormateurById(Long id) {
        return formateurRepository.findById(id);
    }

    public Formateur saveFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public void deleteFormateur(Long id) {
        formateurRepository.deleteById(id);
    }

    public Formateur updateFormateurRole(Long formateurId, org.enaaskills.formateurservice.Model.Role newRole) {
        java.util.Optional<Formateur> optionalFormateur = formateurRepository.findById(formateurId);
        if (optionalFormateur.isPresent()) {
            Formateur formateur = optionalFormateur.get();
            formateur.setRole(newRole);
            return formateurRepository.save(formateur);
        }
        return null;
    }
}
