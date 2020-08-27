package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.KorisnikRegistarMaticeVjencanih;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRMVRepozitorij extends JpaRepository<KorisnikRegistarMaticeVjencanih, Long> {
KorisnikRegistarMaticeVjencanih findByOib(Long oib);
}
