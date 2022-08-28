package com.ugisoftware.blog.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ugisoftware.blog.dto.request.LoginRequestDTO;
import com.ugisoftware.blog.dto.request.RegisterRequestDTO;
import com.ugisoftware.blog.dto.response.AuthResponseDTO;
import com.ugisoftware.blog.dto.response.MessageResponseDTO;
import com.ugisoftware.blog.entities.ERole;
import com.ugisoftware.blog.entities.Role;
import com.ugisoftware.blog.entities.User;
import com.ugisoftware.blog.repositories.RoleRepository;
import com.ugisoftware.blog.repositories.UserRepository;
import com.ugisoftware.blog.security.jwt.JWTTokenProvider;
import com.ugisoftware.blog.security.service.RefreshTokenService;
import com.ugisoftware.blog.security.service.UserDetailsImpl;


@RestController
@RequestMapping("/auth")
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
  JWTTokenProvider jwtUtils;
  @Autowired
  private RefreshTokenService refreshTokenService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser( @RequestBody @Valid LoginRequestDTO loginRequest) {
	  Optional<User> user=userRepository.findByUsername(loginRequest.getUsername());
	  User foundedUser=user.get();
	  if (userRepository.existsByUsername(loginRequest.getUsername())) {
		  
		  if(!foundedUser.getEnabled())
		  {
		  return ResponseEntity
	          .badRequest()
	          .body(new MessageResponseDTO("Error: User is banned or been removed"));
		  }
	    }
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new AuthResponseDTO("Bearer "+jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(), 
                         roles,refreshTokenService.createRefreshToken(foundedUser)));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser( @RequestBody @Valid RegisterRequestDTO signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponseDTO("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponseDTO("Error: Email is already in use!"));
    }
   
   
    // Create new user's account
    User user = new User();
    user.setName(signUpRequest.getName());
    user.setSurname(signUpRequest.getSurname());
    user.setUsername(signUpRequest.getUsername());
    user.setPassword(encoder.encode(signUpRequest.getPassword()));
    user.setEmail(signUpRequest.getEmail());
    user.setEnabled(true);
    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();
   
    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
      
    } else {
      strRoles.forEach(role -> {
    	  
        switch (role) {
        case "admin":
        	
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
          
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);
    
   
    Authentication authentication = authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    refreshTokenService.createRefreshToken(user);
   	
    return ResponseEntity.ok(new MessageResponseDTO("User registered successfully!"));
  
  }
}


