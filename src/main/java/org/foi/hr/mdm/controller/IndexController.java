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
        IspisPodataka podaci = new IspisPodataka();
        String dohvacanjePodataka = "";

        Long oib = Long.parseLong(OIB);
        //dohvati prioritete ključne riječi - relacija RiječServis
        //u bazi pretražujemo tu tablicu, tražimo ključnu riječ s id-em kojeg smo označili na sučelju
        dohvacanjePodataka += "Pretraga prioriteta ključne riječi...";
        List<RijecServis> listaPrioritetaKljucneRijeci = rijecServisRepozitorij.findAllByKljucnaRijecId(id);
        int razinaPrioriteta = 9999;
        int webServis = 0;
        for (RijecServis rs : listaPrioritetaKljucneRijeci) {

            dohvacanjePodataka += System.lineSeparator() + "Prioritet razine: " +
                    prioritetRepozitorij.findById(rs.getPrioritetId()).getRazinaPrioriteta() + " [web servis: " +
                    webServisRepozitorij.findById(rs.getWebServisId()).getPutanja() + "]";

            if (rs.getPrioritetId() < razinaPrioriteta) {
                razinaPrioriteta = rs.getPrioritetId();
                webServis = rs.getWebServisId();
            }
        }

        podaci.setPrioritet(prioritetRepozitorij.findById(razinaPrioriteta).getRazinaPrioriteta());
        podaci.setWebServis(webServisRepozitorij.findById(webServis).getPutanja());
        dohvacanjePodataka += System.lineSeparator() + "-------------------------------------------------" +
                System.lineSeparator() + "    ODABRANI PODACI ZA DOHVAT PODATAKA:" + System.lineSeparator() +
                System.lineSeparator() + "Prioritet: " + podaci.getPrioritet() +
                System.lineSeparator() + "Web servis: " + podaci.getWebServis() +
                System.lineSeparator() + "-------------------------------------------------";


        int serverid = webServisRepozitorij.findById(webServis).getServerId();
        podaci.setServer(serverRepozitorij.findById(serverid).getUrlServera());

        dohvacanjePodataka += System.lineSeparator() + "Odabrani web servis se nalazi na serveru: " + podaci.getServer();

        int registarid = webServisRepozitorij.findById(webServis).getRegistarId();
        podaci.setRegistar(registarRepozitorij.findById(registarid).getNazivRegistra());

        dohvacanjePodataka += System.lineSeparator() + "REGISTAR: " + podaci.getRegistar();

        int institucijaid = registarRepozitorij.findById(registarid).getInstitucijaId();
        podaci.setInstitucija(institucijaRepozitorij.findById(institucijaid).getNazivInstitucije());

        dohvacanjePodataka += System.lineSeparator() + "INSTITUCIJA: " + podaci.getInstitucija()
                + System.lineSeparator() +
                System.lineSeparator() + "-------------------------------------------------" +
                System.lineSeparator() + "Provjera osobnog identifikacijskog broja u registru...";

        boolean korisnikPostojiRKD = restService.postojiKorisnikRKD(oib);
        boolean korisnikPostojiRMR = restService.postojiKorisnikRMR(oib);
        boolean korisnikPostojiRMU  = restService.postojiKorisnikRMU(oib);
        boolean korisnikPostojiRMV  = restService.postojiKorisnikRMV(oib);

        if (registarid == 1) {
            if (korisnikPostojiRKD){
                dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                        " postoje podaci u registru \"Knjiga državljana\""+
                        System.lineSeparator()+"Dohvat podataka o korisniku..."+
                        System.lineSeparator()+"http://localhost:8080/rest//usluge/knjiga-drzavljana/dohvatiPodatkeOKorisniku?OIB="+oib;

                KorisnikRegistarKnjigaDrzavljana k =restService.getKorisnikRKD(oib);
                podaci.setOib(k.getOib());
                podaci.setIme(k.getIme());
                podaci.setPrezime(k.getPrezime());
                podaci.setDatumRodenja(k.getDatumRodenja());
                podaci.setDrzavljanstvo(k.getDrzavljanstvo());

            }else{
                dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                        " ne postoje podaci u registru \"Knjiga državljana\"";
            }
        } else if (registarid == 2) {
            if (korisnikPostojiRMR){
                dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                        " postoje podaci u registru \"Matica rođenih\"" +
                        System.lineSeparator()+"Dohvaćam podatke o korisniku..."+
                        System.lineSeparator()+"http://localhost:8080/rest//usluge/matica-rodjenih/dohvatiPodatkeOKorisniku?OIB="+oib;

                KorisnikRegistarMaticeRodjenih k =restService.getKorisnikRMR(oib);
                podaci.setOib(k.getOib());
                podaci.setIme(k.getIme());
                podaci.setPrezime(k.getPrezime());
                podaci.setDatumRodenja(k.getDatumRodenja());

            }else{
                dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                        " ne postoje podaci u registru \"Matica rođenih\"";
            }
        } else if (registarid == 3) {
            if (korisnikPostojiRMU){
                dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                        " postoje podaci u registru \"Matica umrlih\""+
                        System.lineSeparator()+"Dohvaćam podatke o korisniku..."+
                        System.lineSeparator()+"http://localhost:8080/rest//usluge/matica-umrlih/dohvatiPodatkeOKorisniku?OIB="+oib;

                KorisnikRegistarMaticeUmrlih k =restService.getKorisnikRMU(oib);
                podaci.setOib(k.getOib());
                podaci.setIme(k.getIme());
                podaci.setPrezime(k.getPrezime());
                podaci.setDatumRodenja(k.getDatumRodenja());
                podaci.setDatumSmrti((k.getDatumSmrti()));

            }else{
                dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                        " ne postoje podaci u registru \"Matica umrlih\"";
            }
        } else if (registarid == 4) {
            if (korisnikPostojiRMV){
                dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                        " postoje podaci u registru \"Matica vjenčanih\""+
                        System.lineSeparator()+"Dohvaćam podatke o korisniku..."+
                        System.lineSeparator()+"http://localhost:8080/rest//usluge/matica-vjencanih/dohvatiPodatkeOKorisniku?OIB="+oib;

                KorisnikRegistarMaticeVjencanih k =restService.getKorisnikRMV(oib);
                podaci.setOib(k.getOib());
                podaci.setIme(k.getIme());
                podaci.setPrezime(k.getPrezime());
                podaci.setDjevojackoPrezime(k.getDjevojackoPrezime());
                podaci.setDatumRodenja(k.getDatumRodenja());
                podaci.setDatumVjencanja(k.getDatumVjencanja());
                podaci.setOIBsupruznika(k.getOIBSupruznika());


            }else{
                dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                        " ne postoje podaci u registru \"Matica vjenčanih\"";
            }
        }
        if(!korisnikPostojiRMV && !korisnikPostojiRMR && !korisnikPostojiRMU && !korisnikPostojiRKD){
            dohvacanjePodataka+= System.lineSeparator()+"Za korisnika s OIB-om: "+oib+
                    " ne postoje podaci niti u jednom registru";
        }

//NASTAVAK:             TO DO
        //Ako u registru nema OIB-a pregledaj listu prioriteta
        //Ako postoji prioritet - pogledaj ima li tamo
        //Ako ne postoji pogledaj u bilo kojem registru


        podaci.setJson(podaci.toString());
        podaci.setDohvacanjePodataka(dohvacanjePodataka);
        return podaci;

    }


}
