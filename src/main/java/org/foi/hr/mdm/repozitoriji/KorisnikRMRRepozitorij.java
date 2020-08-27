package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.KorisnikRegistarKnjigaDrzavljana;
import org.foi.hr.mdm.entiteti.KorisnikRegistarMaticeRodjenih;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRMRRepozitorij extends JpaRepository<KorisnikRegistarMaticeRodjenih, Long> {
KorisnikRegistarMaticeRodjenih findByOib(Long oib);
}
