package cz.zpapez.lcs.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class DiffModel {
    private final StringBuilder stringBuilder = new StringBuilder();
    private final DiffType type;
}
