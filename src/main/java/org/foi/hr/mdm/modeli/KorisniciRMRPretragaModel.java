package org.foi.hr.mdm.modeli;

import org.foi.hr.mdm.entiteti.KorisnikRegistarMaticeRodjenih;

import java.util.List;

public class KorisniciRMRPretragaModel {
    List<KorisnikRegistarMaticeRodjenih> listaKorisnikaRMR;

    public KorisniciRMRPretragaModel() {
    }

    public List<KorisnikRegistarMaticeRodjenih> getListaKorisnikaRMR() {
        return listaKorisnikaRMR;
    }

    public void setListaKorisnikaRMR(List<KorisnikRegistarMaticeRodjenih> listaKorisnikaRMR) {
        this.listaKorisnikaRMR = listaKorisnikaRMR;
    }
}
