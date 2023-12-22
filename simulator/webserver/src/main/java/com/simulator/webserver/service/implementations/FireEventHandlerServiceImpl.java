package com.simulator.webserver.service.implementations;

import com.simulator.webserver.dto.FireEventDTO;
import com.simulator.webserver.models.FireEventEntity;
import com.simulator.webserver.service.interfaces.FireEventHandlerService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class FireEventHandlerServiceImpl implements FireEventHandlerService {

    @Override
    public Optional<FireEventEntity> createFireEventFromView(FireEventDTO fireEventDTO) {
        FireEventEntity result = new FireEventEntity();
        result.setCoords(fireEventDTO.getCoords());
        result.setStart_date(Date.valueOf(LocalDate.now()));
        result.setIs_real(true);
        result.setReal_intensity(8);
        return Optional.ofNullable(result);
    }
}
