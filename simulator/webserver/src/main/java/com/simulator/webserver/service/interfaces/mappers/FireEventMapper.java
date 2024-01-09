package com.simulator.webserver.service.interfaces.mappers;

import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FireEventMapper {
    FireEventMapper MAPPER = Mappers.getMapper(FireEventMapper.class);
    @Mapping(source = "real_intensity", target = "realIntensity")
    @Mapping(source = "start_date", target = "startDate")
    @Mapping(source = "end_date", target = "endDate")
    @Mapping(source = "_real", target = "real")
    FireEventDTO toDTO(FireEventEntity fireEventEntity);

    @Mapping(source = "realIntensity", target = "real_intensity")
    @Mapping(source = "startDate", target = "start_date")
    @Mapping(source = "endDate", target = "end_date")
    @Mapping(source = "real", target = "_real")
    FireEventEntity toEntity(FireEventDTO fireEventDTO);
}