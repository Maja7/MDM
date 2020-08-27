package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.KorisnikRegistarKnjigaDrzavljana;
import org.foi.hr.mdm.entiteti.KorisnikRegistarMaticeUmrlih;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRMURepozitorij extends JpaRepository<KorisnikRegistarMaticeUmrlih, Long> {
KorisnikRegistarMaticeUmrlih findByOib(Long oib);
}
