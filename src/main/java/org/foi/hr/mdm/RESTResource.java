package org.foi.hr.mdm;

import org.foi.hr.mdm.entiteti.*;
import org.foi.hr.mdm.repozitoriji.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest")
public class RESTResource {
    @Autowired
    KljucnaRijecRepozitorij kljucnaRijecRepozitorij;
    @Autowired
    KorisnikRKDRepozitorij korisnikRKDRepozitorij;
    @Autowired
    KorisnikRMRRepozitorij korisnikRMRRepozitorij;
    @Autowired
    KorisnikRMURepozitorij korisnikRMURepozitorij;
    @Autowired
    KorisnikRMVRepozitorij korisnikRMVRepozitorij;

    @GetMapping(
            path = "/dohvatiKljucneRijeci")
    public List<KljucnaRijec> getAllKR() {
        return kljucnaRijecRepozitorij.findAll();
    }

    //REGISTAR MATICE ROĐENIH
    @GetMapping(
            path = "/usluge/matica-rodjenih")
    public List<KorisnikRegistarMaticeRodjenih> getAllKMR() {
        return korisnikRMRRepozitorij.findAll();
    }

    @GetMapping(
            path = "/usluge/matica-rodjenih/dohvatiPodatkeOKorisniku",
            params = {"OIB"})
    public KorisnikRegistarMaticeRodjenih getKorisnikRMRByOIB(
            @RequestParam("OIB") Long OIB) {
        return korisnikRMRRepozitorij.findByOib(OIB);
    }
    @GetMapping(
            path = "/usluge/matica-rodjenih/postojiKorisnik",
            params = {"OIB"})
    public boolean getPostojiKorisnikRMRByOIB(
            @RequestParam("OIB") Long OIB) {
        KorisnikRegistarMaticeRodjenih k = korisnikRMRRepozitorij.findByOib(OIB);
        if (k!=null){
            return true;
        }
        return false;
    }

    //REGISTAR MATICE UMRLIH
    @GetMapping(
            path = "/usluge/matica-umrlih")
    public List<KorisnikRegistarMaticeUmrlih> getAllKMU() {
        return korisnikRMURepozitorij.findAll();
    }

    @GetMapping(
            path = "/usluge/matica-umrlih/dohvatiPodatkeOKorisniku",
            params = {"OIB"})
    public KorisnikRegistarMaticeUmrlih getKorisnikRMUByOIB(
            @RequestParam("OIB") Long OIB) {
        return korisnikRMURepozitorij.findByOib(OIB);
    }

    @GetMapping(
            path = "/usluge/matica-umrlih/postojiKorisnik",
            params = {"OIB"})
    public boolean getPostojiKorisnikRMUByOIB(
            @RequestParam("OIB") Long OIB) {
        KorisnikRegistarMaticeUmrlih k = korisnikRMURepozitorij.findByOib(OIB);
        if (k!=null){
            return true;
        }
        return false;
    }

    //REGISTAR MATICE VJENČANIH
    @GetMapping(
            path = "/usluge/matica-vjencanih")
    public List<KorisnikRegistarMaticeVjencanih> getAllKMV() {
        return korisnikRMVRepozitorij.findAll();
    }

    @GetMapping(
            path = "/usluge/matica-vjencanih/dohvatiPodatkeOKorisniku",
            params = {"OIB"})
    public KorisnikRegistarMaticeVjencanih getKorisnikRMVByOIB(
            @RequestParam("OIB") Long OIB) {
        return korisnikRMVRepozitorij.findByOib(OIB);
    }

    @GetMapping(
            path = "/usluge/matica-vjencanih/postojiKorisnik",
            params = {"OIB"})
    public boolean getPostojiKorisnikRMVByOIB(
            @RequestParam("OIB") Long OIB) {
        KorisnikRegistarMaticeVjencanih k = korisnikRMVRepozitorij.findByOib(OIB);
        if (k!=null){
            return true;
        }
        return false;
    }

    //REGISTAR KNJIGE DRŽAVLJANA
    @GetMapping(
            path = "/usluge/knjiga-drzavljana")
    public List<KorisnikRegistarKnjigaDrzavljana> getAllKKD() {
        return korisnikRKDRepozitorij.findAll();
    }

    @GetMapping(
            path = "/usluge/knjiga-drzavljana/dohvatiPodatkeOKorisniku",
            params = {"OIB"})
    public KorisnikRegistarKnjigaDrzavljana getKorisnikRKDByOIB(
            @RequestParam("OIB") Long OIB) {
        return korisnikRKDRepozitorij.findByOib(OIB);
    }

    @GetMapping(
            path = "/usluge/knjiga-drzavljana/postojiKorisnik",
            params = {"OIB"})
    public boolean getPostojiKorisnikRKDByOIB(
            @RequestParam("OIB") Long OIB) {
        KorisnikRegistarKnjigaDrzavljana k = korisnikRKDRepozitorij.findByOib(OIB);
        if (k!=null){
            return true;
        }
        return false;
    }
}
