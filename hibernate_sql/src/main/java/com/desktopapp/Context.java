package com.desktopapp;
// essa classe interage com o bamco de dados
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Context {

    private EntityManagerFactory emf;
    private EntityManager em;

    public Context() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit"); // gera o gerenciador de tabelas
    }

    public void begin() {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em = null;
        }
    }

    public <T> List<T> find(Class<T> entytyClass, String query, Object... values ) {
        EntityManager em = emf.createEntityManager();
        List<T> users = null;
        try {
            var queryObj = em.createQuery(query, entytyClass);
            if(values != null)
            {
                for (Integer i = 0; i < values.length; i++) {
                    queryObj = queryObj.setParameter("arg" + i.toString(), values[i]);
                }
            }
            users = queryObj.getResultList();
        } finally {
            em.close();
        }
        return users;
    }

    public <T> T find(Class<T> entityClass, Object primaryKey) {
        EntityManager em = emf.createEntityManager();
        T user = null;
        try {
            user = em.find(entityClass, primaryKey); // passa primeiro a classe de que é, tipo User.class, e coloca o id
        } finally {
            em.close();
        }
        return user;
    }

    public void save(Object object) {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }
        try {
            em.persist(object); // salva e manda objeto em banco de dados
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            em = null;
        }
    }

    public void commit() {
        if (em == null) {
            System.out.println("connection is null.");
            return;
        }
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
            em = null;
        }
    }
}
