package org.sopt.domain;

import jakarta.persistence.*;
import org.sopt.validator.PostValidator;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // 게시글 상세 화면 — 특정 게시글 식별용

    private String title;     // 목록, 상세, 글쓰기 화면 — 제목

    private String content;   // 목록(미리보기), 상세(전체) 화면 — 내용

    private LocalDateTime createdAt; // 목록, 상세 화면 — 작성 시각

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Post() {}  // JPA 기본 생성자

    public Post(String title, String content, User user, BoardType boardType) {
        PostValidator.validate(title, content);

        this.title = title;
        this.content = content;
        this.user = user;
        this.boardType = boardType;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public User getUser() { return user; }
    public BoardType getBoardType() { return boardType; }

    public void update(String title, String content) {
        PostValidator.validate(title, content);
        this.title = title;
        this.content = content;
    }
}
