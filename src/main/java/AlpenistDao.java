import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class AlpenistDao implements Dao<Alpenist, Integer> {
    private EntityManager entityManager;

    public AlpenistDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Alpenist alpenist) {
        entityManager.persist(alpenist);

    }

    @Override
    public void update(Alpenist alpenist) {
        entityManager.merge(alpenist);

    }

    @Override
    public Alpenist getByPK(Integer integer) {
        return entityManager.find(Alpenist.class, integer);
    }

    @Override
    public void delete(Alpenist alpenist) {
        entityManager.remove(alpenist);

    }

    @Override
    public void deleteByPK(Integer integer) {
        Alpenist alpenist = getByPK(integer);
        if (alpenist != null) {
            delete(alpenist);
        }

    }

    public List<Alpenist> getByAge(int from, int to){
        TypedQuery<Alpenist> query = entityManager.createNamedQuery("Alpenist.getByAge", Alpenist.class);
        query.setParameter("from",from);
        query.setParameter("to",to);
        List<Alpenist> alpenists = query.getResultList();
        return alpenists;
    }
}