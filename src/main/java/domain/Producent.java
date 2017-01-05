package domain;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacek on 01.01.2017.
 */

@Entity
@NamedQueries({
       @NamedQuery(name = "producent.all", query = "Select p from Producent p"),
       @NamedQuery(name = "producent.byNazwa", query = "Select p from Producent p where p.nazwa = :nazwa")
})

public class Producent {
    private Long id;
    private String nazwa;
    private String kraj;

    private List<Produkt> produkty = new ArrayList<Produkt>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Produkt> getProdukty() {
        return produkty;
    }

    public void setProdukty(List<Produkt> produkty) {
        this.produkty = produkty;
    }


}
