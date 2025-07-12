package org.enaaskills.brief_service.Repository;

import org.enaaskills.brief_service.model.Brief;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BriefRepository extends JpaRepository<Brief , Long> {
}
