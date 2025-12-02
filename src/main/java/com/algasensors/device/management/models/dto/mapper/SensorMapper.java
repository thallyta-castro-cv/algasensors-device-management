package com.algasensors.device.management.models.dto.mapper;

import com.algasensors.device.management.models.dto.response.SensorResponseDTO;
import com.algasensors.device.management.models.entity.Sensor;
import org.springframework.stereotype.Component;

@Component
public class SensorMapper {

    public SensorMapper(){}

    public SensorResponseDTO convertToModel(Sensor sensor) {
        return SensorResponseDTO.builder()
                .id(sensor.getId().getValue())
                .name(sensor.getName())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .protocol(sensor.getProtocol())
                .model(sensor.getModel())
                .enabled(sensor.getEnabled())
                .build();
    }
}
