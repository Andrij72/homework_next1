package jpadb.service;

import lombok.experimental.UtilityClass;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@UtilityClass
public class DaoService {
        private final String PERSISTENCE_UNIT_NAME = "PERSISTENCE_MANAGER";
        private EntityManagerFactory factory;

        public EntityManagerFactory getEntityManagerFactory() {
            if (factory == null) {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);            }
            return factory;
        }

        public void shutdown() {
            if (factory != null) {
                factory.close();
            }
        }
    }
