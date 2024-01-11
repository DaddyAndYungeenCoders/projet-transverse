package com.simulator.webserver.service.interfaces.mappers;

import com.simulator.webserver.dto.SensorDTO;
import com.simulator.webserver.models.SensorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SensorMapper {
    SensorMapper MAPPER = Mappers.getMapper(SensorMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "coords", source = "coords")
    SensorDTO toDTO(SensorEntity sensorEntity);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "coords", source = "coords")
    SensorEntity toEntity(SensorDTO sensorDTO);
}
