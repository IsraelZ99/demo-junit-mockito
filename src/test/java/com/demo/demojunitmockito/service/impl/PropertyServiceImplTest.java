package com.demo.demojunitmockito.service.impl;

import com.demo.demojunitmockito.converter.PropertyConverter;
import com.demo.demojunitmockito.dto.PropertyDTO;
import com.demo.demojunitmockito.entity.PropertyEntity;
import com.demo.demojunitmockito.exception.BusinessException;
import com.demo.demojunitmockito.repository.PropertyRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceImplTest {

    @InjectMocks
    @Qualifier("h2-database")
    private PropertyServiceImpl propertyService;

    @Mock
    private PropertyConverter propertyConverter;
    @Mock
    private PropertyRepository propertyRepository;

    @Test
    void testSaveProperty_Success() {
        PropertyDTO dto = new PropertyDTO();
        dto.setTitle("Dummy title");

        PropertyEntity entity = new PropertyEntity();
        entity.setTitle("Dummy");

        PropertyEntity savedEntity = new PropertyEntity();
        savedEntity.setId(1L);
        savedEntity.setTitle("Dummy");

        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setId(1L);
        savedDTO.setTitle("Dummy");

        when(propertyConverter.convertDTOToEntity(Mockito.any()))
                .thenReturn(entity);
        when(propertyRepository.save(Mockito.any()))
                .thenReturn(savedEntity);
        when(propertyConverter.convertEntityToDTO(Mockito.any()))
                .thenReturn(savedDTO);
        PropertyDTO result = propertyService.saveProperty(dto);
        assertEquals(savedDTO.getId(), result.getId());
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void testGetAllProperties_Success() {
        List<PropertyEntity> propertyEntities = new ArrayList<>();
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setId(1L);
        propertyEntity.setTitle("Dummy");
        propertyEntities.add(propertyEntity);

        PropertyDTO dto = new PropertyDTO();
        dto.setId(1L);
        dto.setTitle("Dummy");

        when((List<PropertyEntity>) propertyRepository.findAll())
                .thenReturn(propertyEntities);
        when(propertyConverter.convertEntityToDTO(Mockito.any()))
                .thenReturn(dto);
        List<PropertyDTO> listPropertyDTO = propertyService.getAllProperties();
        assertEquals(1, listPropertyDTO.size());
    }

    @Test
    void testUpdateProperty_Success() {
        PropertyDTO dto = new PropertyDTO();
        dto.setId(1L);
        dto.setTitle("Dummy");
        dto.setPrice(1500.0);
        dto.setAddress("Camp");
        dto.setDescription("Description");
        dto.setOwnerEmail("israel@gmail.com");
        dto.setOwnerName("Israel Garcia");

        PropertyEntity pe = new PropertyEntity();
        pe.setId(1L);
        pe.setTitle("Dummies");
        pe.setPrice(1500.0);
        pe.setAddress("Campaign");
        pe.setDescription("Description 2");
        pe.setOwnerEmail("isra@gmail.com");
        pe.setOwnerName("Israel");

        when(propertyRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(pe));
        when(propertyConverter.convertEntityToDTO(Mockito.any()))
                .thenReturn(dto);

        PropertyDTO updateProperty = propertyService.updateProperty(dto, 1L);
        assertEquals(dto.getTitle(), updateProperty.getTitle());
        assertEquals(dto.getPrice(), updateProperty.getPrice());
        assertEquals(dto.getAddress(), updateProperty.getAddress());
        assertEquals(dto.getDescription(), updateProperty.getDescription());
        assertEquals(dto.getOwnerEmail(), updateProperty.getOwnerEmail());
        assertEquals(dto.getOwnerName(), updateProperty.getOwnerName());
    }

    @Test
    void testUpdatePropertyDescription_Success() {
        PropertyDTO dto = new PropertyDTO();
        dto.setDescription("Updated Description");

        PropertyEntity pe = new PropertyEntity();
        pe.setId(1L);
        pe.setTitle("Dummies");
        pe.setPrice(1500.0);
        pe.setAddress("Campaign");
        pe.setDescription("Description 2");
        pe.setOwnerEmail("isra@gmail.com");
        pe.setOwnerName("Israel");

        PropertyDTO dtoUpdated = new PropertyDTO();
        dtoUpdated.setId(1L);
        dtoUpdated.setTitle("Dummies");
        dtoUpdated.setPrice(1500.0);
        dtoUpdated.setAddress("Campaign");
        dtoUpdated.setDescription(dto.getDescription());
        dtoUpdated.setOwnerEmail("isra@gmail.com");
        dtoUpdated.setOwnerName("Israel");

        when(propertyRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(pe));
        when(propertyConverter.convertEntityToDTO(Mockito.any()))
                .thenReturn(dtoUpdated);

        PropertyDTO result = propertyService.updatePropertyDescription(dto, 1L);
        assertEquals(dto.getDescription(), result.getDescription());
    }

    @Test
    void testUpdatePropertyDescription_Failure() {
        PropertyDTO dto = new PropertyDTO();
        when(propertyRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        BusinessException exception = Assertions.assertThrows(BusinessException.class,
                () -> {
                    PropertyDTO result = propertyService.updatePropertyDescription(dto, 1L);
                });
        Assertions.assertEquals("No Property found for update", exception.getMessage());
    }

    @Test
    void testUpdatePropertyPrice_Success() {
        PropertyDTO dto = new PropertyDTO();
        dto.setPrice(500.50);

        PropertyEntity pe = new PropertyEntity();
        pe.setId(1L);
        pe.setTitle("Dummies");
        pe.setPrice(1500.0);
        pe.setAddress("Campaign");
        pe.setDescription("Description 2");
        pe.setOwnerEmail("isra@gmail.com");
        pe.setOwnerName("Israel");

        PropertyDTO dtoUpdated = new PropertyDTO();
        dtoUpdated.setId(1L);
        dtoUpdated.setTitle("Dummies");
        dtoUpdated.setPrice(dto.getPrice());
        dtoUpdated.setAddress("Campaign");
        dtoUpdated.setDescription("Description 2");
        dtoUpdated.setOwnerEmail("isra@gmail.com");
        dtoUpdated.setOwnerName("Israel");

        when(propertyRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(pe));
        when(propertyConverter.convertEntityToDTO(Mockito.any()))
                .thenReturn(dtoUpdated);

        PropertyDTO result = propertyService.updatePropertyPrice(dto, 1L);
        assertEquals(dto.getPrice(), result.getPrice());
    }

    @Test
    void testDeletePropertyById_Success() {
        propertyService.deletePropertyById(1L);
    }

}
