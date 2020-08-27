package org.foi.hr.mdm.modeli;

import org.foi.hr.mdm.entiteti.KorisnikRegistarKnjigaDrzavljana;

import java.util.List;

public class KorisniciRKDPretragaModel {
    List<KorisnikRegistarKnjigaDrzavljana> listaKorisnikaRKD;

    public KorisniciRKDPretragaModel() {
    }

    public List<KorisnikRegistarKnjigaDrzavljana> getListaKorisnikaRKD() {
        return listaKorisnikaRKD;
    }

    public void setListaKorisnikaRKD(List<KorisnikRegistarKnjigaDrzavljana> listaKorisnikaRKD) {
        this.listaKorisnikaRKD = listaKorisnikaRKD;
    }
}
