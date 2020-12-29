import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "group")
@NamedQueries({
        @NamedQuery(name = "Group.getByGoraName",
                query = "SELECT g FROM Group g JOIN FETCH g.gora m  WHERE m.name = :name"),
        @NamedQuery(name = "Group.getByConditionIsOpen",
                query = "SELECT g FROM Group g WHERE g.isOpen = :condition"),
})
public class Group extends BaseIdentify {

    private int capacity = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)

    private Gora gora;

    @Column(nullable = false)
    private boolean isOpen;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private int duration;

    @ManyToMany
    @JoinTable(name = "al_group",
            joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "al_id"))


    private List<Alpenist> alpenists = new ArrayList<>();


    public Group(boolean isOpen, LocalDate date, int duration, Gora gora) {
        this.isOpen = isOpen;
        this.date = date;
        this.duration = duration;
        this.gora = gora;
    }

    public Gora getGora() {
        return gora;
    }

    public void setGora(Gora gora) {
        this.gora = gora;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Alpenist> getAlpenist() {
        return alpenists;
    }

    public void setAlpenist(List<Alpenist> alpenists) {
        this.alpenists = alpenists;
    }

    public boolean addAlpenist(Alpenist alpenist) {
        if (isOpen) {
            alpenists.add(alpenist);
            capacity++;
            if (capacity == 7) setOpen(false);
            return true;
        }
        return false;
    }
}