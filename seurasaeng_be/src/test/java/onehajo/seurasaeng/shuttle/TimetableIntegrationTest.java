package onehajo.seurasaeng.shuttle;

import onehajo.seurasaeng.entity.Location;
import onehajo.seurasaeng.entity.Shuttle;
import onehajo.seurasaeng.entity.Timetable;
import onehajo.seurasaeng.shuttle.repository.LocationRepository;
import onehajo.seurasaeng.shuttle.repository.ShuttleRepository;
import onehajo.seurasaeng.shuttle.repository.TimetableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class TimetableIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShuttleRepository shuttleRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @BeforeEach
    void setUp() {
        // 출발지, 도착지
        Location departure = locationRepository.save(Location.builder()
                .locationName("정부과천청사역")
                .latitude(37.4254)
                .longitude(126.9892)
                .build());

        Location destination = locationRepository.save(Location.builder()
                .locationName("아이티센타워")
                .latitude(37.5013)
                .longitude(127.0396)
                .build());

        // Shuttle (출근)
        Shuttle shuttle = shuttleRepository.save(Shuttle.builder()
                .shuttleName("정부과천청사")
                .departure(departure)
                .destination(destination)
                .isCommute(true)
                .build());

        // Timetable
        timetableRepository.save(Timetable.builder()
                .shuttle(shuttle)
                .departureTime(LocalTime.of(7, 20))
                .boardingLocation("7번출구 앞")
                .dropoffLocation("G동 도로 옆")
                .arrivalMinutes(15)
                .totalSeats(45)
                .build());
    }

    @Test
    @DisplayName("시간표 목록 조회 API - 통합테스트 | 데이터 존재 시 200 OK")
    void getTimetableSuccess() throws Exception {
        mockMvc.perform(get("/api/timetables")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commute[0].shuttleName").value("과천-센타워 셔틀"))
                .andExpect(jsonPath("$.commute[0].spotName").value("정부과천청사역"))
                .andExpect(jsonPath("$.commute[0].boardingPoint").value("7번출구 앞"))
                .andExpect(jsonPath("$.commute[0].duration").value("15분"))
                .andExpect(jsonPath("$.commute[0].totalSeats").value("45명"));
    }

    @Test
    @DisplayName("시간표 목록 조회 API - 통합테스트 | 데이터 없을 시 204 No Content")
    void getTimetableNoContent() throws Exception {
        // 데이터 삭제
        timetableRepository.deleteAll();
        shuttleRepository.deleteAll();
        locationRepository.deleteAll();

        mockMvc.perform(get("/api/timetables")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}