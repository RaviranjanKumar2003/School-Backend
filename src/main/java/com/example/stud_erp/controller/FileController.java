//package com.example.stud_erp.controller;
//
//import com.example.stud_erp.payload.FileResponse;
//import com.example.stud_erp.service.FileService;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StreamUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URLConnection;
//
//@RestController
//@RequestMapping("/api/file")
//public class FileController {
//
//    @Autowired
//    private FileService fileService;
//
//    @Value("${project.image}")
//    private String path;
//
//    // ================= UPLOAD IMAGE =================
//    @PostMapping("/upload")
//    public ResponseEntity<FileResponse> fileUpload(
//            @RequestParam("image") MultipartFile image
//    ) {
//        String fileName;
//        try {
//            fileName = fileService.uploadImage(path, image);
//        } catch (IOException e) {
//            return new ResponseEntity<>(
//                    new FileResponse(null, "Image upload failed"),
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//        }
//
//        return new ResponseEntity<>(
//                new FileResponse(fileName, "Image uploaded successfully"),
//                HttpStatus.OK
//        );
//    }
//
//    // ================= SERVE IMAGE =================
//    @GetMapping("/profiles/{imageName}")
//    public void downloadImage(
//            @PathVariable String imageName,
//            HttpServletResponse response
//    ) throws IOException {
//
//        InputStream resource = null;
//
//        try {
//            resource = fileService.getResource(path, imageName);
//        } catch (FileNotFoundException e) {
//            //  try lowercase as fallback
//            try {
//                resource = fileService.getResource(path, imageName.toLowerCase());
//            } catch (FileNotFoundException ex) {
//                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                response.getWriter().write("Image not found");
//                return;
//            }
//        }
//
//        String contentType = URLConnection.guessContentTypeFromName(imageName);
//        response.setContentType(
//                contentType != null ? contentType : "application/octet-stream"
//        );
//
//        StreamUtils.copy(resource, response.getOutputStream());
//    }
//
//}




package com.example.stud_erp.controller;

import com.example.stud_erp.payload.FileResponse;
import com.example.stud_erp.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

@RestController
@RequestMapping("/api/file")
@CrossOrigin("*")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    // =====================================================
    // UPLOAD IMAGE / VIDEO
    // =====================================================

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {

        try {

            // =========================================
            // EMPTY CHECK
            // =========================================

            if (file.isEmpty()) {

                return new ResponseEntity<>(
                        new FileResponse(
                                null,
                                "File is empty"
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            // =========================================
            // FILE TYPE CHECK
            // =========================================

            String contentType =
                    file.getContentType();

            if (contentType == null) {

                return new ResponseEntity<>(
                        new FileResponse(
                                null,
                                "Invalid file"
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            // =========================================
            // ALLOW IMAGE + VIDEO
            // =========================================

            boolean isImage =
                    contentType.startsWith("image");

            boolean isVideo =
                    contentType.startsWith("video");

            if (!isImage && !isVideo) {

                return new ResponseEntity<>(
                        new FileResponse(
                                null,
                                "Only image/video allowed"
                        ),
                        HttpStatus.BAD_REQUEST
                );
            }

            // =========================================
            // UPLOAD
            // =========================================

            String fileName =
                    fileService.uploadImage(
                            path,
                            file
                    );

            return new ResponseEntity<>(
                    new FileResponse(
                            fileName,
                            "File uploaded successfully"
                    ),
                    HttpStatus.OK
            );

        } catch (IOException e) {

            e.printStackTrace();

            return new ResponseEntity<>(
                    new FileResponse(
                            null,
                            "Upload failed"
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // =====================================================
    // VIEW IMAGE / VIDEO
    // =====================================================

    @GetMapping("/profiles/{fileName}")
    public void viewFile(
            @PathVariable String fileName,
            HttpServletResponse response
    ) throws IOException {

        InputStream resource;

        try {

            resource =
                    fileService.getResource(
                            path,
                            fileName
                    );

        } catch (FileNotFoundException e) {

            response.setStatus(
                    HttpServletResponse.SC_NOT_FOUND
            );

            response.getWriter().write(
                    "File not found"
            );

            return;
        }

        // =========================================
        // CONTENT TYPE
        // =========================================

        String contentType =
                URLConnection.guessContentTypeFromName(
                        fileName
                );

        if (contentType == null) {

            contentType =
                    "application/octet-stream";
        }

        response.setContentType(
                contentType
        );

        StreamUtils.copy(
                resource,
                response.getOutputStream()
        );
    }
}