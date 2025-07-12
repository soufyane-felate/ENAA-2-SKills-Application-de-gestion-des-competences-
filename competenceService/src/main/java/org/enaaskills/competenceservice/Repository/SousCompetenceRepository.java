package org.enaaskills.competenceservice.Repository;

import org.enaaskills.competenceservice.Model.SousCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousCompetenceRepository extends JpaRepository<SousCompetence, Long> {
}
