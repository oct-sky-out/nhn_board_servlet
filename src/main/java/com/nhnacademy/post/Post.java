package com.nhnacademy.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Post {
    @JsonProperty("id")
    private long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("author")
    private String author;
    @JsonProperty("writeTime")
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime writeTime;
    @JsonProperty("viewCount")
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
