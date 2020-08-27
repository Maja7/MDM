package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="registar", schema = "heroku_a4428e731088dc6")
public class Registar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "registar_id")
    private int id;
    @Column(name="naziv_registra")
    private String nazivRegistra;
    @Column(name="institucija_id")
    private int institucijaId;

    public Registar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivRegistra() {
        return nazivRegistra;
    }

    public void setNazivRegistra(String nazivRegistra) {
        this.nazivRegistra = nazivRegistra;
    }

    public int getInstitucijaId() {
        return institucijaId;
    }

    public void setInstitucijaId(int institucijaId) {
        this.institucijaId = institucijaId;
    }
}
