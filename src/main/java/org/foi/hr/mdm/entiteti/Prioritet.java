package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="prioritet", schema = "heroku_a4428e731088dc6")
public class Prioritet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prioritet_id")
    private int id;
    @Column(name="razina_prioriteta")
    private String razinaPrioriteta;

    public Prioritet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazinaPrioriteta() {
        return razinaPrioriteta;
    }

    public void setRazinaPrioriteta(String razinaPrioriteta) {
        this.razinaPrioriteta = razinaPrioriteta;
    }


}
