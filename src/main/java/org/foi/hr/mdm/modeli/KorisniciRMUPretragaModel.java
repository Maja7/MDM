package org.foi.hr.mdm.modeli;

import org.foi.hr.mdm.entiteti.KorisnikRegistarMaticeUmrlih;

import java.util.List;

public class KorisniciRMUPretragaModel {

    List<KorisnikRegistarMaticeUmrlih> listaKorisnikaRMU;

    public KorisniciRMUPretragaModel() {
    }

    public List<KorisnikRegistarMaticeUmrlih> getListaKorisnikaRMU() {
        return listaKorisnikaRMU;
    }

    public void setListaKorisnikaRMU(List<KorisnikRegistarMaticeUmrlih> listaKorisnikaRMU) {
        this.listaKorisnikaRMU = listaKorisnikaRMU;
    }
}
