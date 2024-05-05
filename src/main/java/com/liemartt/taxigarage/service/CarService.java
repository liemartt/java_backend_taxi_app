package com.liemartt.taxigarage.service;

import com.liemartt.taxigarage.dao.entity.Car;
import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dao.repository.CarRepository;
import com.liemartt.taxigarage.dao.repository.UserRepository;
import com.liemartt.taxigarage.dto.CarDto;
import com.liemartt.taxigarage.dto.CarMapper;
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


    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(carMapper::toDto).toList();
    }
    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id);
        return carMapper.toDto(car);
    }
    public void createCar(CarDto carDto) {
        Car car = carMapper.toEntity(carDto);
        car = carRepository.save(car);
    }
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }
    public void addUserToCar(Long carId, Long userId) {
        Car car = carRepository.findById(carId);
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(car::setUser);
    }
    public void removeUserFromCar(Long carId) {
        Car car = carRepository.findById(carId);
        car.setUser(null);
    }
}
