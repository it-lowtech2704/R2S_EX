package com.springboot.service;


import com.springboot.Role;
import com.springboot.dto.*;
import com.springboot.entity.User;
import com.springboot.exception.BadRequestException;
import com.springboot.exception.InvalidCredentialsException;
import com.springboot.repository.UserRepo;
import com.springboot.security.JwtService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final JwtService jwt;

    public AuthService(UserRepo userRepo, JwtService jwt) {
        this.userRepo = userRepo;
        this.jwt = jwt;
    }

    @Transactional
    public RegisterReponse register(RegisterRequest req){
        if (userRepo.existsByUsername(req.username())){
            throw new IllegalStateException("Username is already in use");
        }
        User u = new User();
        u.setUsername(req.username());
        u.setPasswordHash(BCrypt.hashpw(req.password(), BCrypt.gensalt()));
        u.setRole(req.role() == null ? Role.USER : req.role());

        User savedUser = userRepo.save(u);

        return new RegisterReponse(
           savedUser.getUsername(),
                savedUser.getRole().toString()
        );
    }

    @Transactional(readOnly = true)
    public AuthReponse login(AuthRequest req) {
        var u = userRepo.findByUsername(req.username())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!BCrypt.checkpw(req.password(), u.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        String token = jwt.generateToken(u);
        return new AuthReponse(token);
    }


    @Transactional
    public void changePassword(User user, ChangePassword req) {

        // 1. Sai oldPassword
        if (!BCrypt.checkpw(req.oldPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Current password is incorrect");
        }

        // 2. newPassword == oldPassword
        if (req.oldPassword().equals(req.newPassword())) {
            throw new BadRequestException(
                    "New password must be different from the current password");
        }

        // 3. newPassword != confirmNewPassword
        if (!req.newPassword().equals(req.confirmPassword())) {
            throw new BadRequestException(
                    "New password confirmation does not match");
        }

        // 4. Update password
        String newHash = BCrypt.hashpw(req.newPassword(), BCrypt.gensalt());
        user.setPasswordHash(newHash);
        userRepo.save(user);
    }
}
