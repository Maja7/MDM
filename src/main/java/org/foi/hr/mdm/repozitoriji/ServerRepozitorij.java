package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepozitorij extends JpaRepository<Server,Integer> {
    Server findById(int id);
}
