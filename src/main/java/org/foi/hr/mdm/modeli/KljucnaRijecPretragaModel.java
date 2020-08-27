package org.foi.hr.mdm.modeli;

import org.foi.hr.mdm.entiteti.KljucnaRijec;

import java.util.List;

public class KljucnaRijecPretragaModel {
    private List<KljucnaRijec> listaKljucnihRijeci;

    public KljucnaRijecPretragaModel() {
    }

    public List<KljucnaRijec> getListaKljucnihRijeci() {
        return listaKljucnihRijeci;
    }

    public void setListaKljucnihRijeci(List<KljucnaRijec> listaKljucnihRijeci) {
        this.listaKljucnihRijeci = listaKljucnihRijeci;
    }
}
