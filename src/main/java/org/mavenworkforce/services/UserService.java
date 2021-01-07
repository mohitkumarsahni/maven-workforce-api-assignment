package org.mavenworkforce.services;

import org.mavenworkforce.exceptions.UserNotFoundException;
import org.mavenworkforce.models.User;
import org.mavenworkforce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Long fetchNextInteger(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("User with ID"+email+" does not exist in the system.");
        }
        User currentUser = optionalUser.get();
        Long currentInteger = currentUser.getInteger();
        currentUser.setInteger(currentInteger+1);
        userRepository.save(currentUser);

        return currentInteger+1;
    }

    public Long fetchCurrentInteger(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("User with ID"+email+" does not exist in the system.");
        }

        return optionalUser.get().getInteger();
    }

    public Long resetCurrentInteger(Long value, String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("User with ID"+email+" does not exist in the system.");
        }
        User currentUser = optionalUser.get();
        currentUser.setInteger(value);
        currentUser = userRepository.save(currentUser);

        return currentUser.getInteger();
    }
}
