package com.liemartt.taxigarage.service;

import com.liemartt.taxigarage.dao.entity.Car;
import com.liemartt.taxigarage.dao.repository.CarRepository;
import com.liemartt.taxigarage.dao.repository.UserRepository;
import com.liemartt.taxigarage.dto.CarDto;
import com.liemartt.taxigarage.mappers.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final CarMapper carMapper;


    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public void saveCar(CarDto carDto) {
        Car car  = carMapper.toEntity(carDto);
        carRepository.save(car);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    public List<Car> getAvailableCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(car-> !car.isRented()).toList();
    }
}
