package springboot.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

public class TariffDTO {
    private Integer idTariff;
    @NotEmpty
    @NotBlank
    private String name;
    private String description;
    @Min(0)
    @Max(1000)
    @NumberFormat
    private double price;

    private String type;

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
