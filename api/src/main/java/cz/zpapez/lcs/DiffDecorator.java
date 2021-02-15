package cz.zpapez.lcs;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cz.zpapez.lcs.dto.DiffResponseDto;
import cz.zpapez.lcs.model.DiffModel;

@Component
public class DiffDecorator {

    public List<DiffResponseDto> mapToResponseDtoList(List<DiffModel> result) {
        return result.stream()
                .map(model -> {
                    DiffResponseDto dto;
                    switch (model.getType()) {
                    case ADDED:
                        dto = new DiffResponseDto("+", model.getStringBuilder().toString());
                        break;
                    case REMOVED:
                        dto = new DiffResponseDto("-", model.getStringBuilder().toString());
                        break;
                    default:
                        dto = new DiffResponseDto("", model.getStringBuilder().toString());
                        break;
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
