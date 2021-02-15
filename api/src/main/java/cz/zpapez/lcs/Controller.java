package cz.zpapez.lcs;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cz.zpapez.lcs.dto.DiffResponseDto;
import cz.zpapez.lcs.model.DiffModel;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class Controller {

    private final MyService service;
    private final DiffDecorator mapper;

    @GetMapping("/diff")
    public List<DiffResponseDto> getResource() {

        service.fetchData();

        return List.of(new DiffResponseDto("removed", "abc </span>  def"));
    }

    @PostMapping("/upload")
    public List<DiffResponseDto> submit(@RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2) throws IOException {

        List<DiffModel> result = service.diff(file1.getInputStream(), file2.getInputStream());

        return mapper.mapToResponseDtoList(result);
    }

}
