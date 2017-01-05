package service;

import domain.Producent;
import domain.Produkt;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Jacek on 03.01.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class HurtowniaManagerImplTest {


    @Autowired
    private HurtowniaManager hurtowniaManager;


    private final String NAZWA_PRODUCENT_1 = "Bialy Nosek";
    private final String KRAJ_PRODUCENT_1 = "Polska";

    private final String NAZWA_PRODUCENT_2 = "El Chapo";
    private final String KRAJ_PRODUCENT_2 = "Meksyk";

    private final String NAZWA_PRODUKT_1 = "Amfetamina";
    private final double CENA_PRODUKT_1 = 200.10;
    private final int OBJETOSC_PRODUKT_1 = 10;

    private final String NAZWA_PRODUKT_2 = "Kokaina";
    private final double CENA_PRODUKT_2 = 350.20;
    private final int OBJETOSC_PRODUKT_2 = 15;

    private static final double DELTA = 1e-15;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void addProducent() throws Exception {
        List<Producent> retrievedProducents = hurtowniaManager.getAllProducents();

        for (Producent producent : retrievedProducents) {
            if (producent.getNazwa().equals(NAZWA_PRODUCENT_1)) {
                hurtowniaManager.deleteProducent(producent);
            }
        }

        Producent producent = new Producent();
        producent.setNazwa(NAZWA_PRODUCENT_1);
        producent.setKraj(KRAJ_PRODUCENT_1);


        hurtowniaManager.addProducent(producent);

        Producent retrievedProducent = hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1);

        assertEquals(NAZWA_PRODUCENT_1, retrievedProducent.getNazwa());
        assertEquals(KRAJ_PRODUCENT_1, retrievedProducent.getKraj());
    }

    @Test
    public void getAllProducents() throws Exception {

        Producent producent = new Producent();

        producent.setNazwa(NAZWA_PRODUCENT_1);
        producent.setKraj(KRAJ_PRODUCENT_1);

        hurtowniaManager.addProducent(producent);

        Producent producent2 = new Producent();

        producent2.setNazwa(NAZWA_PRODUCENT_2);
        producent2.setKraj(KRAJ_PRODUCENT_2);


        hurtowniaManager.addProducent(producent2);

        List<Producent> retrievedProducents = hurtowniaManager.getAllProducents();

        assertEquals(retrievedProducents.size(),2);

        assertEquals(NAZWA_PRODUCENT_1, retrievedProducents.get(retrievedProducents.size()-2).getNazwa());
        assertEquals(KRAJ_PRODUCENT_1, retrievedProducents.get(retrievedProducents.size()-2).getKraj());
        assertEquals(NAZWA_PRODUCENT_2, retrievedProducents.get(retrievedProducents.size()-1).getNazwa());
        assertEquals(KRAJ_PRODUCENT_2, retrievedProducents.get(retrievedProducents.size()-1).getKraj());


    }

    @Test
    public void deleteProducent() throws Exception {
        Producent producent = new Producent();

        producent.setNazwa(NAZWA_PRODUCENT_1);
        producent.setKraj(KRAJ_PRODUCENT_1);

        hurtowniaManager.addProducent(producent);

        Producent producent2 = new Producent();

        producent2.setNazwa(NAZWA_PRODUCENT_2);
        producent2.setKraj(KRAJ_PRODUCENT_2);


        hurtowniaManager.addProducent(producent2);

        hurtowniaManager.deleteProducent(producent);

        List<Producent> retrievedProducents = hurtowniaManager.getAllProducents();

        assertEquals(retrievedProducents.size(),1);
        assertEquals(NAZWA_PRODUCENT_2, retrievedProducents.get(retrievedProducents.size()-1).getNazwa());
        assertEquals(KRAJ_PRODUCENT_2, retrievedProducents.get(retrievedProducents.size()-1).getKraj());

    }

    @Test
    public void findProducentbyNazwa() throws Exception {

        Producent producent = new Producent();

        producent.setNazwa(NAZWA_PRODUCENT_1);
        producent.setKraj(KRAJ_PRODUCENT_1);

        hurtowniaManager.addProducent(producent);

        Producent producent2 = new Producent();

        producent2.setNazwa(NAZWA_PRODUCENT_2);
        producent2.setKraj(KRAJ_PRODUCENT_2);


        hurtowniaManager.addProducent(producent2);

        Producent retrieved = hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1);

        assertEquals(NAZWA_PRODUCENT_1, retrieved.getNazwa());
        assertEquals(KRAJ_PRODUCENT_1, retrieved.getKraj());
    }


    //można dodać usuwanie danego produktu przed dodaniem

    @Test
    public void addNewProdukt() throws Exception {

        Produkt produkt = new Produkt();
        produkt.setNazwa(NAZWA_PRODUKT_1);
        produkt.setCena(CENA_PRODUKT_1);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_1);



        Producent producent = new Producent();
        producent.setNazwa(NAZWA_PRODUCENT_1);
        producent.setKraj(KRAJ_PRODUCENT_1);


        hurtowniaManager.addProducent(producent);

        Producent retrievedProducent = hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1);

        assertEquals(NAZWA_PRODUCENT_1, retrievedProducent.getNazwa());
        assertEquals(KRAJ_PRODUCENT_1, retrievedProducent.getKraj());

    }

    @Test
    public void getAllProdukts() throws Exception {

        List<Produkt> retrievedProdukts = hurtowniaManager.getAllProdukts();

        int size = retrievedProdukts.size();

        Produkt produkt = new Produkt();

        produkt.setNazwa(NAZWA_PRODUKT_1);
        produkt.setCena(CENA_PRODUKT_1);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_1);

        hurtowniaManager.addNewProdukt(produkt);

        Produkt produkt2 = new Produkt();

        produkt2.setNazwa(NAZWA_PRODUKT_2);
        produkt2.setCena(CENA_PRODUKT_2);
        produkt2.setObjetosc_mg(OBJETOSC_PRODUKT_2);


        hurtowniaManager.addNewProdukt(produkt2);

        retrievedProdukts = hurtowniaManager.getAllProdukts();

        assertEquals(retrievedProdukts.size(),size+2);

        assertEquals(NAZWA_PRODUKT_1, retrievedProdukts.get(retrievedProdukts.size()-2).getNazwa());
        assertEquals(CENA_PRODUKT_1, retrievedProdukts.get(retrievedProdukts.size()-2).getCena(),DELTA);
        assertEquals(OBJETOSC_PRODUKT_1, retrievedProdukts.get(retrievedProdukts.size()-2).getObjetosc_mg());

        assertEquals(NAZWA_PRODUKT_2, retrievedProdukts.get(retrievedProdukts.size()-1).getNazwa());
        assertEquals(CENA_PRODUKT_2, retrievedProdukts.get(retrievedProdukts.size()-1).getCena(),DELTA);
        assertEquals(OBJETOSC_PRODUKT_2, retrievedProdukts.get(retrievedProdukts.size()-1).getObjetosc_mg());

    }


    @Test
    public void addProduktToProducent() throws Exception {

        Producent producent = new Producent();

        producent.setNazwa(NAZWA_PRODUCENT_1);
        producent.setKraj(KRAJ_PRODUCENT_1);

        hurtowniaManager.addProducent(producent);

        Producent retrievedproducent= hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1);

        assertEquals(retrievedproducent.getProdukty().size(),0);

        Produkt produkt = new Produkt();

        produkt.setNazwa(NAZWA_PRODUKT_1);
        produkt.setCena(CENA_PRODUKT_1);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_1);

        hurtowniaManager.addProduktToProducent(producent,produkt);

        retrievedproducent= hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1);

        assertEquals(retrievedproducent.getProdukty().size(),1);
        assertEquals(retrievedproducent.getProdukty().get(0).getNazwa(),NAZWA_PRODUKT_1);
        assertEquals(retrievedproducent.getProdukty().get(0).getCena(),CENA_PRODUKT_1,DELTA);
        assertEquals(retrievedproducent.getProdukty().get(0).getObjetosc_mg(),OBJETOSC_PRODUKT_1);


    }

    @Test
    public void findProduktById() throws Exception {

        Produkt produkt = new Produkt();

        produkt.setNazwa(NAZWA_PRODUKT_1);
        produkt.setCena(CENA_PRODUKT_1);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_1);

        hurtowniaManager.addNewProdukt(produkt);

        Produkt produkt2 = new Produkt();

        produkt2.setNazwa(NAZWA_PRODUKT_2);
        produkt2.setCena(CENA_PRODUKT_2);
        produkt2.setObjetosc_mg(OBJETOSC_PRODUKT_2);


        hurtowniaManager.addNewProdukt(produkt2);

        Produkt produkttest = hurtowniaManager.getAllProdukts().get(hurtowniaManager.getAllProdukts().size()-2);

        Produkt retrieved = hurtowniaManager.findProduktById(produkttest.getId());

        assertEquals(produkttest.getId(),retrieved.getId());
        assertEquals(produkttest.getNazwa(), retrieved.getNazwa());
        assertEquals(produkttest.getCena(),retrieved.getCena(),DELTA);
        assertEquals(produkt.getObjetosc_mg(), retrieved.getObjetosc_mg());



    }

    @Test
    public void getProduktyofProducent() throws Exception {

        Producent producent = new Producent();

        producent.setNazwa(NAZWA_PRODUCENT_1);
        producent.setKraj(KRAJ_PRODUCENT_1);

        hurtowniaManager.addProducent(producent);

        Producent retrievedproducent= hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1);

        assertEquals(retrievedproducent.getProdukty().size(),0);

        Produkt produkt = new Produkt();

        produkt.setNazwa(NAZWA_PRODUKT_1);
        produkt.setCena(CENA_PRODUKT_1);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_1);

        hurtowniaManager.addProduktToProducent(producent,produkt);

        Produkt produkt2 = new Produkt();

        produkt2.setNazwa(NAZWA_PRODUKT_2);
        produkt2.setCena(CENA_PRODUKT_2);
        produkt2.setObjetosc_mg(OBJETOSC_PRODUKT_2);

        hurtowniaManager.addProduktToProducent(producent,produkt2);

        retrievedproducent= hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1);

        assertEquals(retrievedproducent.getProdukty().size(),2);
        assertEquals(retrievedproducent.getProdukty().get(0).getNazwa(),NAZWA_PRODUKT_1);
        assertEquals(retrievedproducent.getProdukty().get(0).getCena(),CENA_PRODUKT_1,DELTA);
        assertEquals(retrievedproducent.getProdukty().get(0).getObjetosc_mg(),OBJETOSC_PRODUKT_1);
        assertEquals(retrievedproducent.getProdukty().get(1).getNazwa(),NAZWA_PRODUKT_2);
        assertEquals(retrievedproducent.getProdukty().get(1).getCena(),CENA_PRODUKT_2,DELTA);
        assertEquals(retrievedproducent.getProdukty().get(1).getObjetosc_mg(),OBJETOSC_PRODUKT_2);

    }

    @Test
    public void updateProducent() throws Exception {

        assertNull(hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1));
        assertNull(hurtowniaManager.findProducentbyNazwa("Wietnamskie Specyfiki"));

        Producent producent = new Producent();

        producent.setNazwa(NAZWA_PRODUCENT_1);
        producent.setKraj(KRAJ_PRODUCENT_1);

        hurtowniaManager.addProducent(producent);

        Producent retrieved = hurtowniaManager.findProducentbyNazwa(NAZWA_PRODUCENT_1);

        retrieved.setNazwa("Wietnamskie Specyfiki");

        hurtowniaManager.updateProducent(retrieved);

        retrieved=hurtowniaManager.findProducentbyNazwa("Wietnamskie Specyfiki");

        assertEquals(retrieved.getKraj(),KRAJ_PRODUCENT_1);

    }

    @Test
    public void deleteProdukt() throws Exception {

        List<Produkt> retrievedprodukts = hurtowniaManager.getAllProdukts();

        int size = retrievedprodukts.size();

        Produkt produkt = new Produkt();

        produkt.setNazwa(NAZWA_PRODUKT_1);
        produkt.setCena(CENA_PRODUKT_1);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_1);

        hurtowniaManager.addNewProdukt(produkt);

        Produkt produkt2 = new Produkt();

        produkt2.setNazwa(NAZWA_PRODUKT_2);
        produkt2.setCena(CENA_PRODUKT_2);
        produkt2.setObjetosc_mg(OBJETOSC_PRODUKT_2);


        hurtowniaManager.addNewProdukt(produkt2);

        retrievedprodukts = hurtowniaManager.getAllProdukts();


        assertEquals(retrievedprodukts.size(),size+2);

        hurtowniaManager.deleteProdukt(produkt);
        retrievedprodukts = hurtowniaManager.getAllProdukts();

        assertEquals(retrievedprodukts.size(),size+1);

        assertEquals(retrievedprodukts.get(size).getNazwa(),NAZWA_PRODUKT_2);
        assertEquals(retrievedprodukts.get(size).getCena(),CENA_PRODUKT_2,DELTA);
        assertEquals(retrievedprodukts.get(size).getObjetosc_mg(),OBJETOSC_PRODUKT_2);

    }

    @Test
    public void updateProdukt() throws Exception {

        Produkt produkt = new Produkt();

        produkt.setNazwa(NAZWA_PRODUKT_1);
        produkt.setCena(CENA_PRODUKT_1);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_1);

        hurtowniaManager.addNewProdukt(produkt);

        Produkt produkt2 = new Produkt();

        produkt2.setNazwa(NAZWA_PRODUKT_2);
        produkt2.setCena(CENA_PRODUKT_2);
        produkt2.setObjetosc_mg(OBJETOSC_PRODUKT_2);


        hurtowniaManager.addNewProdukt(produkt2);

        List<Produkt> retrievedprodukts = hurtowniaManager.getAllProdukts();

       Produkt modified = hurtowniaManager.findProduktById(retrievedprodukts.get(retrievedprodukts.size()-1).getId());

       modified.setNazwa("Sok z gumijagód");

       hurtowniaManager.updateProdukt(modified);
        retrievedprodukts = hurtowniaManager.getAllProdukts();

        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getNazwa(),"Sok z gumijagód");
        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getCena(),CENA_PRODUKT_2,DELTA);
        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getObjetosc_mg(),OBJETOSC_PRODUKT_2);

    }



}