package org.foi.hr.mdm.controller;

import org.foi.hr.mdm.IspisPodataka;
import org.foi.hr.mdm.RESTService;
import org.foi.hr.mdm.entiteti.*;
import org.foi.hr.mdm.modeli.KljucnaRijecPretragaModel;
import org.foi.hr.mdm.repozitoriji.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @Autowired
    RESTService restService;
    @Autowired
    RijecServisRepozitorij rijecServisRepozitorij;
    @Autowired
    PrioritetRepozitorij prioritetRepozitorij;
    @Autowired
    WebServisRepozitorij webServisRepozitorij;
    @Autowired
    ServerRepozitorij serverRepozitorij;
    @Autowired
    RegistarRepozitorij registarRepozitorij;
    @Autowired
    InstitucijaRepozitorij institucijaRepozitorij;
    IspisPodataka podaci = new IspisPodataka();
    int razinaPrioriteta = 9999;
    int webServis = 0;
    String dohvacanjePodataka = "";
    int serverid = 0;
    int registarid = 0;
    int institucijaid = 0;
    Long oib=null;

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String homePage(Model model) throws Exception {

        List<KljucnaRijec> kljucneRijeci = restService.getKljucneRijeci().getListaKljucnihRijeci();
        model.addAttribute("kljucneRijeci", kljucneRijeci);
        return "home";
    }

    @RequestMapping(value = "/dohvatiPodatke", method = RequestMethod.POST)
    @ResponseBody
    public IspisPodataka getData(HttpServletRequest request, @RequestParam("oib") String OIB,
                                 @RequestParam("id") int id) {
        podaci = new IspisPodataka();
        dohvacanjePodataka = "";
        razinaPrioriteta = 9999;
        webServis = 0;
        serverid = 0;
        registarid = 0;
        institucijaid = 0;

        oib = Long.parseLong(OIB);
        //dohvati prioritete ključne riječi - relacija RiječServis
        //u bazi pretražujemo tu tablicu, tražimo ključnu riječ s id-em kojeg smo označili na sučelju
        dohvacanjePodataka += "Pretraga prioriteta ključne riječi...";
        List<RijecServis> listaPrioritetaKljucneRijeci = rijecServisRepozitorij.findAllByKljucnaRijecId(id);

        for (RijecServis rs : listaPrioritetaKljucneRijeci) {

            dohvacanjePodataka += "Prioritet razine: " +
                    prioritetRepozitorij.findById(rs.getPrioritetId()).getRazinaPrioriteta() + " [web servis: " +
                    webServisRepozitorij.findById(rs.getWebServisId()).getPutanja() + "]";

            if (rs.getPrioritetId() < razinaPrioriteta) {
                razinaPrioriteta = rs.getPrioritetId();
                webServis = rs.getWebServisId();
            }
        }
        postaviPodatke();

        boolean korisnikPostojiRKD = restService.postojiKorisnikRKD(oib);
        boolean korisnikPostojiRMR = restService.postojiKorisnikRMR(oib);
        boolean korisnikPostojiRMU = restService.postojiKorisnikRMU(oib);
        boolean korisnikPostojiRMV = restService.postojiKorisnikRMV(oib);

        int krug = 0;
        boolean korisnikPronađen = false;

        while (krug < listaPrioritetaKljucneRijeci.size()){
            if (registarid == 1) {
                if (korisnikPostojiRKD) {

                    ispisPodatakaRKD();
                    korisnikPronađen = true;
                } else {
                    dohvacanjePodataka += System.lineSeparator() + "Za korisnika s OIB-om: " + oib +
                            " ne postoje podaci u registru \"Knjiga državljana\"";
                }
            } else if (registarid == 2) {
                if (korisnikPostojiRMR) {
                    ispisPodatakaRMR();
                    korisnikPronađen = true;
                } else {
                    dohvacanjePodataka += System.lineSeparator() + "Za korisnika s OIB-om: " + oib +
                            " ne postoje podaci u registru \"Matica rođenih\"";
                }
            } else if (registarid == 3) {
                if (korisnikPostojiRMU) {
                    ispisPodatakaRMU();
                    korisnikPronađen = true;
                } else {
                    dohvacanjePodataka += System.lineSeparator() + "Za korisnika s OIB-om: " + oib +
                            " ne postoje podaci u registru \"Matica umrlih\"";
                }
            } else if (registarid == 4) {
                if (korisnikPostojiRMV) {
                    ispisPodatakaRMV();

                    korisnikPronađen = true;

                } else {
                    dohvacanjePodataka += System.lineSeparator() + "Za korisnika s OIB-om: " + oib +
                            " ne postoje podaci u registru \"Matica vjenčanih\"";
                }
            }
            krug++;

            if (!korisnikPronađen && krug<listaPrioritetaKljucneRijeci.size()) {

                razinaPrioriteta++;

                List<RijecServis> result = listaPrioritetaKljucneRijeci.stream()
                        .filter(item -> item.getPrioritetId() == razinaPrioriteta)
                        .collect(Collectors.toList());
                if (result.size() != 0) {
                    RijecServis rs = result.get(0);
                    webServis = rs.getWebServisId();

                }
                postaviPodatke();
            }

        }
        if(!korisnikPronađen){
            dohvacanjePodataka+= System.lineSeparator() + "-----------------------------------------"+
                    System.lineSeparator() + "Podaci o korisniku nisu pronađeni u registrima označenim prioritetima"+
                    System.lineSeparator() + "Pretraga ostalih registara sustava RH...";
            razinaPrioriteta=3;
            podaci.setPrioritet(prioritetRepozitorij.findById(3).getRazinaPrioriteta());

            if(korisnikPostojiRKD){
                webServis=1;
                postaviPodatke();
                ispisPodatakaRKD();
            }
            if(korisnikPostojiRMR){
                webServis=2;
                postaviPodatke();
                ispisPodatakaRMR();
            }
            if(korisnikPostojiRMV){
                webServis=3;
                postaviPodatke();
                ispisPodatakaRMV();
            }
            if(korisnikPostojiRMU){
                webServis=4;
                postaviPodatke();
                ispisPodatakaRMU();
            }

        }

        if (!korisnikPostojiRMV && !korisnikPostojiRMR && !korisnikPostojiRMU && !korisnikPostojiRKD) {
            dohvacanjePodataka += System.lineSeparator()+"-------------------------------------------"
                    + "Za korisnika s OIB-om: " + oib + " ne postoje podaci niti u jednom registru";
        }


        podaci.setJson(podaci.toString());
        podaci.setDohvacanjePodataka(dohvacanjePodataka);
        return podaci;

    }



    private void postaviPodatke() {
        podaci.setPrioritet(prioritetRepozitorij.findById(razinaPrioriteta).getRazinaPrioriteta());
        podaci.setWebServis(webServisRepozitorij.findById(webServis).getPutanja());
        dohvacanjePodataka += System.getProperty("line.separator")+"\r\n-------------------------------------------------" +
                "   ODABRANI PODACI ZA DOHVAT PODATAKA:" + System.getProperty("line.separator")+
                System.getProperty("line.separator")+"\r\nPrioritet: " + podaci.getPrioritet() +
                System.getProperty("line.separator")+"Web servis: " + podaci.getWebServis() +
                System.getProperty("line.separator")+"-------------------------------------------------";


        serverid = webServisRepozitorij.findById(webServis).getServerId();
        podaci.setServer(serverRepozitorij.findById(serverid).getUrlServera());

        dohvacanjePodataka += System.lineSeparator() + "Odabrani web servis se nalazi na serveru: " + podaci.getServer();

        registarid = webServisRepozitorij.findById(webServis).getRegistarId();
        podaci.setRegistar(registarRepozitorij.findById(registarid).getNazivRegistra());

        dohvacanjePodataka += System.lineSeparator() + "REGISTAR: " + podaci.getRegistar();

        institucijaid = registarRepozitorij.findById(registarid).getInstitucijaId();
        podaci.setInstitucija(institucijaRepozitorij.findById(institucijaid).getNazivInstitucije());

        dohvacanjePodataka += System.lineSeparator() + "INSTITUCIJA: " + podaci.getInstitucija()
                + System.lineSeparator() +
                System.lineSeparator() + "-------------------------------------------------" +
                System.lineSeparator() + "Provjera osobnog identifikacijskog broja u registru...";
    }

    private void ispisPodatakaRKD() {
        dohvacanjePodataka += System.lineSeparator() + "Za korisnika s OIB-om: " + oib +
                " postoje podaci u registru \"Knjiga državljana\"" +
                System.lineSeparator() + "Dohvat podataka o korisniku..." +
                System.lineSeparator() + "http://localhost:8080/rest//usluge/knjiga-drzavljana/dohvatiPodatkeOKorisniku?OIB=" + oib;

        KorisnikRegistarKnjigaDrzavljana k = restService.getKorisnikRKD(oib);
        podaci.setOib(k.getOib());
        podaci.setIme(k.getIme());
        podaci.setPrezime(k.getPrezime());
        podaci.setDatumRodenja(k.getDatumRodenja());
        podaci.setDrzavljanstvo(k.getDrzavljanstvo());
    }

    private void ispisPodatakaRMR() {
        dohvacanjePodataka += System.lineSeparator() + "Za korisnika s OIB-om: " + oib +
                " postoje podaci u registru \"Matica rođenih\"" +
                System.lineSeparator() + "Dohvaćam podatke o korisniku..." +
                System.lineSeparator() + "http://localhost:8080/rest//usluge/matica-rodjenih/dohvatiPodatkeOKorisniku?OIB=" + oib;

        KorisnikRegistarMaticeRodjenih k = restService.getKorisnikRMR(oib);
        podaci.setOib(k.getOib());
        podaci.setIme(k.getIme());
        podaci.setPrezime(k.getPrezime());
        podaci.setDatumRodenja(k.getDatumRodenja());
    }

    private void ispisPodatakaRMV() {
        dohvacanjePodataka += System.lineSeparator() + "Za korisnika s OIB-om: " + oib +
                " postoje podaci u registru \"Matica vjenčanih\"" +
                System.lineSeparator() + "Dohvaćam podatke o korisniku..." +
                System.lineSeparator() + "http://localhost:8080/rest//usluge/matica-vjencanih/dohvatiPodatkeOKorisniku?OIB=" + oib;

        KorisnikRegistarMaticeVjencanih k = restService.getKorisnikRMV(oib);
        podaci.setOib(k.getOib());
        podaci.setIme(k.getIme());
        podaci.setPrezime(k.getPrezime());
        podaci.setDjevojackoPrezime(k.getDjevojackoPrezime());
        podaci.setDatumRodenja(k.getDatumRodenja());
        podaci.setDatumVjencanja(k.getDatumVjencanja());
        podaci.setOIBsupruznika(k.getOIBSupruznika());
    }

    private void ispisPodatakaRMU() {
        dohvacanjePodataka += System.lineSeparator() + "Za korisnika s OIB-om: " + oib +
                " postoje podaci u registru \"Matica umrlih\"" +
                System.lineSeparator() + "Dohvaćam podatke o korisniku..." +
                System.lineSeparator() + "http://localhost:8080/rest//usluge/matica-umrlih/dohvatiPodatkeOKorisniku?OIB=" + oib;

        KorisnikRegistarMaticeUmrlih k = restService.getKorisnikRMU(oib);
        podaci.setOib(k.getOib());
        podaci.setIme(k.getIme());
        podaci.setPrezime(k.getPrezime());
        podaci.setDatumRodenja(k.getDatumRodenja());
        podaci.setDatumSmrti((k.getDatumSmrti()));
    }
}
