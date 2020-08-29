package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.NacinIsporuke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NacinIsporukeRepozitorij extends JpaRepository<NacinIsporuke, Integer> {
    NacinIsporuke findById(int id);
}
