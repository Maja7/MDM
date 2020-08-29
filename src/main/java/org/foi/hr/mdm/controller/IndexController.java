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
    @Autowired
    PodatakRepozitorij podatakRepozitorij;
    @Autowired
    PodatakURegistruRepozitorij podatakURegistruRepozitorij;
    @Autowired
    UslugaRepozitorij uslugaRepozitorij;
    @Autowired
    NacinIsporukeRepozitorij nacinIsporukeRepozitorij;

    IspisPodataka podaci = new IspisPodataka();

    int razinaPrioriteta = 9999;
    int webServis = 0;
    String dohvacanjePodataka = "";
    int serverid = 0;
    int registarid = 0;
    int institucijaid = 0;
    Long oib = null;
    int uslugaId = 4;

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String homePage(Model model) throws Exception {

        List<KljucnaRijec> kljucneRijeci = restService.getKljucneRijeci().getListaKljucnihRijeci();
        List<Podatak> listaPodataka = podatakRepozitorij.findAll();
        model.addAttribute("kljucneRijeci", kljucneRijeci);
        model.addAttribute("podaci", listaPodataka);
        return "home";
    }

    @RequestMapping(value = "/dohvatiPodatke", method = RequestMethod.POST)
    @ResponseBody
    public IspisPodataka getData(HttpServletRequest request, @RequestParam("oib") String OIB,
                                 @RequestParam("id") int id, @RequestParam("pIme") boolean ime,
                                 @RequestParam("pPrezime") boolean prezime, @RequestParam("pDatumRodenja") boolean datumRodjenja,
                                 @RequestParam("pDrzavljanstvo") boolean drzavljanstvo,
                                 @RequestParam("pDjevojackoPrezime") boolean djevojackoPrezime,
                                 @RequestParam("pDatumVjencanja") boolean datumVjencanja,
                                 @RequestParam("pOIBSupruznika") boolean OIBsupruznika,
                                 @RequestParam("pDatumSmrti") boolean datumSmrti) {
        podaci = new IspisPodataka();
        dohvacanjePodataka = "";
        razinaPrioriteta = 9999;
        webServis = 0;
        serverid = 0;
        registarid = 0;
        institucijaid = 0;

        oib = Long.parseLong(OIB);

        Usluga u = uslugaRepozitorij.findById(uslugaId);
        podaci.setUsluga(u.getNazivUsluge());
        podaci.setNacinIsporuke(nacinIsporukeRepozitorij.findById(u.getNacinIsporukeId()).getNacinIsporuke());

        dohvacanjePodataka += "Usluga: "+podaci.getUsluga()+ System.lineSeparator() +
                "Nacin isporuke usluge: "+podaci.getNacinIsporuke();

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

        while (krug < listaPrioritetaKljucneRijeci.size()) {
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

            if (!korisnikPronađen && krug < listaPrioritetaKljucneRijeci.size()) {

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
        if (!korisnikPronađen) {
            dohvacanjePodataka += System.lineSeparator() + "-----------------------------------------" +
                    System.lineSeparator() + "Podaci o korisniku nisu pronađeni u registrima označenim prioritetima" +
                    System.lineSeparator() + "Pretraga ostalih registara sustava RH...";
            razinaPrioriteta = 3;
            podaci.setPrioritet(prioritetRepozitorij.findById(3).getRazinaPrioriteta());

            if (korisnikPostojiRKD) {
                webServis = 1;
                postaviPodatke();
                ispisPodatakaRKD();
            }
            if (korisnikPostojiRMR) {
                webServis = 2;
                postaviPodatke();
                ispisPodatakaRMR();
            }
            if (korisnikPostojiRMV) {
                webServis = 3;
                postaviPodatke();
                ispisPodatakaRMV();
            }
            if (korisnikPostojiRMU) {
                webServis = 4;
                postaviPodatke();
                ispisPodatakaRMU();
            }

        }

        if (!korisnikPostojiRMV && !korisnikPostojiRMR && !korisnikPostojiRMU && !korisnikPostojiRKD) {
            dohvacanjePodataka += System.lineSeparator() + "-------------------------------------------"
                    + "Za korisnika s OIB-om: " + oib + " ne postoje podaci niti u jednom registru";
        }

        if (ime || prezime || datumRodjenja || drzavljanstvo || datumVjencanja || djevojackoPrezime ||
                OIBsupruznika || datumSmrti) {
            dohvacanjePodataka += "-----------------------------------------" +
                    "Dohvaćanje dodatno označenih podataka ... ";
        } else {
            dohvacanjePodataka += "Niti jedan dodatni podatak nije označen. " +
                    "Dohvaćanje podataka za korisnika je završeno.";
        }
        if (ime) {
            PodatakURegistru pr = podatakURegistruRepozitorij.findByPodatakId(1);
            int reg = pr.getRegistarId();
            //na temelju registra bi trebali doći do podataka
            //ovo je malo hardkodirano jer su relacije s korisnicima odvojene
            if (korisnikPostojiRMR) {
                podaci.setIme(restService.getKorisnikRMR(oib).getIme());
            } else {
                dohvacanjePodataka += "U definiranom registru nema podatka [ime] za korisnika s oibom: " + oib;
            }

        }
        if (prezime) {
            PodatakURegistru pr = podatakURegistruRepozitorij.findByPodatakId(2);
            int reg = pr.getRegistarId();
            if (korisnikPostojiRMR) {
                podaci.setPrezime(restService.getKorisnikRMR(oib).getPrezime());
            } else {
                dohvacanjePodataka += "U definiranom registru nema podatka [prezime] za korisnika s oibom: " + oib;
            }

        }
        if (datumRodjenja) {
            PodatakURegistru pr = podatakURegistruRepozitorij.findByPodatakId(3);
            int reg = pr.getRegistarId();
            if (korisnikPostojiRMR) {
                podaci.setDatumRodenja(restService.getKorisnikRMR(oib).getDatumRodenja());
            } else {
                dohvacanjePodataka += "U definiranom registru nema podatka [datum rođenja] za korisnika s oibom: " + oib;
            }
        }
        if (drzavljanstvo) {
            PodatakURegistru pr = podatakURegistruRepozitorij.findByPodatakId(4);
            int reg = pr.getRegistarId();
            if (korisnikPostojiRKD) {
                podaci.setDrzavljanstvo(restService.getKorisnikRKD(oib).getDrzavljanstvo());
            } else {
                dohvacanjePodataka += "U definiranom registru nema podatka [državljanstvo] za korisnika s oibom: " + oib;
            }
        }
        if (djevojackoPrezime) {
            PodatakURegistru pr = podatakURegistruRepozitorij.findByPodatakId(5);
            int reg = pr.getRegistarId();
            if (korisnikPostojiRMV) {
                podaci.setDjevojackoPrezime(restService.getKorisnikRMV(oib).getDjevojackoPrezime());
            } else {
                dohvacanjePodataka += "U definiranom registru nema podatka [djevojačko prezime] za korisnika s oibom: " + oib;
            }

        }
        if (datumVjencanja) {
            PodatakURegistru pr = podatakURegistruRepozitorij.findByPodatakId(6);
            int reg = pr.getRegistarId();
            if (korisnikPostojiRMV) {
                podaci.setDatumVjencanja(restService.getKorisnikRMV(oib).getDatumVjencanja());
            } else {
                dohvacanjePodataka += "U definiranom registru nema podatka [datum vjenčanja] za korisnika s oibom: " + oib;
            }

        }
        if (OIBsupruznika) {
            PodatakURegistru pr = podatakURegistruRepozitorij.findByPodatakId(7);
            int reg = pr.getRegistarId();
            if (korisnikPostojiRMV) {
                podaci.setOIBsupruznika(restService.getKorisnikRMV(oib).getOIBSupruznika());
            } else {
                dohvacanjePodataka += "U definiranom registru nema podatka [OIB supružnika] za korisnika s oibom: " + oib;
            }

        }
        if (datumSmrti) {
            PodatakURegistru pr = podatakURegistruRepozitorij.findByPodatakId(8);
            int reg = pr.getRegistarId();
            if (korisnikPostojiRMU) {
                podaci.setDatumSmrti(restService.getKorisnikRMU(oib).getDatumSmrti());
            } else {
                dohvacanjePodataka += "U definiranom registru nema podatka [datum smrti] za korisnika s oibom: " + oib;
            }

        }

        podaci.setJson(podaci.toString());
        podaci.setDohvacanjePodataka(dohvacanjePodataka);
        return podaci;

    }


    private void postaviPodatke() {
        podaci.setPrioritet(prioritetRepozitorij.findById(razinaPrioriteta).getRazinaPrioriteta());
        podaci.setWebServis(webServisRepozitorij.findById(webServis).getPutanja());
        dohvacanjePodataka += System.getProperty("line.separator") + "\r\n-------------------------------------------------" +
                "   ODABRANI PODACI ZA DOHVAT PODATAKA:" + System.getProperty("line.separator") +
                System.getProperty("line.separator") + "\r\nPrioritet: " + podaci.getPrioritet() +
                System.getProperty("line.separator") + "Web servis: " + podaci.getWebServis() +
                System.getProperty("line.separator") + "-------------------------------------------------";


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
