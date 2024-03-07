package ru.bezborodov.twitter.user.subscription.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import ru.bezborodov.twitter.user.profile.model.UserProfile;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(schema = "twitter", name = "subscriptions")
@EntityListeners(AuditingEntityListener.class)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdTimestamp;

    @OneToOne
    private UserProfile follower;

    @OneToOne
    private UserProfile followed;
}
