/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ealp.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author Tiberius
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class JTAProvider {

    private EntityManager em;
    private UserTransaction tx;

    public JTAProvider(EntityManager em, UserTransaction tx) {
        this.tx = tx;
        this.em = em;
    }

    public UserTransaction getTx() {
        return tx;
    }

    public EntityManager getEm() {
        return em;
    }

    public Query createQuery(String namedQuery) {
        return em.createQuery(namedQuery);
    }

    public Query createNamedQuery(String namedQuery) {
        return em.createNamedQuery(namedQuery);
    }

    public Query createNativeQuery(String namedQuery) {
        return em.createNativeQuery(namedQuery);
    }

    public Query createNativeQuery(String namedQuery, Class cls) {
        return em.createNativeQuery(namedQuery, cls);
    }

    public <T extends Object> T getOne(Class<T> entityClass, Object id) {
        T res = null;
        try {

            res = (T) em.find(entityClass, id);
            em.close();

        } catch (Exception e) {
           
        }
        return res;

    }

    public <T extends Object> T getOneFromQuery(Query qry) {
        T res = null;
        try {

            res = (T) qry.getSingleResult();

        } catch (Exception e) {
           
        }
        return res;

    }

    public <T extends Object> T getManyFromQuery(Query qry) {
        T res = null;
        try {

            
            res = (T) qry.getResultList();

        } catch (Exception e) {
            
        }
        return res;

    }

    public boolean create(Object entity) {
        boolean res = false;
        try {
            tx.begin();
            em.persist(entity);
            em.flush();
            tx.commit();
            res = true;
        } catch (Exception e) {
            
        }
        return res;
    }

    public boolean createList(Object ent) {
        List<Object> entities = (List<Object>) ent;
        boolean res = false;
        try {
            tx.begin();
            for (Object entity : entities) {
                em.persist(entity);
            }
            em.flush();
            tx.commit();
            res = true;
        } catch (Exception e) {
            
        }
        return res;
    }

    public boolean updateList(Object ent) {
        List<Object> entities = (List<Object>) ent;
        boolean res = false;
        try {
            tx.begin();
            for (Object entity : entities) {
                em.merge(entity);
            }
            em.flush();
            tx.commit();
            res = true;
        } catch (Exception e) {
           
        }
        return res;
    }

    public boolean update(Object entity) {
        boolean res = false;
        try {
            tx.begin();
            em.merge(entity);
            em.flush();
            tx.commit();
            res = true;
        } catch (Exception e) {
            
        }
        return res;
    }

    public Object merge(Object entity) {
        boolean res = false;
        try {
            tx.begin();
            entity = em.merge(entity);
            em.flush();
            tx.commit();
            res = true;
        } catch (Exception e) {
            
        }
        return res ? entity : null;
    }

    public boolean delete(Object entity) {
        boolean res = false;
        try {
            tx.begin();
            //merge then remove????
            em.remove(em.merge(entity));
            em.flush();
            tx.commit();
            res = true;
        } catch (Exception e) {
            
        }
        return res;
    }

    public boolean deleteList(Object ent) {
        List<Object> entities = (List<Object>) ent;
        boolean res = false;
        try {
            tx.begin();
            //merge then remove????
            for (Object entity : entities) {
                em.remove(em.merge(entity));
            }
            em.flush();
            tx.commit();
            res = true;
        } catch (Exception e) {
            
        }
        return res;
    }
}
