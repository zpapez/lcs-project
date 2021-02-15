package cz.zpapez.lcs.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DiffResponseDto {

    private final String type;
    private final String string;
}
