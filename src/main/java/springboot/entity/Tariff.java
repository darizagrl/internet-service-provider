package springboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "tariff")
public class Tariff {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "tariffIdSeq", sequenceName = "tariff_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tariffIdSeq")
    private Integer idTariff;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "type")
    @JoinTable(name = "service",
            joinColumns = @JoinColumn(name = "name", referencedColumnName = "type"))
    private String type;
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
}
