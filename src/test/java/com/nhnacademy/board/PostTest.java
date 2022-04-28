package com.nhnacademy.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PostTest {

    @Test
    void increaseViewCount() {
        Post post = new Post();

        post.increaseViewCount();

        assertThat(post.getViewCount()).isEqualTo(1);
    }
}
