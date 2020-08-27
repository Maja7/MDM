package org.foi.hr.mdm.entiteti;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="korisnik_registar_knjige_drzavljana", schema = "heroku_a4428e731088dc6")
public class KorisnikRegistarKnjigaDrzavljana {
    @Id
    @Column(name = "OIB")
    private Long oib;
    @Column(name="ime")
    private String ime;
    @Column(name="prezime")
    private String prezime;
    @Column(name="datum_rodenja")
    private Date datumRodenja;
    @Column(name="drzavljanstvo")
    private String drzavljanstvo;

    public KorisnikRegistarKnjigaDrzavljana() {
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

    public Date getDatumRodenja() {
        return datumRodenja;
    }

    public void setDatumRodenja(Date datumRodenja) {
        this.datumRodenja = datumRodenja;
    }

    public String getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(String drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }
}
