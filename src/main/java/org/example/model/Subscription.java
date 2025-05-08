package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serviceName;

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.EAGER)
    @Builder.Default
    @JsonIgnore
    private Set<AppUser> users = new HashSet<>();

}
