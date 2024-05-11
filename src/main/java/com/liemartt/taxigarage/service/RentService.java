package com.liemartt.taxigarage.service;

import com.liemartt.taxigarage.dao.entity.Car;
import com.liemartt.taxigarage.dao.entity.Rent;
import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dao.repository.CarRepository;
import com.liemartt.taxigarage.dao.repository.RentRepository;
import com.liemartt.taxigarage.dao.repository.UserRepository;
import com.liemartt.taxigarage.dto.RentRequestDto;
import com.liemartt.taxigarage.dto.RentResponseDto;
import com.liemartt.taxigarage.mappers.RentMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentMapper rentMapper;

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public Optional<Rent> getRentById(Long id) {
        return rentRepository.findById(id);
    }

    public List<RentResponseDto> getEndedRentsByUsername(String username) {
        List<Rent> rents = rentRepository.findAllByUserUsername(username);
        return rents.stream().filter(x -> x.getEndDate() != null).toList().stream().map(rentMapper::rentToRentResponseDto).toList();
    }

    public List<RentResponseDto> getCurrentRentsByUsername(String username) {
        List<Rent> rents = rentRepository.findAllByUserUsername(username);
        return rents.stream().filter(x -> x.getEndDate() == null).toList().stream().map(rentMapper::rentToRentResponseDto).toList();
    }

    @Transactional
    public Optional<Rent> createRent(RentRequestDto rentCarDto) {
        Optional<Car> car = carRepository.findById(rentCarDto.carId());
        Optional<User> user = userRepository.findByUsername(rentCarDto.username());

        if (car.isPresent() && user.isPresent()) {
            Car carEntity = car.get();
            User userEntity = user.get();
            if (!carEntity.isRented()) {
                carEntity.setRented(true);
                Rent rent = new Rent();
                rent.setCar(carEntity);
                rent.setUser(userEntity);
                rentRepository.save(rent);
                return Optional.of(rent);
            }
        }
        return Optional.empty();
    }

    @Transactional
    public void endRentById(Long rentId) {
        Optional<Rent> rent = rentRepository.findById(rentId);
        if (rent.isPresent()) {
            Rent rentEntity = rent.get();
            rentEntity.getCar().setRented(false);
            rentEntity.setEndDate(LocalDate.now());
        }
    }

    public List<RentResponseDto> getAllRentsByUserId(Long userId) {
        return rentRepository.findAllByUserId(userId).stream().map(rentMapper::rentToRentResponseDto).toList();
    }

    public List<RentResponseDto> getAllRentsByUsername(String username) {
        return rentRepository.findAllByUserUsername(username).stream().map(rentMapper::rentToRentResponseDto).toList();
    }

    public int countRentsByCarAndUser(Long carId, String username){
        User user = userRepository.findByUsername(username).get();
        return rentRepository.countRentsByCarIdAndUserId(carId, user.getId());
    }
}
