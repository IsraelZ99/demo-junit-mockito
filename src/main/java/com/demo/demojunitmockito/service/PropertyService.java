package com.demo.demojunitmockito.service;

import com.demo.demojunitmockito.dto.PropertyDTO;
import com.demo.demojunitmockito.exception.BusinessException;

import java.util.List;

public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO property);
    List<PropertyDTO> getAllProperties();
    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);
    PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) throws BusinessException;
    PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId);
    void deletePropertyById(Long propertyId);
}
