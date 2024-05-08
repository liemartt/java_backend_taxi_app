package com.liemartt.taxigarage.service;

import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dao.repository.UserRepository;
import com.liemartt.taxigarage.dto.UserDto;
import com.liemartt.taxigarage.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userToUserDto).toList();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
