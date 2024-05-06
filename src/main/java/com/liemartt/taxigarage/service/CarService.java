package com.liemartt.taxigarage.service;

import com.liemartt.taxigarage.dao.entity.Car;
import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dao.repository.CarRepository;
import com.liemartt.taxigarage.dao.repository.UserRepository;
import com.liemartt.taxigarage.dto.CarDto;
import com.liemartt.taxigarage.dto.CarMapper;
import com.liemartt.taxigarage.dto.RentCarDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final CarMapper carMapper;


    public List<Car> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public void saveCar(CarDto carDto) {
        Car car = carMapper.toEntity(carDto);
        car = carRepository.save(car);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Transactional
    public void addUserToCar(RentCarDto rentCarDto) {
        Optional<Car> car = carRepository.findById(rentCarDto.carId());
        Optional<User> user = userRepository.findById(rentCarDto.userId());
        System.out.println(car);
        System.out.println(user);
        if (car.isPresent() && user.isPresent()) {
            Car carEntity = car.get();
            User userEntity = user.get();
            carEntity.setUser(userEntity);
        }
    }

    public void removeUserFromCar(Long carId) {
        Optional<Car> car = carRepository.findById(carId);
        car.ifPresent(value -> value.setUser(null));
    }
    public List<Car> getAvailableCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().filter(car->car.getUser()==null).toList();
    }
}
