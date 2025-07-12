package org.enaaskills.competenceservice.Repository;

import org.enaaskills.competenceservice.Model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {
}
