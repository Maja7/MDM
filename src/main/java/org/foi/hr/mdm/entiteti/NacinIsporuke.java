package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="nacin_isporuke", schema = "heroku_a4428e731088dc6")
public class NacinIsporuke {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nacin_isporuke_id")
    private int id;
    @Column(name="nacin_isporuke")
    private String nacinIsporuke;

    public NacinIsporuke() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNacinIsporuke() {
        return nacinIsporuke;
    }

    public void setNacinIsporuke(String nacinIsporuke) {
        this.nacinIsporuke = nacinIsporuke;
    }
}
