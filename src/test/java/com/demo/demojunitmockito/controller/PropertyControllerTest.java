package com.demo.demojunitmockito.controller;

import com.demo.demojunitmockito.dto.PropertyDTO;
import com.demo.demojunitmockito.service.PropertyService;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    // Mockito is going to create a proxy object of PropertyController
    // and inject to our PropertyControllerTest
    @InjectMocks
    private PropertyController propertyController;

    // Mockito will give memory to PropertyService it will inject this
    // proxy/dummy PropertyService object inside the proxy object PropertyController
    @Mock
    @Qualifier("h2-database")
    private PropertyService propertyService;

    @Test
    @DisplayName("Test Success Scenario for Saving new Property")
    void testSaveProperty() {
        PropertyDTO dto = new PropertyDTO();
        dto.setTitle("Dummy title");

        PropertyDTO savedProperty = new PropertyDTO();
        savedProperty.setId(1L);
        savedProperty.setTitle(dto.getTitle());

        // Do not make the actual call for propertyService.saveProperty(dto)
        // inside controller rather return dummy object
        Mockito.when(propertyService.saveProperty(dto)).thenReturn(savedProperty);
        ResponseEntity<PropertyDTO> entity = propertyController.saveProperty(dto);
        Assertions.assertNotNull(entity.getBody().getId());
        assertEquals(HttpStatus.CREATED.value(),
                entity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for fetching all properties")
    void testGetProperties() {
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        PropertyDTO dto = new PropertyDTO();
        dto.setId(1L);
        dto.setTitle("Dummy Property");
        propertyDTOList.add(dto);

        // Do not make the actual call for propertyService.getAllProperties()
        // inside controller rather return dummy List<PropertyDTO> in the controller
        Mockito.when(propertyService.getAllProperties()).thenReturn(propertyDTOList);
        ResponseEntity<List<PropertyDTO>> responseEntity =
                propertyController.getAllProperties();
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for updating only price of the property")
    void testUpdatePropertyPrice() {
        PropertyDTO dto = new PropertyDTO();
        dto.setPrice(500.50);
        Mockito.when(propertyService.updatePropertyPrice(Mockito.any(),
                        Mockito.anyLong()))
                .thenReturn(dto);
        ResponseEntity<PropertyDTO> response = propertyController.updatePropertyPrice(dto, 1L);
        assertEquals(500.50, response.getBody().getPrice());
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for updating only description of the property")
    void testUpdatePropertyDescription() {
        PropertyDTO dto = new PropertyDTO();
        dto.setDescription("My description");
        Mockito.when(propertyService.updatePropertyDescription(Mockito.any(), Mockito.anyLong()))
                .thenReturn(dto);
        ResponseEntity<PropertyDTO> response = propertyController.updatePropertyDescription(dto, 1L);
        assertEquals("My description", response.getBody().getDescription());
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for updating property")
    void testUpdateProperty() {
        PropertyDTO dto = new PropertyDTO();
        dto.setOwnerEmail("israel@gmail.com");

        PropertyDTO updated = dto;
        updated.setId(1L);
        dto.setOwnerEmail("jose@gmail.com");

        Mockito.when(propertyService.updateProperty(dto, 1L)).thenReturn(updated);
        ResponseEntity<PropertyDTO> response = propertyController.updateProperty(dto, 1L);
        Assertions.assertNotEquals("israel@gmail.com", response.getBody().getOwnerEmail());
        assertEquals("jose@gmail.com", response.getBody().getOwnerEmail());
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Success Scenario for delete property by id")
    void deleteProperty() {
        ResponseEntity<Void> response = propertyController.deletePropertyById(1L);
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatusCodeValue());

    }

}
