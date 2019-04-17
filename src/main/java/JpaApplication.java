import jpadb.objects.JpaPersistent;
import jpadb.entities.Developer;
import javax.persistence.EntityManager;
import jpadb.objects.JpaDeveloper;

public class JpaApplication {
    public static void main(String[] args) {
        EntityManager entityManager =  JpaPersistent.getEntityManagerFactory().createEntityManager();
        Developer developer = new Developer();
        developer.setName("AndrewK");
        developer.setAge(30);
        developer.setGender("male");
        developer.setSalary(5000.00);
       JpaDeveloper developerJpa = new  JpaDeveloper(entityManager);
        developerJpa.insertDeveloper(developer);
        JpaPersistent.shutdown();
    }
}