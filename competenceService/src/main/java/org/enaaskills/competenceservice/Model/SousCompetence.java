package org.enaaskills.competenceservice.Model;

import jakarta.persistence.*;

@Entity
public class SousCompetence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private boolean validation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competence_id")
    private Competence competence;

    public SousCompetence() {
    }

    public SousCompetence(String titre, boolean validation) {
        this.titre = titre;
        this.validation = validation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public boolean isValidated() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }
}
