package com.nhnacademy.profile;

import com.nhnacademy.commnicate.Communicable;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProfileGet implements Communicable {
    private static final String UPLOAD_DIR = ProfilePost.class
    .getClassLoader()
    .getResource("")
    .getPath();

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("image/jpeg");
        String fileName = req.getParameter("image");
        if(Objects.isNull(fileName)){
            return null;
        }
        try (FileInputStream fis = new FileInputStream(UPLOAD_DIR + "/" + fileName);
             BufferedOutputStream bout = new BufferedOutputStream(resp.getOutputStream())
        ) {
            byte[] image = fis.readAllBytes();
            bout.write(image, 0, image.length);
        } catch (IOException e) {
            log.error("", e);
        }
        return null;
    }
}
