package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.AppUser;
import org.example.model.Subscription;
import org.example.repository.UserRepository;
import org.example.repository.SubscriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final UserRepository userRepo;
    private final SubscriptionRepository subRepo;

    @Transactional
    public Subscription addSubscription(Long userId, String serviceName) {
        AppUser user = userRepo.findById(userId).orElseThrow();

        if (user.getSubscriptions().stream()
                .anyMatch(s -> s.getServiceName().equalsIgnoreCase(serviceName))) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Пользователь уже подписан на сервис " + serviceName);
        }

        Subscription subscription = subRepo.findByServiceName(serviceName)
                .orElseGet(() -> subRepo.save(Subscription.builder().serviceName(serviceName).build()));

        subscription.getUsers().add(user);
        user.getSubscriptions().add(subscription);

        userRepo.save(user);
        return subscription;
    }


    @Transactional
    public List<Subscription> getUserSubscriptions(Long userId) {
        AppUser user = userRepo.findById(userId).orElseThrow();
        if (user.getSubscriptions() == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(user.getSubscriptions());
    }

    @Transactional
    public void deleteSubscription(Long userId, Long subId) {
        AppUser user = userRepo.findById(userId).orElseThrow();
        Subscription subscription = subRepo.findById(subId).orElseThrow();
        user.getSubscriptions().remove(subscription);
        userRepo.save(user);
    }

    @Transactional
    public List<Map.Entry<String, Long>> getTop3() {
        return subRepo.findAll().stream()
                .collect(Collectors.groupingBy(Subscription::getServiceName, Collectors.summingLong(s -> s.getUsers().size())))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .toList();
    }
}
