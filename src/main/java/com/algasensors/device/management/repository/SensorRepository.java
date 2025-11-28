package com.algasensors.device.management.repository;

import com.algasensors.device.management.models.entity.Sensor;
import com.algasensors.device.management.models.entity.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
}
