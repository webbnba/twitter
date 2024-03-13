package ru.bezborodov.twitter.user.tweet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bezborodov.twitter.user.profile.model.UserProfile;
import ru.bezborodov.twitter.user.tweet.model.Tweet;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    Page<Tweet> findAllByUserProfile(UserProfile userProfile, Pageable pageable);

    @Query("""
           SELECT t
           FROM Tweet t
           JOIN FETCH t.userProfile up
           JOIN up.followers flws
           WHERE flws.follower.id = ?1
           """)
    Page<Tweet> findAllFollowerTweets(long followerId, Pageable pageable);
}
