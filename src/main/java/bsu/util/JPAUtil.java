package bsu.util;
import jakarta.persistence.*;
public class JPAUtil {
    private static final EntityManagerFactory emf;

    static {
        EntityManagerFactory temp = null;
        try {
            temp = Persistence.createEntityManagerFactory("my-persistence-unit");
            System.out.println(" EntityManagerFactory инициализирован.");
        } catch (Exception e) {
            System.err.println("Ошибка инициализации JPA: " + e.getMessage());
            e.printStackTrace();
        }
        emf = temp;
    }

    public static EntityManager getEntityManager() {
        if (emf == null) throw new IllegalStateException("EntityManagerFactory не инициализирован.");
        return emf.createEntityManager();
    }

    public static void shutdown() {
        if (emf != null && emf.isOpen()) emf.close();
    }
}