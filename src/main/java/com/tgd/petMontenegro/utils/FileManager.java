package com.tgd.petMontenegro.utils;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public class FileManager {

    private static final String UPLOAD_DIR = "/resources/img/uploads/";

    public static String uploadImage(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty";
        }

        try {
            // Get the file's original name
            String originalFilename = file.getOriginalFilename();
            // Create a new file in the specified directory
            File dest = new File(UPLOAD_DIR + originalFilename);
            // Transfer the received file to the destination file
            file.transferTo(dest);
            return "File uploaded successfully: " + originalFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
}