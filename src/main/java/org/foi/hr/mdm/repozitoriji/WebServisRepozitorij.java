package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.WebServis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebServisRepozitorij extends JpaRepository<WebServis,Integer> {
    WebServis findById(int id);
}
