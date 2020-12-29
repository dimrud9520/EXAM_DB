import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class Tst {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("entityManager");
        EntityManager manager = factory.createEntityManager();
        AlpenistDao alpenistDao = new AlpenistDao(manager);
        GroupDao groupsDao = new GroupDao(manager);
        GoraDao goraDao = new GoraDao(manager);

        Alpenist[] alpenists = new Alpenist[]{
                new Alpenist("Oskar", "Moscow, Lenin street", 34),
                new Alpenist("Bratislav", "Tomsk, Pobeda street", 24),
                new Alpenist("Sergei", "Minsk, Mir street", 45),
                new Alpenist("Nikolay", "Kiev, Kr.comminarov street", 36),
                new Alpenist("Vladimir", "Penza, Oktyabrya street", 48),
                new Alpenist("Denis", "Boston, 12 street", 18),
                new Alpenist("Svetlana", "Podolsk, Pobeda street", 26),
                new Alpenist("Sam", "Virginia, 15 street", 47),
                new Alpenist("Rokky", "Kansas, west street", 27),
                new Alpenist("Vadim", "Rostov, Lenin street", 43)
        };

        Gora[] goras = new Gora[]{
                new Gora("Elbrus", "Russia", 5642),
                new Gora("Monoblan", "France", 4809),
                new Gora("PikPobedi", "China", 7439)
        };

        Group[] groups = new Group[]{
                new Group(true, LocalDate.of(2021, 6, 20), 10, goras[0]),
                new Group(true, LocalDate.of(2021, 5, 15), 15, goras[1]),
                new Group(true, LocalDate.of(2021, 8, 10), 10, goras[2])
        };

        for (int i = 0; i < 10; i++) {
            if (i < 3) {
                if (groups[0].addAlpenist(alpenists[i]))
                    alpenists[i].getGroups().add(groups[0]);
            }
            if (i >= 3 && i < 6) {
                if (groups[1].addAlpenist(alpenists[i]))
                    alpenists[i].getGroups().add(groups[1]);
                if (groups[2].addAlpenist(alpenists[i]))
                    alpenists[i].getGroups().add(groups[2]);
            }
            if (i >= 6 && i < 10) {
                if (groups[2].addAlpenist(alpenists[i]))
                    alpenists[i].getGroups().add(groups[2]);
            }
        }

        manager.getTransaction().begin();
        for (Gora gora : goras) {
            goraDao.add(gora);
        }
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        for (Alpenist alpenist : alpenists) {
            alpenistDao.add(alpenist);
        }
        manager.getTransaction().commit();

        manager.getTransaction().begin();
        for (Group group : groups) {
            groupsDao.add(group);
        }
        manager.getTransaction().commit();


        manager.getTransaction().begin();
        List<Alpenist> alpenistList = alpenistDao.getByAge(28,36);
        manager.getTransaction().commit();
        for (Alpenist alpenist : alpenistList) {
            System.out.println("Name: " + alpenist.getName() +"\n"+
                    "Address: " + alpenist.getAddress() + "\n" +
                    "Age: " + alpenist.getAge());
        }


        manager.getTransaction().begin();
        List<Group> groupAlpenists = groupsDao.getByGoraName("Elbrus");
        manager.getTransaction().commit();
        for (Group group : groupAlpenists) {
            System.out.println("id: " + group.getId() +"\n"+
                    "mountain: " + group.getGora().getName() + "\n" +
                    "date: " + group.getDate());
        }

        manager.getTransaction().begin();
        List<Gora> gora1 = goraDao.getGoraByCountry("Russia");
        manager.getTransaction().commit();
        for (Gora gora : gora1) {
            System.out.println("id: " + gora.getId() +"\n"+
                    "name: " + gora.getName() + "\n" +
                    "height: " + gora.getHeight());
        }

        manager.getTransaction().begin();
        List<Group> group1 = groupsDao.getByConditionIsOpen(true);;
        manager.getTransaction().commit();//unmanaged
        for (Group group : group1) {
            System.out.println("id: " + group.getId() +"\n"+
                    "mountain: " + group.getGora().getName() + "\n" +
                    "date: " + group.getDate());

        }
        manager.close();


    }
}