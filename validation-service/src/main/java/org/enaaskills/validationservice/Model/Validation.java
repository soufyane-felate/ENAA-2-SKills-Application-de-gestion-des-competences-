package org.enaaskills.validationservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Validation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long renduId;
    private Long formateurId;

    public Validation() {
    }

    public Validation(Long renduId, Long formateurId) {
        this.renduId = renduId;
        this.formateurId = formateurId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRenduId() {
        return renduId;
    }

    public void setRenduId(Long renduId) {
        this.renduId = renduId;
    }

    public Long getFormateurId() {
        return formateurId;
    }

    public void setFormateurId(Long formateurId) {
        this.formateurId = formateurId;
    }
}
