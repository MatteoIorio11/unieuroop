package unieuroop.model.person;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public final  class ClientSerializer  extends JsonSerializer<Client> {

    @Override
    public void serialize(final Client value, final JsonGenerator gen, 
            final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("basePerson", value.getPerson());
        gen.writeEndObject();
    }

}
