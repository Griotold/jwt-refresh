package com.griotold.jwt_refresh.presentation;

import com.griotold.jwt_refresh.domain.entity.Post;
import com.griotold.jwt_refresh.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@Controller
public class PostController {

    private final PostRepository postRepository;

    @GetMapping
    public ResponseEntity<List<Post>> get() {
        return ResponseEntity.ok(postRepository.findAll());
    }

}
