package kr.co.jayce.circuitbreakerpractice.service;

import kr.co.jayce.circuitbreakerpractice.constant.Resilience4jCode;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CircuitBreakerService {

    @CircuitBreaker(name = Resilience4jCode.CIRCUIT_TEST_5000, fallbackMethod = "getCircuitBreakerFallback")
    public String getCircuitBreaker() {
        try{
            Thread.sleep(1000);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
        // runtimeException();
        return "hello world!";
    }

    private void runtimeException() {
        throw new RuntimeException("failed");
    }

    private String getCircuitBreakerFallback(Throwable t) {
        return "getCircuitBreakerFallback! exception type: " + t.getClass() + "exception, message: " + t.getMessage();
    }
}
