import jpadb.service.DaoService;
import jpadb.entities.Developer;
import javax.persistence.EntityManager;
import jpadb.objects.JpaDeveloper;

public class JpaApplication {
    public static void main(String[] args) {
        EntityManager entityManager =  DaoService.getEntityManagerFactory().createEntityManager();
        Developer developer = new Developer();
        developer.setName("AndrewK");
        developer.setAge(30);
        developer.setSex("male");
        developer.setSalary(5000);
       JpaDeveloper developerJpa = new  JpaDeveloper(entityManager);
        developerJpa.insertDeveloper(developer);
        DaoService.shutdown();
    }
}
