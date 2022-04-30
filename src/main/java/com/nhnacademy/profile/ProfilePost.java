package com.nhnacademy.profile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "profilePost", urlPatterns = "/profile")
@MultipartConfig(
    location = "/tmp/", // 서버에 저장될 경로
    fileSizeThreshold = 1024
)
public class ProfilePost extends HttpServlet {
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = ProfilePost.class
        .getClassLoader()
        .getResource("")
        .getPath();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("image/jpeg");
        String fileName = req.getParameter("fileName");

        try (FileInputStream fis = new FileInputStream(UPLOAD_DIR + "/" + fileName);
             BufferedOutputStream bout = new BufferedOutputStream(resp.getOutputStream())
        ) {
            byte[] image = fis.readAllBytes();
            bout.write(image, 0, image.length);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
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

        req.getRequestDispatcher("/user.nhn")
            .forward(req, resp);
        }catch (ServletException | IOException e)  {
            log.error("", e);
        }
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
