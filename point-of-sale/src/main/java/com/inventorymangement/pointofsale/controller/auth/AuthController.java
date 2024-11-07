package com.inventorymangement.pointofsale.controller.auth;


import com.inventorymangement.pointofsale.dto.LoginDTO;
import com.inventorymangement.pointofsale.dto.RegisterDTO;
import com.inventorymangement.pointofsale.dto.response.AuthResponseDTO;
import com.inventorymangement.pointofsale.entity.Role;
import com.inventorymangement.pointofsale.entity.UserEntity;
import com.inventorymangement.pointofsale.repo.RoleRepo;
import com.inventorymangement.pointofsale.repo.UserRepo;
import com.inventorymangement.pointofsale.security.JWTGenerator;
import com.inventorymangement.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),
                        loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(
                new AuthResponseDTO(token),
                HttpStatus.OK
        );
    }

    @PostMapping("/register")
    public ResponseEntity<StandardResponse> register(@RequestBody RegisterDTO registerDTO){
        if (userRepo.existsByUserName(registerDTO.getUsername())){
            return new ResponseEntity<>(
                    new StandardResponse(400,"Username is taken",null),
                    HttpStatus.BAD_REQUEST
            );
        }

        UserEntity user = new UserEntity();
        user.setUserName(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Role roles = roleRepo.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepo.save(user);

        return new ResponseEntity<>(
                new StandardResponse(200,"Register Success",null),
                HttpStatus.OK
        );
    }
}
