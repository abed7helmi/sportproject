package org.sid.heartratecollectorservice.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import org.sid.heartratecollectorservice.dto.HrSensorDTO;

import java.util.Map;

public class HrSensorDTOSerializer implements Serializer<HrSensorDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // Rien à faire ici
    }

    @Override
    public byte[] serialize(String topic, HrSensorDTO data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Erreur lors de la sérialisation de HrSensorDTO", e);
        }
    }

    @Override
    public void close() {
        // Rien à faire ici
    }
}
