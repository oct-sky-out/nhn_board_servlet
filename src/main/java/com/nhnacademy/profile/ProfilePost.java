package com.nhnacademy.profile;

import com.nhnacademy.commnicate.Communicable;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@MultipartConfig(
    location = "/tmp/", // 서버에 저장될 경로
    fileSizeThreshold = 1024
)
public class ProfilePost implements Communicable {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = ProfilePost.class
        .getClassLoader()
        .getResource("")
        .getPath();

    @Override
    public String communicate(HttpServletRequest req, HttpServletResponse resp) {
        try {
        Part imagePart = req.getPart("image");
        String contentDisposition = imagePart.getHeader(CONTENT_DISPOSITION);
        String fileName = extractFileName(contentDisposition);
        if (imagePart.getSize() > 0) {
            imagePart.write(UPLOAD_DIR + File.separator + fileName);
            imagePart.delete();
        }

        String id = new String(req.getPart("id").getInputStream().readAllBytes());
        String name = new String(req.getPart("name").getInputStream().readAllBytes());
        String password = new String(req.getPart("password").getInputStream().readAllBytes());
        req.setAttribute("id", id);
        req.setAttribute("name", name);
        req.setAttribute("password", password);
        req.setAttribute("imageName", fileName);

        return "forward:/user.nhn";
        }catch (ServletException | IOException e)  {
            log.error("", e);
        }
        throw new ImageUploadException("이미지 업로드 실패");
    }

    private String extractFileName(String contentDisposition) {
        for (String token : contentDisposition.split(";")) {
            if(token.trim().startsWith("filename")){
                return token.split("=")[1].replace("\"", "");
            }
        }
        return null;
    }
}
