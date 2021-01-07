package org.mavenworkforce.controllers;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.mavenworkforce.configs.security.jwt.JwtUtility;
import org.mavenworkforce.configs.security.userimpl.UserDetailsImpl;
import org.mavenworkforce.exceptions.UserAlreadyExistException;
import org.mavenworkforce.models.EnumRole;
import org.mavenworkforce.models.Role;
import org.mavenworkforce.models.User;
import org.mavenworkforce.pojos.requests.auth.LoginRequest;
import org.mavenworkforce.pojos.requests.auth.SignupRequest;
import org.mavenworkforce.pojos.responses.success.JwtResponse;
import org.mavenworkforce.pojos.responses.success.MessageResponse;
import org.mavenworkforce.pojos.responses.success.SuccessResponse;
import org.mavenworkforce.repositories.RoleRepository;
import org.mavenworkforce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtility jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new ResponseEntity<>(new SuccessResponse(new JwtResponse(jwt,
                userDetails.getEmail()), Instant.now()), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistException(signUpRequest.getEmail());
        }

        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        user.setInteger(0L);
        userRepository.save(user);

        return new ResponseEntity<>(new SuccessResponse(new MessageResponse("User registered successfully!"), Instant.now()), HttpStatus.OK);
    }
}
