package springboot.service;

import springboot.entity.Service;

import java.util.List;

public interface ServicesService {
    List<Service> getAllServices();

    Service serviceById(int id);
}
