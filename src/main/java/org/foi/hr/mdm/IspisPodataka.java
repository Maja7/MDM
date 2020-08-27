package org.foi.hr.mdm;

import java.util.Date;

public class IspisPodataka {
    private Long oib;
    private String ime;
    private String prezime;
    private String djevojackoPrezime;
    private Date datumRodenja;
    private String bracniStatus;
    private Date datumVjencanja;
    private Long OIBsupruznika;
    private Date datumSmrti;
    private String drzavljanstvo;
    private String putanja;
    private String prioritet;
    private String dohvacanjePodataka;
    private String webServis;
    private String registar;
    private String institucija;
    private String server;
    private String jsonIspis;


    public IspisPodataka() {
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

    public String getBracniStatus() {
        return bracniStatus;
    }

    public void setBracniStatus(String bracniStatus) {
        this.bracniStatus = bracniStatus;
    }

    public Date getDatumVjencanja() {
        return datumVjencanja;
    }

    public void setDatumVjencanja(Date datumVjencanja) {
        this.datumVjencanja = datumVjencanja;
    }

    public Long getOIBsupruznika() {
        return OIBsupruznika;
    }

    public void setOIBsupruznika(Long OIBsupruznika) {
        this.OIBsupruznika = OIBsupruznika;
    }

    public Date getDatumSmrti() {
        return datumSmrti;
    }

    public void setDatumSmrti(Date datumSmrti) {
        this.datumSmrti = datumSmrti;
    }

    public String getDrzavljanstvo() {
        return drzavljanstvo;
    }

    public void setDrzavljanstvo(String drzavljanstvo) {
        this.drzavljanstvo = drzavljanstvo;
    }

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }
    public String getPrioritet() {
        return prioritet;
    }

    public void setPrioritet(String prioritet) {
        this.prioritet = prioritet;
    }
    public String getDohvacanjePodataka() {
        return dohvacanjePodataka;
    }

    public void setDohvacanjePodataka(String dohvacanjePodataka) {
        this.dohvacanjePodataka = dohvacanjePodataka;
    }

    public String getWebServis() {
        return webServis;
    }

    public void setWebServis(String webServis) {
        this.webServis = webServis;
    }

    public String getRegistar() {
        return registar;
    }

    public void setRegistar(String registar) {
        this.registar = registar;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getJson() {
        return jsonIspis;
    }

    public void setJson(String json) {
        this.jsonIspis = json;
    }

    @Override
    public String toString() {
        return "{" +
                "oib:" + oib +
                ", ime:'" + ime + '\'' +
                ", prezime:'" + prezime + '\'' +
                ", djevojackoPrezime:'" + djevojackoPrezime + '\'' +
                ", datumRodenja:" + datumRodenja +
                ", bracniStatus:'" + bracniStatus + '\'' +
                ", datumVjencanja:" + datumVjencanja +
                ", OIBsupruznika:" + OIBsupruznika +
                ", datumSmrti:" + datumSmrti +
                ", drzavljanstvo:'" + drzavljanstvo + '\'' +
                '}';
    }
}
