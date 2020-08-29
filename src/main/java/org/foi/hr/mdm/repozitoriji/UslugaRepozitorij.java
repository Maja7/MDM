package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UslugaRepozitorij extends JpaRepository<Usluga, Integer> {
Usluga findById(int id);
}
