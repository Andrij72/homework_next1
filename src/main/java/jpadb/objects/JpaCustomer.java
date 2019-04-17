package jpadb.objects;

import jpadb.entities.Customer;
import lombok.extern.log4j.Log4j;
import javax.persistence.EntityManager;

@Log4j
public class JpaCustomer {
    private EntityManager entityManager;

    public JpaCustomer (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public boolean insertCustomer (Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.warn(e.getMessage());
            return false;
        }
    }

    public boolean updateCustomer (Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.warn(e.getMessage());
            return false;
        }
    }

    public Customer readCustomer (Long id) {
        try {
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
            return customer;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.warn(e.getMessage());
            return null;
        }
    }

    public boolean deleteCustomer (Long id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Customer.class, id));
            entityManager.getTransaction().commit();
            return true;
        } catch (RuntimeException e) {
            if (entityManager != null) {
                entityManager.getTransaction().rollback();
            }
            log.warn(e.getMessage());
            return false;
        }
    }
}