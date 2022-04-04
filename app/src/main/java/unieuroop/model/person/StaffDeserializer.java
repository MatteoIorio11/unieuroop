package unieuroop.model.person;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javafx.util.Pair;
import unieuroop.model.department.DepartmentImpl;
import unieuroop.model.product.Product;

public class StaffDeserializer extends JsonDeserializer<Staff>{

    @Override
    public Staff deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final JsonNode node = p.getCodec().readTree(p);
        final String name = node.get("name").asText();
        final String surname = node.get("surname").asText();
        final String email = node.get("email").asText();
        final int id = node.get("id").asInt();
        final int password = node.get("password").asInt();
        final var days = new ArrayList<DayOfWeek>();
        final var endTimes = new ArrayList<LocalTime>();
        final var startTimes = new ArrayList<LocalTime>();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        final LocalDate birthday = mapper.treeToValue(node.get("birthday"), LocalDate.class);
        for (final var el : node.get("days")) {
            days.add(mapper.treeToValue(el, DayOfWeek.class));
        }
        for (final var el : node.get("startTimes")) {
            startTimes.add(mapper.treeToValue(el, LocalTime.class));
        }
        for (final var el : node.get("endTimes")) {
            endTimes.add(mapper.treeToValue(el, LocalTime.class));
        }
        final var m = new HashMap<DayOfWeek, Pair<LocalTime, LocalTime>>();
        for (int i = 0; i < endTimes.size(); i++) {
            m.put(days.get(i), new Pair<>(startTimes.get(i), endTimes.get(i)));
        }
        return new Staff(name, surname, birthday, id, email, password, m);
    }

}
