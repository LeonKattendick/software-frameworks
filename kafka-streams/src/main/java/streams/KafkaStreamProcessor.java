package streams;

import models.GameData;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@Configurable
public class KafkaStreamProcessor {

    @Bean
    public Function<KStream<String, GameData>, KStream<String, GameData>> processor(){
        return kstream -> kstream.filter((key, gamedata) -> {
            if(gamedata.smthg == ""){

            }else{

            }
            return gamedata.smthg == "";
        });
    }
}
