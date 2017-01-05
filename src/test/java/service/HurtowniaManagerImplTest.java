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
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
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

        int size = hurtowniaManager.getAllProducents().size();

        Producent producent = new Producent();

        producent.setNazwa(NAZWA_PRODUCENT_2);
        producent.setKraj(KRAJ_PRODUCENT_2);

        hurtowniaManager.addProducent(producent);

        assertEquals(size+1,hurtowniaManager.getAllProducents().size());

    }

    @Test
    public void deleteProducent() throws Exception {

        int size=hurtowniaManager.getAllProducents().size();

        Producent producent = new Producent();

        producent.setNazwa("Elkoks");
        producent.setKraj("Czechy");

        hurtowniaManager.addProducent(producent);

        assertEquals(size+1,hurtowniaManager.getAllProducents().size());

        hurtowniaManager.deleteProducent(producent);

        assertEquals(size,hurtowniaManager.getAllProducents().size());
        assertNull(hurtowniaManager.findProducentbyNazwa("Elkoks"));

    }

    @Test
    public void findProducentbyNazwa() throws Exception {

        Producent producent = new Producent();

        producent.setNazwa("Kokodżambo");
        producent.setKraj("Estonia");


        hurtowniaManager.addProducent(producent);

        Producent retrieved = hurtowniaManager.findProducentbyNazwa("Kokodżambo");

        assertEquals("Kokodżambo", retrieved.getNazwa());
        assertEquals("Estonia", retrieved.getKraj());
    }



    @Test
    public void addNewProdukt() throws Exception {

        int size = hurtowniaManager.getAllProdukts().size();

        Produkt produkt = new Produkt();
        produkt.setNazwa(NAZWA_PRODUKT_1);
        produkt.setCena(CENA_PRODUKT_1);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_1);

        hurtowniaManager.addNewProdukt(produkt);

        assertEquals(size+1,hurtowniaManager.getAllProdukts().size());

        Produkt retrievedProdukt = hurtowniaManager.getAllProdukts().get(size);

        assertEquals(NAZWA_PRODUKT_1, retrievedProdukt.getNazwa());
        assertEquals(CENA_PRODUKT_1, retrievedProdukt.getCena(),DELTA);
        assertEquals(OBJETOSC_PRODUKT_1, retrievedProdukt.getObjetosc_mg());

    }

    @Test
    public void getAllProdukts() throws Exception {

        List<Produkt> retrievedProdukts = hurtowniaManager.getAllProdukts();

        int size = retrievedProdukts.size();

        Produkt produkt = new Produkt();

        produkt.setNazwa(NAZWA_PRODUKT_2);
        produkt.setCena(CENA_PRODUKT_2);
        produkt.setObjetosc_mg(OBJETOSC_PRODUKT_2);


        hurtowniaManager.addNewProdukt(produkt);

        retrievedProdukts = hurtowniaManager.getAllProdukts();

        assertEquals(retrievedProdukts.size(),size+1);

        assertEquals(NAZWA_PRODUKT_2, retrievedProdukts.get(retrievedProdukts.size()-1).getNazwa());
        assertEquals(CENA_PRODUKT_2, retrievedProdukts.get(retrievedProdukts.size()-1).getCena(),DELTA);
        assertEquals(OBJETOSC_PRODUKT_2, retrievedProdukts.get(retrievedProdukts.size()-1).getObjetosc_mg());

    }


    @Test
    public void addProduktToProducent() throws Exception {

        Producent producent= new Producent();

        producent.setNazwa("Rumuńskie Laboratoria");
        producent.setKraj("Rumunia");

        int size = producent.getProdukty().size();

        Produkt produkt = new Produkt();

        produkt.setNazwa("Metaamfetamina");
        produkt.setCena(130.00);
        produkt.setObjetosc_mg(12);


        hurtowniaManager.addProduktToProducent(producent,produkt);

       assertEquals(producent.getProdukty().size(),size+1);
        assertEquals(producent.getProdukty().get(0).getNazwa(),"Metaamfetamina");
        assertEquals(producent.getProdukty().get(0).getCena(),130.00,DELTA);
        assertEquals(producent.getProdukty().get(0).getObjetosc_mg(),12);


    }

    @Test
    public void findProduktById() throws Exception {

        Produkt produkt = new Produkt();

        produkt.setNazwa("HGH");
        produkt.setCena(1500);
        produkt.setObjetosc_mg(50);

        hurtowniaManager.addNewProdukt(produkt);


        Produkt produkttest = hurtowniaManager.getAllProdukts().get(hurtowniaManager.getAllProdukts().size()-1);

        Produkt retrieved = hurtowniaManager.findProduktById(produkttest.getId());

        assertEquals(produkttest.getId(),retrieved.getId());
        assertEquals(produkttest.getNazwa(), retrieved.getNazwa());
        assertEquals(produkttest.getCena(),retrieved.getCena(),DELTA);
        assertEquals(produkt.getObjetosc_mg(), retrieved.getObjetosc_mg());



    }

    @Test
    public void getProduktyofProducent() throws Exception {

        Producent producent = new Producent();

        producent.setNazwa("Bambostan");
        producent.setKraj("RPA");


        assertEquals(producent.getProdukty().size(),0);

        Produkt produkt = new Produkt();

        produkt.setNazwa("Grzyby");
        produkt.setCena(150.00);
        produkt.setObjetosc_mg(200);

        hurtowniaManager.addProduktToProducent(producent,produkt);

        Produkt produkt2 = new Produkt();

        produkt2.setNazwa("Dziwne coś w kryształach");
        produkt2.setCena(2000.20);
        produkt2.setObjetosc_mg(11);

        hurtowniaManager.addProduktToProducent(producent,produkt2);

        producent= hurtowniaManager.findProducentbyNazwa("Bambostan");

        assertEquals(producent.getProdukty().size(),2);
        assertEquals(producent.getProdukty().get(0).getNazwa(),"Grzyby");
        assertEquals(producent.getProdukty().get(0).getCena(),150.00,DELTA);
        assertEquals(producent.getProdukty().get(0).getObjetosc_mg(),200);
        assertEquals(producent.getProdukty().get(1).getNazwa(),"Dziwne coś w kryształach");
        assertEquals(producent.getProdukty().get(1).getCena(),2000.20,DELTA);
        assertEquals(producent.getProdukty().get(1).getObjetosc_mg(),11);

    }

    @Test
    public void updateProducent() throws Exception {

        assertNull(hurtowniaManager.findProducentbyNazwa("Wietnamskie Specyfiki"));

        Producent producent = new Producent();
        producent.setNazwa("Boliwijskie Czary");
        producent.setKraj("Boliwia");

        hurtowniaManager.addProducent(producent);

        Producent retrieved = hurtowniaManager.findProducentbyNazwa("Boliwijskie Czary");

        retrieved.setNazwa("Wietnamskie Specyfiki");
        retrieved.setKraj(KRAJ_PRODUCENT_1);

        hurtowniaManager.updateProducent(retrieved);

        retrieved=hurtowniaManager.findProducentbyNazwa("Wietnamskie Specyfiki");

        assertEquals(retrieved.getKraj(),KRAJ_PRODUCENT_1);
        assertNotNull(hurtowniaManager.findProducentbyNazwa("Wietnamskie Specyfiki"));

    }

    @Test
    public void deleteProdukt() throws Exception {

        List<Produkt> retrievedprodukts = hurtowniaManager.getAllProdukts();

        int size = retrievedprodukts.size();

        Produkt produkt = new Produkt();

        produkt.setNazwa("Krokodylek");
        produkt.setCena(100.00);
        produkt.setObjetosc_mg(5);

        hurtowniaManager.addNewProdukt(produkt);


        retrievedprodukts = hurtowniaManager.getAllProdukts();


        assertEquals(retrievedprodukts.size(),size+1);

        hurtowniaManager.deleteProdukt(produkt);
        retrievedprodukts = hurtowniaManager.getAllProdukts();

        assertEquals(retrievedprodukts.size(),size);

        for (Produkt tested : retrievedprodukts) {
            assertFalse(tested.getNazwa().equals("Krokodylek"));
            assertFalse(tested.getCena()==100.00);
            assertFalse(tested.getObjetosc_mg()==5);
        }

    }

    @Test
    public void updateProdukt() throws Exception {

        Produkt produkt = new Produkt();

        produkt.setNazwa("LSD");
        produkt.setCena(324.99);
        produkt.setObjetosc_mg(39);

        hurtowniaManager.addNewProdukt(produkt);

        List<Produkt> retrievedprodukts = hurtowniaManager.getAllProdukts();

        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getNazwa(),"LSD");
        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getCena(),324.99,DELTA);
        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getObjetosc_mg(),39);



       Produkt modified = hurtowniaManager.findProduktById(retrievedprodukts.get(retrievedprodukts.size()-1).getId());

       modified.setNazwa("Sok z gumijagód");
       modified.setCena(111.22);
       modified.setObjetosc_mg(55);

       hurtowniaManager.updateProdukt(modified);
        retrievedprodukts = hurtowniaManager.getAllProdukts();

        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getNazwa(),"Sok z gumijagód");
        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getCena(),111.22,DELTA);
        assertEquals(retrievedprodukts.get(retrievedprodukts.size()-1).getObjetosc_mg(),55);

    }


}