package fa.training.controller;

import fa.training.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class UploadController {

    private final UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/api/movies/upload")
    public List<Map<String,String>> upload(@RequestParam("file") MultipartFile file) throws Exception {
        List<Map<String, String>> temp = uploadService.upload(file);
        temp.remove(0);
        return temp;
    }
}
