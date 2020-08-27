package org.foi.hr.mdm.modeli;

import org.foi.hr.mdm.entiteti.KorisnikRegistarMaticeVjencanih;

import java.util.List;

public class KorisniciRMVPretragaModel {
    List<KorisnikRegistarMaticeVjencanih> listaKorisnikaRMV;

    public KorisniciRMVPretragaModel() {
    }

    public List<KorisnikRegistarMaticeVjencanih> getListaKorisnikaRMV() {
        return listaKorisnikaRMV;
    }

    public void setListaKorisnikaRMV(List<KorisnikRegistarMaticeVjencanih> listaKorisnikaRMV) {
        this.listaKorisnikaRMV = listaKorisnikaRMV;
    }
}
