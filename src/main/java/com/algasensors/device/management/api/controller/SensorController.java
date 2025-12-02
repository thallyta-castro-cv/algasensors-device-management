package com.algasensors.device.management.api.controller;

import com.algasensors.device.management.models.dto.mapper.SensorMapper;
import com.algasensors.device.management.models.dto.request.SensorRequestDTO;
import com.algasensors.device.management.models.dto.response.SensorResponseDTO;
import com.algasensors.device.management.models.entity.Sensor;
import com.algasensors.device.management.models.entity.SensorId;
import com.algasensors.device.management.repository.SensorRepository;
import com.algasensors.device.management.utils.IdGenerator;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    @GetMapping("{sensorId}")
    public SensorResponseDTO getOne(@PathVariable TSID sensorId){
        Sensor sensor = sensorRepository.findById(new SensorId(sensorId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return sensorMapper.convertToModel(sensor);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorResponseDTO create(@RequestBody SensorRequestDTO input) {
        Sensor sensor = Sensor.builder()
                .id(new SensorId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enabled(false)
                .build();

        sensorRepository.saveAndFlush(sensor);

        return sensorMapper.convertToModel(sensor);
    }
}
