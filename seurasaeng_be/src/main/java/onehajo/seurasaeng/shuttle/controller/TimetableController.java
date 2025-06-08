package onehajo.seurasaeng.shuttle.controller;

import lombok.RequiredArgsConstructor;
import onehajo.seurasaeng.shuttle.dto.TimetableResponseDto;
import onehajo.seurasaeng.shuttle.service.TimetableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/timetables")
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping
    public ResponseEntity<TimetableResponseDto> getTimetable() {
        TimetableResponseDto response = timetableService.getTimetable();

        if ((response.getCommute() == null || response.getCommute().isEmpty()) &&
                (response.getOffwork() == null || response.getOffwork().isEmpty())) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }
}