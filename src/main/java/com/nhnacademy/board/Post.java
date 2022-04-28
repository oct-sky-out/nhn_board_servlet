package com.nhnacademy.board;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
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
}
