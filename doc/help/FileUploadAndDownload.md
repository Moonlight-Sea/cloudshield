#

## SpringBootApplication

### 文件下载上传

[File Downloading in Spring Boot Applications](https://www.springcloud.io/post/2023-03/springboot-download/#gsc.tab=0)

#### Download a single file

```java
package io.springcloud.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @GetMapping
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // The file to be downloaded.
        Path file = Paths.get("E:\\test.mp4");

        // Get the media type of the file
        String contentType = Files.probeContentType(file);
        if (contentType == null) {
            // Use the default media type
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        response.setContentType(contentType);
        // File Size
        response.setContentLengthLong(Files.size(file));
        /* Building the Content-Disposition header with the ContentDisposition utility class can avoid the problem of garbled downloaded file names.
         */
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(file.getFileName().toString(), StandardCharsets.UTF_8)
                .build()
                .toString());
        // Response data to the client
        Files.copy(file, response.getOutputStream());
    }
}
```

#### Compressing files with Gzip

```java
package io.springcloud.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @GetMapping("/gzip")
    public void gzipDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Path file = Paths.get("E:\\test.mp4");

        String contentType = Files.probeContentType(file);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // Tell the client what encoding is used by the body and the client will automatically decode it.
        response.setHeader(HttpHeaders.CONTENT_ENCODING, "gzip");
        response.setContentType(contentType);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename(file.getFileName().toString(), StandardCharsets.UTF_8)
                .build()
                .toString());

        // Compress the body with GZIPOutputStream
        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(response.getOutputStream())) {
            Files.copy(file, gzipOutputStream);
        }
    }
}
```

#### Download multiple files

```java
package io.springcloud.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @GetMapping("/zip")
    public void zipDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // List of files to be downloaded
        List<Path> files = Arrays.asList(Paths.get("E:\\test.mp4"),
                Paths.get("E:\\node.txt"),
                Paths.get("E:\\keys.txt"));


        response.setContentType("application/zip"); // zip archive format
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment()
                .filename("download.zip", StandardCharsets.UTF_8)
                .build()
                .toString());


        // Archiving multiple files and responding to the client
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            for (Path file : files) {
                try (InputStream inputStream = Files.newInputStream(file)) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getFileName().toString()));
                    StreamUtils.copy(inputStream, zipOutputStream);
                    zipOutputStream.flush();
                }
            }
        }
    }
}
```

- [Download an Image or a File with Spring MVC](https://www.baeldung.com/spring-controller-return-image-file)
- [Spring MVC Download File Controller Example](https://howtodoinjava.com/spring-mvc/spring-mvc-download-file-controller-example/)
- [Uploading and Downloading Files with Spring Boot](https://www.devglan.com/spring-boot/spring-boot-file-upload-download)
- [Spring Boot 文件上传与下载](https://zhuanlan.zhihu.com/p/60856486)