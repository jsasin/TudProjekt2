package service;

import domain.Producent;
import domain.Produkt;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jacek on 03.01.2017.
 */
@Component
@Transactional
public class HurtowniaManagerImpl implements HurtowniaManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addProducent(Producent producent) {
        producent.setId(null);
        sessionFactory.getCurrentSession().persist(producent);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Producent> getAllProducents() {
        return sessionFactory.getCurrentSession().getNamedQuery("producent.all")
                .list();
    }

    @Override
    public void deleteProducent(Producent producent) {
        sessionFactory.getCurrentSession().delete(producent);

    }

    @Override
    @SuppressWarnings("unchecked")
    public Producent findProducentbyNazwa(String nazwa) {
        return (Producent) sessionFactory.getCurrentSession().getNamedQuery("producent.byNazwa").setString("nazwa", nazwa).uniqueResult();
    }

    @Override
    public Long addNewProdukt(Produkt produkt) {
        produkt.setId(null);
        return (Long) sessionFactory.getCurrentSession().save(produkt);
    }



    @Override
    @SuppressWarnings("unchecked")
    public List<Produkt> getAllProdukts() {
        return sessionFactory.getCurrentSession().getNamedQuery("produkt.all")
                .list();
    }


    @Override
    public void addProduktToProducent(Producent producent, Produkt produkt) {
        producent.getProdukty().add(produkt);
        sessionFactory.getCurrentSession().persist(producent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Produkt findProduktById(Long id) {
        return (Produkt) sessionFactory.getCurrentSession().get(Produkt.class, id);
    }

    @Override
    public List<Produkt> getProduktyofProducent(Producent producent) {
        return  producent.getProdukty();
    }
}
