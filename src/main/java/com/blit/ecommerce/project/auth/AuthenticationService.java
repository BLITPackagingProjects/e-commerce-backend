package com.blit.ecommerce.project.auth;

import com.blit.ecommerce.project.config.JwtService;
import com.blit.ecommerce.project.entities.PasswordResetToken;
import com.blit.ecommerce.project.entities.UserRole;
import com.blit.ecommerce.project.repository.PasswordResetTokenRepository;
import com.blit.ecommerce.project.repository.RoleRepository;
import com.blit.ecommerce.project.repository.UserRepository;
import com.blit.ecommerce.project.entities.User;
import com.blit.ecommerce.project.entities.Role;
import com.blit.ecommerce.project.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public AuthenticationResponse register(RegisterRequest request) {


         List<UserRole> listOfRole = new ArrayList<>();


//        var List<Role> listOfRole= new Ro();
//        role.setType_id(userRole);


        if(request.getTypeId()==1){

            UserRole userRole1 = userRoleRepository.findById(1).orElseThrow(null);
            listOfRole.add(userRole1);


        } else if (request.getTypeId()==2) {

            UserRole userRole2  = userRoleRepository.findById(2).orElseThrow(null);
            listOfRole.add(userRole2);

        }

        Role role = new Role();
        role.setUserRoleList(listOfRole);
        roleRepository.save(role);

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();
        repository.save(user);




//        Role userRole = Role.USER;
//        UserRole role = userRoleRepository.findById(1).get();


//        if("SELLER".equals(request.getRole())) {
//            role = userRoleRepository.findById(2).get();
//        }

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                ));
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .user(user)
                .token(jwtToken)
                .build();
    }
    public String validatePasswordResetToken(String token){
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);

        if(passwordResetToken==null){
            return "invalidToken";
        }else if(passwordResetToken.getExpiryDate().before(Calendar.getInstance().getTime())){
            return "expired";
        }else {
            return null;
        }
    }

}

