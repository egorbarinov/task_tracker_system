package com.egorbarinov.tasktrackersystem.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;

public abstract class RepositoryImpl<T, ID extends Serializable> implements Repository<T, ID> {

    private static SessionFactory factory = new Configuration()
            .configure("configs/hibernate.cfg.xml")
            .buildSessionFactory();;

    private Class<T> typeClass = null;

    public RepositoryImpl(Class<T> typeClass) {
        this.typeClass = typeClass;
//        this.factory = new Configuration()
//                .configure("configs/hibernate.cfg.xml")
//                .buildSessionFactory();
    }

    @Override
    public T findById(ID id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            final T object = session.get(typeClass, id);
            session.getTransaction().commit();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(T entity) {
        try (Session session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            session.merge(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = factory.getCurrentSession();) {
            session.getTransaction().begin();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(ID id) {
        try (Session session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            session.delete(session.get(typeClass, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<T> findAll() {
        List<T> entities = null;
        try (Session session = factory.getCurrentSession()) {
            session.getTransaction().begin();
            entities = session.createQuery("from " + typeClass.getSimpleName(), typeClass).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }


}
