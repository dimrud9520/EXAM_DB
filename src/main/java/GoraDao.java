import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class GoraDao implements Dao<Gora, Integer> {

    private EntityManager entityManager;

    public GoraDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Gora gora) {
        entityManager.persist(gora);
    }

    @Override
    public void update(Gora gora) {
        entityManager.merge(gora);

    }

    @Override
    public Gora getByPK(Integer integer) {
        return entityManager.find(Gora.class, integer);
    }

    @Override
    public void delete(Gora gora) {
        entityManager.remove(gora);

    }

    @Override
    public void deleteByPK(Integer integer) {
        Gora gora = getByPK(integer);
        if(gora != null)
            entityManager.remove(gora);

    }

    public List<Gora> getGoraByCountry(String country){
        TypedQuery<Gora> query = entityManager.createNamedQuery("Gora.getGoraByCountry",
                Gora.class);
        query.setParameter("country", country);
        List<Gora> goras = query.getResultList();
        return goras;
    }

}