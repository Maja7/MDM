package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.KorisnikRegistarKnjigaDrzavljana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRKDRepozitorij extends JpaRepository<KorisnikRegistarKnjigaDrzavljana, Long> {
KorisnikRegistarKnjigaDrzavljana findByOib(Long oib);
}
