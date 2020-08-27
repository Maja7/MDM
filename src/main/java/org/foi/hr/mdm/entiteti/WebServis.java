package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="web_servis", schema = "heroku_a4428e731088dc6")
public class WebServis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "web_servis_id")
    private int id;
    @Column(name = "putanja")
    private String putanja;
    @Column(name = "server_id")
    private int serverId;
    @Column(name = "registar_id")
    private int registarId;

    public WebServis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }

    public int getServerId() {
        return serverId;
    }

    public void setServerId(int serverId) {
        this.serverId = serverId;
    }

    public int getRegistarId() {
        return registarId;
    }

    public void setRegistarId(int registarId) {
        this.registarId = registarId;
    }
}
