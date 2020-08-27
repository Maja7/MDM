package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="podatak_u_registru", schema = "heroku_a4428e731088dc6")
public class PodatakURegistru {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "podatak_id")
    private int podatakId;
    @Column(name = "registar_id")
    private int registarId;

    public PodatakURegistru() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPodatakId() {
        return podatakId;
    }

    public void setPodatakId(int podatakId) {
        this.podatakId = podatakId;
    }

    public int getRegistarId() {
        return registarId;
    }

    public void setRegistarId(int registarId) {
        this.registarId = registarId;
    }
}
