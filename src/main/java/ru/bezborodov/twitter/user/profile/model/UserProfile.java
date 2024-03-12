package ru.bezborodov.twitter.user.profile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.bezborodov.twitter.user.subscription.model.Subscription;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "twitter", name = "user_profiles")
public class UserProfile {
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String imageLink;

    @OneToMany
    @JoinColumn(name = "followed_id", referencedColumnName = "id")
    private List<Subscription> followers;
}
