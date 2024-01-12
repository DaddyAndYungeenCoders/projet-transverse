package com.simulator.webserver.service.interfaces.mappers;

import com.simulator.webserver.dto.FireStationDTO;
import com.simulator.webserver.models.FireStationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FireStationMapper {
    FireStationMapper MAPPER = Mappers.getMapper(FireStationMapper.class);
    
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "coords", source = "coords")
    FireStationDTO toDTO(FireStationEntity entity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "address", source = "address")
    @Mapping(target = "coords", source = "coords")
    FireStationEntity toEntity(FireStationDTO dto);
}
