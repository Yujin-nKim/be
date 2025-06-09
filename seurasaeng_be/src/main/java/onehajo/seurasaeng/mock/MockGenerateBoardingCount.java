package onehajo.seurasaeng.mock;

import lombok.RequiredArgsConstructor;
import onehajo.seurasaeng.entity.User;
import onehajo.seurasaeng.qr.service.BoardingService;
import onehajo.seurasaeng.user.repository.UserRepository;
import onehajo.seurasaeng.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/count")
@RequiredArgsConstructor
public class MockGenerateBoardingCount {

    private final BoardingService boardingService;

    @GetMapping("/{shuttleId}")
    public Map<String, Long> generateBoardingCount(@PathVariable Long shuttleId) {
        Long count = boardingService.incrementBoardingCount(shuttleId);
        return Map.of("count 생성 완료" , count);
    }
}

