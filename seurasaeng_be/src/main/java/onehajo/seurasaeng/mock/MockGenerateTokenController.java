package onehajo.seurasaeng.mock;

import lombok.RequiredArgsConstructor;
import onehajo.seurasaeng.entity.User;
import onehajo.seurasaeng.user.repository.UserRepository;
import onehajo.seurasaeng.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MockGenerateTokenController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @GetMapping("/test-token")
    public Map<String, String> generateTestToken() {
        Optional<User> user = userRepository.findByEmail("yujin@example.com");
        String token = jwtUtil.generateToken(user.get().getId(), user.get().getName(), user.get().getEmail());
        return Map.of("accessToken", token);
    }
}
