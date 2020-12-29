import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Gora.getGoraByCountry",
                query = "SELECT g FROM Gora g WHERE g.country = :country"),
})
public class Gora extends BaseIdentify {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private int height;

    public Gora(String name, String country, int height) throws Exception {
        setName(name);
        setCountry(country);
        setHeight(height);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name.length() < 4) {
            throw new Exception("name < 4");
        } else {
            this.name = name;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws Exception {
        if (country.length() < 4) {
            throw new Exception("country < 4");
        } else {
            this.country = country;
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws Exception {
        if (height < 100) {
            throw new Exception("height < 100");
        } else {
            this.height = height;
        }
    }
}