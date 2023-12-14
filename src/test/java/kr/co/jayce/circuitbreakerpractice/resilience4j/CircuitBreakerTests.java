package kr.co.jayce.circuitbreakerpractice.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import kr.co.jayce.circuitbreakerpractice.service.CircuitBreakerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CircuitBreakerTests {

    @Autowired
    private CircuitBreakerService circuitBreakerService;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    /*@BeforeEach
    void setUp() {
        // CircuitBreakerRegistry에서 CircuitBreaker 가져오기
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.ofDefaults();
        this.circuitBreaker = circuitBreakerRegistry.circuitBreaker("circuit-test-5000");
    }*/
    @Test
    void testCircuitBreakerSlowCall() throws InterruptedException {
        // slowCallDurationThreshold를 초과하는 시간이 걸리는 시뮬레이션
        List<String> result = new ArrayList<>();
        for(int i = 0 ; i < 12 ; i ++) {
            result.add(circuitBreakerService.getCircuitBreaker());
        }

        System.out.println(result);
        // CircuitBreaker가 열린 경우 fallback 메소드가 호출되었는지 확인
        // assertEquals("fallbackResult", result); // fallbackMethod에 따라 변경

        // CircuitBreaker 상태 확인
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("circuit-test-5000");
        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
    }
}

