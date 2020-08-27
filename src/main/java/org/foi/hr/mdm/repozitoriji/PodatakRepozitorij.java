package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.Podatak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodatakRepozitorij extends JpaRepository<Podatak, Integer> {
}
