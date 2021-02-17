package springboot.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "tariff")
public class Tariff {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "tariffIdSeq", sequenceName = "tariff_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tariffIdSeq")
    private Integer idTariff;
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Column(name = "description")
    @NotEmpty(message = "Description cannot be empty")
    private String description;
    @Column(name = "price", nullable = false)
    @Min(0) @Max(1000)
    private double price;
    @Column(name = "type")
    @JoinTable(name = "service",
            joinColumns = @JoinColumn(name = "name", referencedColumnName = "type"))
    @NotEmpty(message = "Type cannot be empty")
    private String type;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_tariffs",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "tariff_id", referencedColumnName = "id"
            ))
    private Set<User> users;

    public Tariff() {
    }

    public Tariff(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Integer getIdTariff() {
        return idTariff;
    }

    public void setIdTariff(Integer idTariff) {
        this.idTariff = idTariff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
