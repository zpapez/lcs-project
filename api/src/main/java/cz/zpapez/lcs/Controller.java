package cz.zpapez.lcs;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {

    private final MyService service;

    @GetMapping("/diff")
    public String getResource() {

        service.fetchData();

        return "OK result";
    }

    @PostMapping("/upload")
    public String submit(@RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) throws IOException {

        String result = service.diff(file1.getInputStream(), file2.getInputStream());

        return result;
    }

}
