package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.Prioritet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrioritetRepozitorij extends JpaRepository<Prioritet, Integer> {
    Prioritet findById(int id);
}
