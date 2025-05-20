package bsu.dao;
import bsu.model.User;
import bsu.util.JPAUtil;
import jakarta.persistence.*;

public class UserDao {
    public void save(User u) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }
    public User findByUsername(String uname) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<User> q = em.createQuery(
                    "SELECT u FROM User u WHERE u.username=:u", User.class);
            q.setParameter("u", uname);
            return q.getSingleResult();
        } catch(NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }}