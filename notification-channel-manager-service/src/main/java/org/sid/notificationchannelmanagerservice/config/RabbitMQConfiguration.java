package org.sid.notificationchannelmanagerservice.config;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {


    private static final String ROUTING_C = "routing.Notif";

    /*@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setAddresses("127.0.0.1:15672,127.0.0.1:15673");
        //connectionFactory.setChannelCacheSize(10);
        return connectionFactory;
    }*/



    @Bean
    Queue queueC(){
        return new Queue("NotifQueue",false);

    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");
    }



    @Bean
    Binding bindingC(Queue queueC,DirectExchange exchange){
        return BindingBuilder.bind(queueC)
                .to(exchange)
                .with(ROUTING_C);
    }

    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }



}
