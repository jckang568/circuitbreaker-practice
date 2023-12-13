package kr.co.jayce.circuitbreakerpractice.controller;

import kr.co.jayce.circuitbreakerpractice.service.CircuitBreakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CircuitBreakerController {

    private final CircuitBreakerService circuitBreakerService;

    @GetMapping("/hello")
    public String hello() {
        return circuitBreakerService.getCircuitBreaker();
    }

}
