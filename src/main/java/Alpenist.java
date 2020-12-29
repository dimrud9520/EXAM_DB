import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "alpenist")
@NamedQueries({
        @NamedQuery(name = "Alpenist.getByAge",
                query = "SELECT a FROM Alpenist a WHERE a.age >= :from and a.age < :to" ),
})
public class Alpenist extends BaseIdentify {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "alpenists")
    private List<Group> groupAlpenists = new ArrayList<>();



    public Alpenist(String name, String address, int age) throws Exception {
        setName(name);
        setAddress(address);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() < 3) {
            throw new Exception("name<3");
        } else {
            this.name = name;
        }
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws Exception {
        if (address.length() < 5) {
            throw new Exception("address<5");
        } else {
            this.address = address;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        if (age < 18) {
            throw new Exception("age < 18");
        } else {
            this.age = age;
        }
    }

    public List<Group> getGroups() {
        return groupAlpenists;
    }

    public void setGroups(List<Group> groupClimbers) {
        this.groupAlpenists = groupClimbers;
    }
}