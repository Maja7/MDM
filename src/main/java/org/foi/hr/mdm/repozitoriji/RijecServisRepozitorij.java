package org.foi.hr.mdm.repozitoriji;

import org.foi.hr.mdm.entiteti.RijecServis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RijecServisRepozitorij extends JpaRepository<RijecServis,Integer> {
    RijecServis findByKljucnaRijecId(int id);
    List<RijecServis> findAllByKljucnaRijecId(int id);
}
