package hello.upload.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {
    @Value("${file.dir}")
    private String fileDirectory;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(
            @RequestParam String itemName,
            @RequestParam MultipartFile file,
            HttpServletRequest request) throws IOException {
        log.info("itemName : {}", itemName);
        log.info("request : {}", request);

        if (!file.isEmpty()) {
            String fullPath = fileDirectory + file.getOriginalFilename();
            log.info("fullPath : {}", fullPath);
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }
}
