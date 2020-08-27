package org.foi.hr.mdm.entiteti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="korisnik_registar_matice_umrlih", schema = "heroku_a4428e731088dc6")
public class KorisnikRegistarMaticeUmrlih {
    @Id
    @Column(name = "OIB")
    private Long oib;
    @Column(name="ime")
    private String ime;
    @Column(name="prezime")
    private String prezime;
    @Column(name="datum_rodenja")
    private Date datumRodenja;
    @Column(name="datum_smrti")
    private Date datumSmrti;

    public KorisnikRegistarMaticeUmrlih() {
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

    public Date getDatumSmrti() {
        return datumSmrti;
    }

    public void setDatumSmrti(Date datumSmrti) {
        this.datumSmrti = datumSmrti;
    }
}
