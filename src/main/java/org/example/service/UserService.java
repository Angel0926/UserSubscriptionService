package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.AppUser;
import org.example.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public AppUser create(AppUser user) {
        return repository.save(user);
    }


    @Transactional
    public AppUser getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public List<AppUser> getAll() {
        return repository.findAll();
    }

    public AppUser update(Long id, AppUser data) {
        AppUser user = getById(id);
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }
}
