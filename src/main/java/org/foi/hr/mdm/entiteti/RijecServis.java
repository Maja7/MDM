package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="rijec_servis", schema = "heroku_a4428e731088dc6")
public class RijecServis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "prioritet_id")
    private int prioritetId;
    @Column(name = "kljucna_rijec_id")
    private int kljucnaRijecId;
    @Column(name = "web_servis_id")
    private int webServisId;

    public RijecServis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrioritetId() {
        return prioritetId;
    }

    public void setPrioritetId(int prioritetId) {
        this.prioritetId = prioritetId;
    }

    public int getKljucnaRijecId() {
        return kljucnaRijecId;
    }

    public void setKljucnaRijecId(int kljucnaRijecId) {
        this.kljucnaRijecId = kljucnaRijecId;
    }

    public int getWebServisId() {
        return webServisId;
    }

    public void setWebServisId(int webServisId) {
        this.webServisId = webServisId;
    }
}
