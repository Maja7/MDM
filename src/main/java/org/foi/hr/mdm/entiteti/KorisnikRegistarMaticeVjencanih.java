package org.foi.hr.mdm.entiteti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="korisnik_registar_matice_vjencanih", schema = "heroku_a4428e731088dc6")
public class KorisnikRegistarMaticeVjencanih {
    @Id
    @Column(name = "OIB")
    private Long oib;
    @Column(name="ime")
    private String ime;
    @Column(name="prezime")
    private String prezime;
    @Column(name="djevojacko_prezime")
    private String djevojackoPrezime;
    @Column(name="datum_rodenja")
    private Date datumRodenja;
    @Column(name="datum_vjencanja")
    private Date datumVjencanja;
    @Column(name="OIB_supruznika")
    private Long OIBSupruznika;

    public KorisnikRegistarMaticeVjencanih() {
    }

    public Long getOib() {
        return oib;
    }

    public void setOib(Long oib) {
        this.oib = oib;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getDjevojackoPrezime() {
        return djevojackoPrezime;
    }

    public void setDjevojackoPrezime(String djevojackoPrezime) {
        this.djevojackoPrezime = djevojackoPrezime;
    }

    public Date getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(Date datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public Date getDatumVjencanja() {
        return datumVjencanja;
    }

    public void setDatumVjencanja(Date datumVjencanja) {
        this.datumVjencanja = datumVjencanja;
    }

    public Long getOIBSupruznika() {
        return OIBSupruznika;
    }

    public void setOIBSupruznika(Long OIBSupruznika) {
        this.OIBSupruznika = OIBSupruznika;
    }
}
