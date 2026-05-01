package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.request.CreateUserRequest;
import org.sopt.exception.notfound.UserNotFoundException;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser(CreateUserRequest request){

        User user = new User(request.nickname(), request.email());
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
