package org.enaaskills.competenceservice.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String titre;
    private String description;
    private boolean validation;

    @OneToMany(mappedBy = "competence", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SousCompetence> sousCompetences = new HashSet<>();

    public Competence() {
    }

    public Competence(String code, String titre, String description, boolean validation) {
        this.code = code;
        this.titre = titre;
        this.description = description;
        this.validation = validation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValidated() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public Set<SousCompetence> getSousCompetences() {
        return sousCompetences;
    }

    public void setSousCompetences(Set<SousCompetence> sousCompetences) {
        this.sousCompetences = sousCompetences;
    }

    public void addSousCompetence(SousCompetence sousCompetence) {
        this.sousCompetences.add(sousCompetence);
        sousCompetence.setCompetence(this);
    }

    public void removeSousCompetence(SousCompetence sousCompetence) {
        this.sousCompetences.remove(sousCompetence);
        sousCompetence.setCompetence(null);
    }
}
