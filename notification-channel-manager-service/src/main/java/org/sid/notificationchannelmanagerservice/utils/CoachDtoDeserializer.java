package org.sid.notificationchannelmanagerservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.sid.notificationchannelmanagerservice.dto.CoachDTO;

import java.util.Map;


public class CoachDtoDeserializer implements Deserializer<CoachDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Rien à faire ici
    }

    @Override
    public CoachDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, CoachDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Erreur lors de la désérialisation de CoachDto", e);
        }
    }

    @Override
    public void close() {
        // Rien à faire ici
    }
}
