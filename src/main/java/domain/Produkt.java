package domain;

import javax.persistence.*;

/**
 * Created by Jacek on 01.01.2017.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "produkt.all", query = "Select p from Produkt p"),
        @NamedQuery(name = "produkt.byId", query = "Select p from Produkt p where p.id = :id0")
})
public class Produkt {
    private Long id;
    private String nazwa;
    private double cena;
    private int objetosc_mg;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }
    @Column(unique = true)
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getObjetosc_mg() {
        return objetosc_mg;
    }

    public void setObjetosc_mg(int objetosc_mg) {
        this.objetosc_mg = objetosc_mg;
    }

}
