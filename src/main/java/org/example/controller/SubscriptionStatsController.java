package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.SubscriptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionStatsController {

    private final SubscriptionService service;

    @GetMapping("/top")
    public List<Map.Entry<String, Long>> top3() {
        return service.getTop3();
    }
}
