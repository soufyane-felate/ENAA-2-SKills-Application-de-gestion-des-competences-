package org.enaaskills.validationservice.Repository;

import org.enaaskills.validationservice.Model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long> {
}
