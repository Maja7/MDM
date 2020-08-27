package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="podatak", schema = "heroku_a4428e731088dc6")
public class Podatak {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "podatak_id")
    private int id;
    @Column(name = "vrsta_podatka")
    private String vrstaPodatka;

    public Podatak() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVrstaPodatka() {
        return vrstaPodatka;
    }

    public void setVrstaPodatka(String vrstaPodatka) {
        this.vrstaPodatka = vrstaPodatka;
    }
}
