package unieuroop.model.person;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javafx.util.Pair;

public final class StaffSerializer extends JsonSerializer<Staff> {

    @Override
    public void serialize(final Staff value, final JsonGenerator gen, final SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        final var k = new ArrayList<DayOfWeek>();
        final var v = new ArrayList<Pair<LocalTime, LocalTime>>();
        value.getWorkingTimeTable().entrySet().forEach(e -> {
            k.add(e.getKey());
            v.add(e.getValue());
        });
        gen.writeStartObject();
        gen.writeStringField("name", value.getName());
        gen.writeStringField("surname", value.getSurname());
        gen.writeStringField("email", value.getEmail());
        gen.writeNumberField("id", value.getId());
        gen.writeNumberField("password", value.getPassword());
        final ObjectMapper mapper = (ObjectMapper) gen.getCodec();
        gen.writeFieldName("birthday");
        final String stringValue = mapper.writeValueAsString(value.getBirthdayDate());
        gen.writeRawValue(stringValue);
        gen.writeArrayFieldStart("days");
        for (final var el : k) {
          gen.writeObject(el);
        }
        gen.writeEndArray();
        gen.writeArrayFieldStart("startTimes");
        for (final var el : v) {
          gen.writeObject(el.getKey());
        }
        gen.writeEndArray();
        gen.writeArrayFieldStart("endTimes");
        for (final var el : v) {
          gen.writeObject(el.getValue());
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }

}
