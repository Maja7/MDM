package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="kljucna_rijec", schema = "heroku_a4428e731088dc6")
public class KljucnaRijec {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="kljucna_rijec_id")
    private int id;
    @Column(name="rijec")
    private String kljucnaRijec;

    public KljucnaRijec() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKljucnaRijec() {
        return kljucnaRijec;
    }

    public void setKljucnaRijec(String kljucnaRijec) {
        this.kljucnaRijec = kljucnaRijec;
    }
}
