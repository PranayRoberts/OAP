package com.capgemini.oap.service;

import com.capgemini.oap.exception.UserAlreadyExistsException;
import com.capgemini.oap.exception.UserNotFoundException;
import com.capgemini.oap.model.User;
import com.capgemini.oap.pojo.UserRequest;
import com.capgemini.oap.pojo.UserResponse;
import com.capgemini.oap.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
    @Autowired
    private UserRepository userRepository;

    public String registerUser(UserRequest user) {
        Optional<User> userNameExists = userRepository.findUserByUserName(user.getUserName());
        if (userNameExists.isPresent()){
            logger.warning("Username already exists");
            throw new UserAlreadyExistsException("Username already exists");}
        Optional<User> userEmailExists = userRepository.findUserByEmail(user.getEmail());
        if (userEmailExists.isPresent()) {
            logger.warning("Email already exists");
            throw new UserAlreadyExistsException("Email already exists");
        }
        User userDetails = User.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword()).build();
        logger.info("Registering a new User");
        User savedUser = userRepository.save(userDetails);
        return savedUser.getUserName();
    }
    public String loginUser(String value, String password) {
        Optional<User> userDetailsByUsername = userRepository.findUserByUserName(value);
        if (userDetailsByUsername.isEmpty()) {
            Optional<User> userDetailsByEmail = userRepository.findUserByEmail(value);
            if (userDetailsByEmail.isEmpty()) {
                logger.warning("Invalid email or username");
                throw new UserNotFoundException("Invalid email or username");
            } else if (!password.equals(userDetailsByEmail.get().getPassword())) {
                logger.warning("Wrong password");
                throw new UserNotFoundException("Wrong password");
            }
            return userDetailsByEmail.get().getUserName();
        } else if (!password.equals(userDetailsByUsername.get().getPassword())) {
            logger.warning("Wrong password");
            throw new UserNotFoundException("Wrong password");
        }
        return userDetailsByUsername.get().getUserName();
    }

    public UserResponse getUserByUserName(String username) {
        Optional<User> user = userRepository.findUserByUserName(username);
        if (user.isEmpty()){
            logger.warning("Invalid username");
            throw new UserNotFoundException("Invalid username");
        }
        return UserResponse.builder()
                .id(user.get().getId())
                .userName(user.get().getUserName())
                .email(user.get().getEmail())
                .build();
    }
    @Override
    public UserResponse getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            logger.warning("Invalid User ID");
            throw new UserNotFoundException("Invalid ID");
        }
        return UserResponse.builder()
                .id(user.get().getId())
                .userName(user.get().getUserName())
                .email(user.get().getEmail())
                .build();
    }
}
