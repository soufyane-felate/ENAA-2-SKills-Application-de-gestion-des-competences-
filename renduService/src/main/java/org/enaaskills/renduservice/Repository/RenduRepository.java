package org.enaaskills.renduservice.Repository;

import org.enaaskills.renduservice.Model.Rendu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RenduRepository extends JpaRepository<Rendu, Long> {

    @Query("Select DISTINCT briefId from Rendu where Rendu.dateDepot between :D1 and :D2")
      List<Long> getByDateDepot(LocalDate D1 ,LocalDate D2);

}
