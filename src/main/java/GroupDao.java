import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class GroupDao implements Dao<Group, Integer> {
    private EntityManager entityManager;

    public GroupDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Group group) {
        entityManager.persist(group);

    }

    @Override
    public void update(Group group) {
        entityManager.merge(group);

    }

    @Override
    public Group getByPK(Integer integer) {
        return entityManager.find(Group.class, integer);
    }

    @Override
    public void delete(Group group) {
        entityManager.remove(group);

    }

    @Override
    public void deleteByPK(Integer integer) {
        Group group = getByPK(integer);
        if(group != null){
            delete(group);
        }

    }

    public List<Group> getByGoraName(String goraName){
        TypedQuery<Group> query = entityManager.createNamedQuery("Group.getByGoraName",
                Group.class);
        query.setParameter("name",goraName);
        List<Group> groups = query.getResultList();
        return groups;
    }


    public List<Group> getByConditionIsOpen(Boolean condition){
        TypedQuery<Group> query = entityManager.createNamedQuery("Group.getByConditionIsOpen",
                Group.class);
        query.setParameter("condition",condition);
        List<Group> groups = query.getResultList();
        return groups;
    }
}