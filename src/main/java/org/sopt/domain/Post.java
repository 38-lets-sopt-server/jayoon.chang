package org.sopt.domain;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;

public class Post {
    @Id
    @GeneratedValue
    private Long id;          // 게시글 상세 화면 — 특정 게시글 식별용
    private String title;     // 목록, 상세, 글쓰기 화면 — 제목
    private String content;   // 목록(미리보기), 상세(전체) 화면 — 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Long user_id;
    // private final String author;    // 목록, 상세 화면 — 글쓴이

     private final LocalDateTime createdAt; // 목록, 상세 화면 — 작성 시각

    public Post(String title, String content, Long id) {
        this.title = title;
        this.content = content;
        this.user_id = id;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    // public String getAuthor() { return author; }
     public LocalDateTime getCreatedAt() { return createdAt; }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
