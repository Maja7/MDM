package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.Institucija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitucijaRepozitorij extends JpaRepository<Institucija, Integer> {
    Institucija findById(int id);
}
