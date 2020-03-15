package service;

import deserialize.VendasDeserialize;
import model.Vendas;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ProcessaVendas {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, VendasDeserialize.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"processa-vendas-group");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        try (KafkaConsumer<String, Vendas> consumer = new KafkaConsumer<String, Vendas>(properties)) {
            consumer.subscribe(Collections.singletonList("vendas"));
            while (true) {
                ConsumerRecords<String, Vendas> vendasConsumerRecords = consumer.poll(Duration.ofMillis(200));
                for (ConsumerRecord<String, Vendas> venda : vendasConsumerRecords) {
                    Vendas ingresso = venda.value();
                    System.out.println(ingresso);
                }

            }
        }
    }
}
