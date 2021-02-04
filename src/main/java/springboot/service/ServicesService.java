package springboot.service;

import springboot.entity.Service;
import springboot.entity.Tariff;

import java.util.List;

public interface ServicesService {
    List<Service> getAllServices();
    public Service serviceById(int id);
}
