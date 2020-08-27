package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="institucija", schema = "heroku_a4428e731088dc6")
public class Institucija {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "institucija_id")
    private int id;
    @Column(name="naziv_institucije")
    private String nazivInstitucije;

    public Institucija() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivInstitucije() {
        return nazivInstitucije;
    }

    public void setNazivInstitucije(String nazivInstitucije) {
        this.nazivInstitucije = nazivInstitucije;
    }

}
