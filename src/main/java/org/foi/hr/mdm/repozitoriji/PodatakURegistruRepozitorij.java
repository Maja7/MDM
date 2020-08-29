package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.PodatakURegistru;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodatakURegistruRepozitorij extends JpaRepository<PodatakURegistru, Integer> {
    PodatakURegistru findByPodatakId(int id);
}
