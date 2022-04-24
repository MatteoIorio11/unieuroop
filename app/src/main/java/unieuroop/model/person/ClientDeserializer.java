package unieuroop.model.person;

import java.io.IOException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import unieuroop.controller.serialization.ObjectMapperFactory;

public final class ClientDeserializer extends JsonDeserializer<Client> {

    @Override
    public ClientImpl deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JacksonException {
        final JsonNode node = p.getCodec().readTree(p);
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        final var person = mapper.treeToValue(node.get("basePerson"), BasePersonImpl.class);
        return new ClientImpl(person.getName(), person.getSurname(), person.getBirthdayDate(), person.getCode());
    }

}
