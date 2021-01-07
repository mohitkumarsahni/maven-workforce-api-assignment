package org.mavenworkforce.configs.security.userimpl;

import org.mavenworkforce.exceptions.UserNotFoundException;
import org.mavenworkforce.models.User;
import org.mavenworkforce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()) {
            return UserDetailsImpl.build(user.get());
        }else {
            throw new UsernameNotFoundException(email);
        }
    }
}
