package com.nhnacademy.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.BoardAppInitializer;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Reader<T>{
    public List<T> read(String resourcePath, Consumer<T> put) {
        try (InputStream postsInput = new FileInputStream(
            BoardAppInitializer.class.getClassLoader().getResource(resourcePath).getPath())) {
            ObjectMapper mapper = new ObjectMapper();

            List<T> result = mapper
                .reader()
                .forType(new TypeReference<List<T>>() {})
                .readValue(postsInput);

            for (T t : result) {
                put.accept(t);
            }
            return result;
        } catch (IOException e) {
            log.error("", e);
        }
        throw new JsonDataParseExceptiom("데이터 변환에 실패했습니다.");
    }
}
