package com.demo.demojunitmockito.service.impl;

import com.demo.demojunitmockito.dto.PropertyDTO;
import com.demo.demojunitmockito.service.PropertyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fake")
public class PropertyServiceFakeImpl implements PropertyService {


    @Override
    public PropertyDTO saveProperty(PropertyDTO property) {
        System.out.println(property.getTitle());
        return null;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return null;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        return null;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        return null;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        return null;
    }

    @Override
    public void deletePropertyById(Long propertyId) {

    }
}
