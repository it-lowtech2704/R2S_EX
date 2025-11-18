package com.springboot.controller;
import com.springboot.dto.*;
import com.springboot.entity.User;
import com.springboot.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterReponse> register(
            @Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.ok(service.register(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthReponse> login(
            @Valid @RequestBody AuthRequest req) {
        return ResponseEntity.ok(service.login(req));
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody ChangePassword req,
            Authentication authentication) {
        // user hiện tại từ SecurityContext
        User currentUser = (User) authentication.getPrincipal();
        service.changePassword(currentUser, req);

        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
