package cz.zpapez.lcs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final MyService service;

    @GetMapping("/diff")
    public String getResource() {

        service.fetchData();

        return "OK result";
    }

    @PostMapping("/upload")
    public String submit(@RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) {


        log.warn("received {} and {}", file1.getName(), file2.getName());

        return "uploaded";
    }

}
