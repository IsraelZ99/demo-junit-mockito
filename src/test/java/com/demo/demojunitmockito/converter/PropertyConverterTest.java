package com.demo.demojunitmockito.converter;

import com.demo.demojunitmockito.dto.PropertyDTO;
import com.demo.demojunitmockito.entity.PropertyEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PropertyConverterTest {

    @InjectMocks
    private PropertyConverter propertyConverter;

    @Test
    void testConvertDTOToEntity_Success() {
        PropertyDTO dto = new PropertyDTO();
        dto.setTitle("Dummy");
        dto.setPrice(500.50);
        PropertyEntity entity = propertyConverter.convertDTOToEntity(dto);
        // TODO: We can add another fields to testing
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getTitle(), entity.getTitle());
    }

    @Test
    void testConvertEntityToDTO_Success() {
        PropertyEntity entity = new PropertyEntity();
        entity.setTitle("Dummy");
        entity.setPrice(500.50);
        PropertyDTO dto = propertyConverter.convertEntityToDTO(entity);
        // TODO: We can add another fields to testing
        assertEquals(entity.getPrice(), dto.getPrice());
        assertEquals(entity.getTitle(), dto.getTitle());
    }

}
