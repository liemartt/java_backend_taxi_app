package com.liemartt.taxigarage.service;
import com.liemartt.taxigarage.dao.entity.User;
import com.liemartt.taxigarage.dao.repository.UserRepository;
import com.liemartt.taxigarage.dto.JwtResponse;
import com.liemartt.taxigarage.dto.SignInRequest;
import com.liemartt.taxigarage.dto.SignUpRequest;
import com.liemartt.taxigarage.exceptions.AppError;
import com.liemartt.taxigarage.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public ResponseEntity<?> signin(SignInRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.username());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token, userDetails.getAuthorities().stream().filter(x->x.getAuthority().equals("ROLE_ADMIN")).count()==1));
    }

    public ResponseEntity<?> signup(SignUpRequest registrationUserDto) {
        if (userService.getUserByUsername(registrationUserDto.username()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createUser(registrationUserDto);
        return ResponseEntity.ok(userMapper.userToUserDto(user));
    }

}