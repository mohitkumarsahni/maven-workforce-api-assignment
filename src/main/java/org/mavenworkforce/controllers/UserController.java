package org.mavenworkforce.controllers;

import org.mavenworkforce.pojos.requests.other.ResetIntegerRequest;
import org.mavenworkforce.pojos.responses.success.IntegerResponse;
import org.mavenworkforce.pojos.responses.success.SuccessResponse;
import org.mavenworkforce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/next")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> fetchNextInteger(Authentication authentication) {
        Long nextInteger = userService.fetchNextInteger(authentication.getName());
        return new ResponseEntity<>(new SuccessResponse(new IntegerResponse(nextInteger), Instant.now()), HttpStatus.OK);
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> fetchCurrentInteger(Authentication authentication) {
        return new ResponseEntity<>(new SuccessResponse(new IntegerResponse(userService.fetchCurrentInteger(authentication.getName())), Instant.now()), HttpStatus.OK);
    }

    @PutMapping("/current")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> resetInteger(@Valid @RequestBody ResetIntegerRequest resetIntegerRequest, Authentication authentication) {
        return new ResponseEntity<>(new SuccessResponse(new IntegerResponse(userService.resetCurrentInteger(resetIntegerRequest.getCurrent(), authentication.getName())), Instant.now()), HttpStatus.OK);
    }

}
