package org.sid.emergencynotificationagentservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.sid.emergencynotificationagentservice.dto.CoachDTO;

import java.util.Map;

public class CoachDtoSerialize implements Serializer<CoachDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Rien à faire ici
    }

    @Override
    public byte[] serialize(String topic, CoachDTO data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Erreur lors de la sérialisation de CoachDto", e);
        }
    }

    @Override
    public void close() {
        // Rien à faire ici
    }
}
