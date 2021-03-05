package com.zup.transacao.kafka;

import com.zup.transacao.kafka.consumer.TransacaoConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Value("${kafka.transacao.server}")
    private String kafkaServer;

    @Value("${kafka.transacao.id}")
    private String kafkaId;

    @Bean
    public ConsumerFactory<String, TransacaoConsumer> consumerConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaId);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), getDeserializer());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,
            TransacaoConsumer>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TransacaoConsumer> listener =
                new ConcurrentKafkaListenerContainerFactory<>();
        listener.setConsumerFactory(consumerConfig());
        return listener;
    }

    public JsonDeserializer<TransacaoConsumer> getDeserializer() {
        JsonDeserializer<TransacaoConsumer> json = new JsonDeserializer<>(TransacaoConsumer.class, false);
        json.addTrustedPackages("*");

        return json;
    }

}
