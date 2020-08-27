package org.foi.hr.mdm.entiteti;

import javax.persistence.*;

@Entity
@Table(name="server", schema = "heroku_a4428e731088dc6")
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "server_id")
    private int id;
    @Column(name = "url_servera")
    private String urlServera;

    public Server() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlServera() {
        return urlServera;
    }

    public void setUrlServera(String urlServera) {
        this.urlServera = urlServera;
    }
}
