package com.demo.demojunitmockito.converter;

import com.demo.demojunitmockito.dto.PropertyDTO;
import com.demo.demojunitmockito.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {

    public PropertyEntity convertDTOToEntity(PropertyDTO propertyDTO) {
        PropertyEntity pE = new PropertyEntity();
        pE.setTitle(propertyDTO.getTitle());
        pE.setAddress(propertyDTO.getAddress());
        pE.setOwnerEmail(propertyDTO.getOwnerEmail());
        pE.setOwnerName(propertyDTO.getOwnerName());
        pE.setPrice(propertyDTO.getPrice());
        pE.setDescription(propertyDTO.getDescription());
        return pE;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity entity){
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(entity.getId());
        propertyDTO.setTitle(entity.getTitle());
        propertyDTO.setAddress(entity.getAddress());
        propertyDTO.setOwnerEmail(entity.getOwnerEmail());
        propertyDTO.setOwnerName(entity.getOwnerName());
        propertyDTO.setPrice(entity.getPrice());
        propertyDTO.setDescription(entity.getDescription());
        return propertyDTO;
    }

}
