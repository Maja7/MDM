package org.foi.hr.mdm;

import org.foi.hr.mdm.entiteti.*;
import org.foi.hr.mdm.modeli.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("restService")
public class RESTService {

    private static String path = "http://localhost:8080/rest/";
    private static final String GET_KLJUCNE_RIJECI = path + "/dohvatiKljucneRijeci";
    private static final String GET_ALL_KORISNICI_RMR = path + "/usluge/matica-rodjenih";
    private static final String GET_ALL_KORISNICI_RMU = path + "/usluge/matica-umrlih";
    private static final String GET_ALL_KORISNICI_RMV = path + "/usluge/matica-vjencanih";
    private static final String GET_ALL_KORISNICI_RKD = path + "/usluge/knjiga-drzavljana";
    private static final String GET_KORISNIK_RMR = path + "/usluge/matica-rodjenih/dohvatiPodatkeOKorisniku?OIB=";
    private static final String GET_KORISNIK_RMU = path + "/usluge/matica-umrlih/dohvatiPodatkeOKorisniku?OIB=";
    private static final String GET_KORISNIK_RMV = path + "/usluge/matica-vjencanih/dohvatiPodatkeOKorisniku?OIB=";
    private static final String GET_KORISNIK_RKD = path + "/usluge/knjiga-drzavljana/dohvatiPodatkeOKorisniku?OIB=";
    private static final String GET_POSTOJI_KORISNIK_RMR = path + "/usluge/matica-rodjenih/postojiKorisnik?OIB=";
    private static final String GET_POSTOJI_KORISNIK_RMU = path + "/usluge/matica-umrlih/postojiKorisnik?OIB=";
    private static final String GET_POSTOJI_KORISNIK_RMV = path + "/usluge/matica-vjencanih/postojiKorisnik?OIB=";
    private static final String GET_POSTOJI_KORISNIK_RKD = path + "/usluge/knjiga-drzavljana/postojiKorisnik?OIB=";

    public KljucnaRijecPretragaModel getKljucneRijeci() {
        KljucnaRijecPretragaModel search = new KljucnaRijecPretragaModel();

        List<KljucnaRijec> listaKljucnihRijeci = new ArrayList<>();
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_KLJUCNE_RIJECI, HttpMethod.GET, entity, String.class);

        JSONArray json = new JSONArray(response.getBody());

        for (int i = 0; i < json.length(); i++) {
            JSONObject object = json.getJSONObject(i);
            KljucnaRijec kljucnaRijec = new KljucnaRijec();
            kljucnaRijec.setId(object.getInt("id"));
            kljucnaRijec.setKljucnaRijec(object.getString("kljucnaRijec"));

            listaKljucnihRijeci.add(kljucnaRijec);
        }

        search.setListaKljucnihRijeci(listaKljucnihRijeci);

        return search;
    }
    public KorisniciRMRPretragaModel getKorisniciMaticeRodjenih() {
        KorisniciRMRPretragaModel search = new KorisniciRMRPretragaModel();

        List<KorisnikRegistarMaticeRodjenih> listaKorisnikaRMR = new ArrayList<>();
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_ALL_KORISNICI_RMR, HttpMethod.GET, entity, String.class);

        JSONArray json = new JSONArray(response.getBody());

        for (int i = 0; i < json.length(); i++) {
            JSONObject object = json.getJSONObject(i);
            KorisnikRegistarMaticeRodjenih korisnik = new KorisnikRegistarMaticeRodjenih();
            korisnik.setOib(object.getLong("oib"));
            korisnik.setIme(object.getString("ime"));
            korisnik.setPrezime(object.getString("prezime"));
            korisnik.setDatumRodenja(Date.valueOf(object.getString("datumRodenja")));

            listaKorisnikaRMR.add(korisnik);
        }

        search.setListaKorisnikaRMR(listaKorisnikaRMR);

        return search;
    }
    public KorisniciRMUPretragaModel getKorisniciMaticeUmrlih() {
        KorisniciRMUPretragaModel search = new KorisniciRMUPretragaModel();

        List<KorisnikRegistarMaticeUmrlih> listaKorisnikaRMU = new ArrayList<>();
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_ALL_KORISNICI_RMU, HttpMethod.GET, entity, String.class);

        JSONArray json = new JSONArray(response.getBody());

        for (int i = 0; i < json.length(); i++) {
            JSONObject object = json.getJSONObject(i);
            KorisnikRegistarMaticeUmrlih korisnik = new KorisnikRegistarMaticeUmrlih();
            korisnik.setOib(object.getLong("oib"));
            korisnik.setIme(object.getString("ime"));
            korisnik.setPrezime(object.getString("prezime"));
            korisnik.setDatumRodenja(Date.valueOf(object.getString("datumRodenja")));
            korisnik.setDatumSmrti(Date.valueOf(object.getString("datumSmrti")));

            listaKorisnikaRMU.add(korisnik);
        }

        search.setListaKorisnikaRMU(listaKorisnikaRMU);

        return search;
    }

    public KorisniciRMVPretragaModel getKorisniciMaticeVjencanih() {
        KorisniciRMVPretragaModel search = new KorisniciRMVPretragaModel();

        List<KorisnikRegistarMaticeVjencanih> listaKorisnikaRMV = new ArrayList<>();
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_ALL_KORISNICI_RMV, HttpMethod.GET, entity, String.class);

        JSONArray json = new JSONArray(response.getBody());

        for (int i = 0; i < json.length(); i++) {
            JSONObject object = json.getJSONObject(i);
            KorisnikRegistarMaticeVjencanih korisnik = new KorisnikRegistarMaticeVjencanih();
            korisnik.setOib(object.getLong("oib"));
            korisnik.setIme(object.getString("ime"));
            korisnik.setPrezime(object.getString("prezime"));
            korisnik.setDjevojackoPrezime(object.getString("djevojackoPrezime"));
            korisnik.setDatumRodenja(Date.valueOf(object.getString("datumRodenja")));
            korisnik.setDatumVjencanja(Date.valueOf(object.getString("datumVjencanja")));
            korisnik.setOIBSupruznika(object.getLong("OIBSupruznika"));

            listaKorisnikaRMV.add(korisnik);
        }

        search.setListaKorisnikaRMV(listaKorisnikaRMV);

        return search;
    }
    public KorisniciRKDPretragaModel getKorisniciKnjigeDrzavljana() {
        KorisniciRKDPretragaModel search = new KorisniciRKDPretragaModel();

        List<KorisnikRegistarKnjigaDrzavljana> listaKorisnikaRKD = new ArrayList<>();
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_ALL_KORISNICI_RKD, HttpMethod.GET, entity, String.class);

        JSONArray json = new JSONArray(response.getBody());

        for (int i = 0; i < json.length(); i++) {
            JSONObject object = json.getJSONObject(i);
            KorisnikRegistarKnjigaDrzavljana korisnik = new KorisnikRegistarKnjigaDrzavljana();
            korisnik.setOib(object.getLong("oib"));
            korisnik.setIme(object.getString("ime"));
            korisnik.setPrezime(object.getString("prezime"));
            korisnik.setDatumRodenja(Date.valueOf(object.getString("datumRodenja")));
            korisnik.setDrzavljanstvo(object.getString("drzavljanstvo"));

            listaKorisnikaRKD.add(korisnik);
        }

        search.setListaKorisnikaRKD(listaKorisnikaRKD);

        return search;
    }

    public KorisnikRegistarMaticeRodjenih getKorisnikRMR(Long oib) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_KORISNIK_RMR + oib, HttpMethod.GET, entity, String.class);

        JSONObject object = new JSONObject(response.getBody());
        KorisnikRegistarMaticeRodjenih korisnik = new KorisnikRegistarMaticeRodjenih();
        korisnik.setOib(object.getLong("oib"));
        korisnik.setIme(object.getString("ime"));
        korisnik.setPrezime(object.getString("prezime"));
        korisnik.setDatumRodenja(Date.valueOf(object.getString("datumRodenja")));

        return korisnik;
    }

    public KorisnikRegistarMaticeUmrlih getKorisnikRMU(Long oib) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_KORISNIK_RMU + oib, HttpMethod.GET, entity, String.class);

        JSONObject object = new JSONObject(response.getBody());
        KorisnikRegistarMaticeUmrlih korisnik = new KorisnikRegistarMaticeUmrlih();
        korisnik.setOib(object.getLong("oib"));
        korisnik.setIme(object.getString("ime"));
        korisnik.setPrezime(object.getString("prezime"));
        korisnik.setDatumRodenja(Date.valueOf(object.getString("datumRodenja")));
        korisnik.setDatumSmrti(Date.valueOf(object.getString("datumSmrti")));

        return korisnik;
    }

    public KorisnikRegistarMaticeVjencanih getKorisnikRMV(Long oib) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_KORISNIK_RMV + oib, HttpMethod.GET, entity, String.class);

        JSONObject object = new JSONObject(response.getBody());
        KorisnikRegistarMaticeVjencanih korisnik = new KorisnikRegistarMaticeVjencanih();
        korisnik.setOib(object.getLong("oib"));
        korisnik.setIme(object.getString("ime"));
        korisnik.setPrezime(object.getString("prezime"));
        korisnik.setDjevojackoPrezime(object.getString("djevojackoPrezime"));
        korisnik.setDatumRodenja(Date.valueOf(object.getString("datumRodenja")));
        korisnik.setDatumVjencanja(Date.valueOf(object.getString("datumVjencanja")));
        korisnik.setOIBSupruznika(object.getLong("oibsupruznika"));

        return korisnik;
    }

    public KorisnikRegistarKnjigaDrzavljana getKorisnikRKD(Long oib) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_KORISNIK_RKD + oib, HttpMethod.GET, entity, String.class);

        JSONObject object = new JSONObject(response.getBody());
        KorisnikRegistarKnjigaDrzavljana korisnik = new KorisnikRegistarKnjigaDrzavljana();
        korisnik.setOib(object.getLong("oib"));
        korisnik.setIme(object.getString("ime"));
        korisnik.setPrezime(object.getString("prezime"));
        korisnik.setDatumRodenja(Date.valueOf(object.getString("datumRodenja")));
        korisnik.setDrzavljanstvo(object.getString("drzavljanstvo"));

        return korisnik;
    }

    public boolean postojiKorisnikRMR(Long oib){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_POSTOJI_KORISNIK_RMR + oib, HttpMethod.GET, entity, String.class);

        if (response.getBody().equals("false")) {
            return false;
        }
        return true;
    }
    public boolean postojiKorisnikRMU(Long oib){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_POSTOJI_KORISNIK_RMU + oib, HttpMethod.GET, entity, String.class);

        if (response.getBody().equals("false")) {
            return false;
        }
        return true;
    }
    public boolean postojiKorisnikRMV(Long oib){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_POSTOJI_KORISNIK_RMV + oib, HttpMethod.GET, entity, String.class);

        if (response.getBody().equals("false")) {
            return false;
        }
        return true;
    }
    public boolean postojiKorisnikRKD(Long oib){
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = rest.exchange(GET_POSTOJI_KORISNIK_RKD + oib, HttpMethod.GET, entity, String.class);

        if (response.getBody().equals("false")) {
            return false;
        }
        return true;
    }
}
