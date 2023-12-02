package pers.sea.shield.batch.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


/**
 * jackson java8 LocalDateTime parse Test
 *
 * @author moon on 12/2/2023
 */
public class Java8TimeParseTest {

    @Test
    public void whenSerializingJava8Date_thenCorrect()
            throws JsonProcessingException {
        LocalDateTime date = LocalDateTime.of(2014, 12, 20, 2, 30);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String result = mapper.writeValueAsString(date);
        assertThat(result, containsString("2014-12-20T02:30"));
    }
}
