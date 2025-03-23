package com.griotold.jwt_refresh.config;

import com.griotold.jwt_refresh.domain.entity.Post;
import com.griotold.jwt_refresh.domain.entity.User;
import com.griotold.jwt_refresh.domain.enums.Provider;
import com.griotold.jwt_refresh.domain.enums.Role;
import com.griotold.jwt_refresh.domain.repository.PostRepository;
import com.griotold.jwt_refresh.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(User.create("social1234", Provider.KAKAO, "test@email.com", "nick", Role.MEMBER));

        List<Post> posts = List.of(Post.create("포스트1"), Post.create("포스트2"), Post.create("포스트3"));
        postRepository.saveAll(posts);
    }
}
