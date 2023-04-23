package org.sid.emergencynotificationagentservice.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.sid.emergencynotificationagentservice.dto.HrSensorDTO;

import java.util.Map;

public class HrSensorDTODeserializer implements Deserializer<HrSensorDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Rien à faire ici
    }

    @Override
    public HrSensorDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, HrSensorDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Erreur lors de la désérialisation de HrSensorDTO", e);
        }
    }

    @Override
    public void close() {
        // Rien à faire ici
    }
}

