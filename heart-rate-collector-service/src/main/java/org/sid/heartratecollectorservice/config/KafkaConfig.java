package org.sid.heartratecollectorservice.config;



import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.sid.heartratecollectorservice.dto.HrSensorDTO;
import org.sid.heartratecollectorservice.utils.HrSensorDTOSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public NewTopic kafkaTopicHeartRate(){
        return TopicBuilder.name("hr-data-collector").build();
    }

    public Map<String, Object> producerConfig(){
        Map<String,Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, HrSensorDTOSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, HrSensorDTO> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, HrSensorDTO> kafkaTemplateInjection(ProducerFactory<String,HrSensorDTO> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }

}
