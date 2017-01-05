package service;

import domain.Producent;
import domain.Produkt;

import java.util.List;

/**
 * Created by Jacek on 02.01.2017.
 */
public interface HurtowniaManager {

    void addProducent(Producent producent);
    List<Producent> getAllProducents();
    void deleteProducent(Producent producent);
    Producent findProducentbyNazwa(String nazwa);

    Long addNewProdukt(Produkt produkt);
    List<Produkt> getAllProdukts();
    void addProduktToProducent(Producent producent, Produkt produkt);
    Produkt findProduktById(Long id);

    List<Produkt> getProduktyofProducent(Producent producent);

}
