package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.KljucnaRijec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KljucnaRijecRepozitorij extends JpaRepository<KljucnaRijec, Integer> {
}
