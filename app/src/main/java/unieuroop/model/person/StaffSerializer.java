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

import javafx.util.Pair;
import unieuroop.controller.serialization.ObjectMapperFactory;

public final class StaffSerializer extends JsonSerializer<StaffImpl> {

    @Override
    public void serialize(final StaffImpl value, final JsonGenerator generator, final SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        final var keys = new ArrayList<DayOfWeek>();
        final var values = new ArrayList<Pair<LocalTime, LocalTime>>();
        value.getWorkingTimeTable().entrySet().forEach(e -> {
            keys.add(e.getKey());
            values.add(e.getValue());
        });
        generator.writeStartObject();
        generator.writeStringField("name", value.getPerson().getName());
        generator.writeStringField("surname", value.getPerson().getSurname());
        generator.writeStringField("email", value.getEmail());
        generator.writeNumberField("id", value.getPerson().getCode());
        generator.writeNumberField("password", value.getPassword());
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        generator.writeFieldName("birthday");
        generator.writeRawValue(mapper.writeValueAsString(value.getPerson().getBirthdayDate()));
        generator.writeArrayFieldStart("days");
        for (final var el : keys) {
          generator.writeObject(el);
        }
        generator.writeEndArray();
        generator.writeArrayFieldStart("startTimes");
        for (final var el : values) {
          generator.writeObject(el.getKey());
        }
        generator.writeEndArray();
        generator.writeArrayFieldStart("endTimes");
        for (final var el : values) {
          generator.writeObject(el.getValue());
        }
        generator.writeEndArray();
        generator.writeEndObject();
    }

}
