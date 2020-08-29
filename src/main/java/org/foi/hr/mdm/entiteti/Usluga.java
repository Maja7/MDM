package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="usluga", schema = "heroku_a4428e731088dc6")
public class Usluga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usluga_id")
    private int id;
    @Column(name="naziv_usluge")
    private String nazivUsluge;
    @Column(name="institucija_id")
    private int institucijaId;
    @Column(name="nacin_isporuke_id")
    private int nacinIsporukeId;

    public Usluga() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public int getInstitucijaId() {
        return institucijaId;
    }

    public void setInstitucijaId(int institucijaId) {
        this.institucijaId = institucijaId;
    }

    public int getNacinIsporukeId() {
        return nacinIsporukeId;
    }

    public void setNacinIsporukeId(int nacinIsporukeId) {
        this.nacinIsporukeId = nacinIsporukeId;
    }
}
