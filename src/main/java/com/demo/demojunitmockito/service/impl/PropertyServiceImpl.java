package com.demo.demojunitmockito.service.impl;

import com.demo.demojunitmockito.converter.PropertyConverter;
import com.demo.demojunitmockito.dto.PropertyDTO;
import com.demo.demojunitmockito.entity.PropertyEntity;
import com.demo.demojunitmockito.exception.BusinessException;
import com.demo.demojunitmockito.repository.PropertyRepository;
import com.demo.demojunitmockito.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("h2-database")
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity pE = propertyConverter.convertDTOToEntity(propertyDTO);
        pE = propertyRepository.save(pE);
        return propertyConverter.convertEntityToDTO(pE);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> propertyList =
                (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();
        for (PropertyEntity pe : propertyList) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (propertyEntity.isPresent()) {
            PropertyEntity pE = propertyEntity.get();
            pE.setTitle(propertyDTO.getTitle());
            pE.setAddress(propertyDTO.getAddress());
            pE.setOwnerEmail(propertyDTO.getOwnerEmail());
            pE.setOwnerName(propertyDTO.getOwnerName());
            pE.setPrice(propertyDTO.getPrice());
            pE.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pE);
            propertyRepository.save(pE);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) throws BusinessException {
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (propertyEntity.isPresent()) {
            PropertyEntity pE = propertyEntity.get();
            pE.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityToDTO(pE);
            propertyRepository.save(pE);
        } else {
            throw new BusinessException("No Property found for update");
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;
        if (propertyEntity.isPresent()) {
            PropertyEntity pE = propertyEntity.get();
            pE.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityToDTO(pE);
            propertyRepository.save(pE);
        }
        return dto;
    }

    @Override
    public void deletePropertyById(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

}
