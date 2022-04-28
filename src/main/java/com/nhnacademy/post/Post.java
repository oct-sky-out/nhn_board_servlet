package com.nhnacademy.post;

import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Post {
    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime writeTime;
    private int viewCount;

    public void increaseViewCount(){
        viewCount++;
    }

    public long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getAuthor() {
        return this.author;
    }

    public LocalDateTime getWriteTime() {
        return this.writeTime;
    }

    public int getViewCount() {
        return this.viewCount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setWriteTime(LocalDateTime writeTime) {
        this.writeTime = writeTime;
    }
}
