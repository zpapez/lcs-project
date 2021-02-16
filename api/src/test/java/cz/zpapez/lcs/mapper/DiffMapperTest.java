package cz.zpapez.lcs.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import cz.zpapez.lcs.dto.DiffResponseDto;
import cz.zpapez.lcs.model.DiffModel;
import cz.zpapez.lcs.model.DiffType;

public class DiffMapperTest {

    private DiffMapper mapper = new DiffMapper();

    @Test
    public void itShouldMapEmptyModelToEmptyList() {
        List<DiffResponseDto> response = mapper.mapToResponseDtoList(Collections.emptyList());
        assertEquals(0, response.size());
    }

    @Test
    public void itShouldMapAddedTypeToAdditionDto() {
        DiffModel addition = mock(DiffModel.class);
        when(addition.getType()).thenReturn(DiffType.ADDED);
        when(addition.getStringBuilder()).thenReturn(new StringBuilder("addition"));

        List<DiffResponseDto> response = mapper.mapToResponseDtoList(List.of(addition ));

        assertEquals(1, response.size());
        assertEquals("+", response.get(0).getType());
        assertEquals("addition", response.get(0).getString());
    }

    @Test
    public void itShouldMapRemovedTypeToRemovalDto() {
        DiffModel addition = mock(DiffModel.class);
        when(addition.getType()).thenReturn(DiffType.REMOVED);
        when(addition.getStringBuilder()).thenReturn(new StringBuilder("removal"));

        List<DiffResponseDto> response = mapper.mapToResponseDtoList(List.of(addition ));

        assertEquals(1, response.size());
        assertEquals("-", response.get(0).getType());
        assertEquals("removal", response.get(0).getString());
    }

    @Test
    public void itShouldMapMatchedTypeToMatchingDto() {
        DiffModel addition = mock(DiffModel.class);
        when(addition.getType()).thenReturn(DiffType.MATCHED);
        when(addition.getStringBuilder()).thenReturn(new StringBuilder("matching"));

        List<DiffResponseDto> response = mapper.mapToResponseDtoList(List.of(addition ));

        assertEquals(1, response.size());
        assertEquals("", response.get(0).getType());
        assertEquals("matching", response.get(0).getString());
    }
}
