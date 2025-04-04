package ba.unsa.etf.nbp24t1.security.Authentication;

import ba.unsa.etf.nbp24t1.entity.Auth.Reset;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<String> forgotPassword(@PathVariable String email) {
        authenticationService.sendPasswordViaEmail(email);
        return ResponseEntity.ok("Password reset email sent successfully");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/addUser")
    public ResponseEntity<AuthenticationResponse> addUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.addUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/logout/{email}")
    public void logout(@PathVariable String email) {
        authenticationService.logout(email);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }

//    @PostMapping("/reset-password/{email}/{oldPassword}/{newPassword}")
//    public ResponseEntity<String> resetPassword(@PathVariable String email,
//                              @PathVariable String oldPassword,
//                              @PathVariable String newPassword) {
//        return authenticationService.resetPassword(email, oldPassword, newPassword);
//    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Reset request) {
        return authenticationService.resetPassword(request);
    }
}
