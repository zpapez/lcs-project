package cz.zpapez.lcs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
