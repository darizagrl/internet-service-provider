package springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.entity.Service;
import springboot.repository.ServiceRepo;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServicesServiceImpl implements ServicesService {
    @Autowired
    ServiceRepo serviceRepo;

    @Override
    public List<Service> getAllServices() {
        return serviceRepo.findAll();
    }

    @Override
    public Service serviceById(int id) {
        Optional<Service> optional = serviceRepo.findById(id);
        Service service;
        if (optional.isPresent()) {
            service = optional.get();
        } else {
            throw new RuntimeException("Service not found for id :: " + id);
        }
        return service;
    }
}
