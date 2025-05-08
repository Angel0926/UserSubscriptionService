package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.SubscriptionRequest;
import org.example.model.Subscription;
import org.example.service.SubscriptionService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class SubscriptionController {

    private final SubscriptionService service;

    @PostMapping("/{userId}/subscriptions")
    public Subscription add(@PathVariable(name = "userId") Long userId, @RequestBody SubscriptionRequest serviceName) {
        return service.addSubscription(userId, serviceName.name());
    }

    @GetMapping("/{userId}/subscriptions")
    public List<Subscription> getUserSubscriptions(@PathVariable("userId") Long userId) {
        return service.getUserSubscriptions(userId);
    }

    @DeleteMapping("/{userId}/subscriptions/{subId}")
    public void delete(@PathVariable("userId") Long userId, @PathVariable("subId") Long subId) {
        service.deleteSubscription(userId, subId);
    }
}
