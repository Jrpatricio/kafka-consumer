package deserialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import model.Vendas;
import org.apache.kafka.common.serialization.Deserializer;

public class VendasDeserialize implements Deserializer<Vendas> {

    @SneakyThrows
    public Vendas deserialize(String topic, byte[] data) {
        return new ObjectMapper().readValue(data,Vendas.class);

    }
}
