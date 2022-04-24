package unieuroop.controller.serialization;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;



public final class Serialization {
    private Serialization() { }

    public static <X> void serialize(final String filePath, final X object) throws  IOException {
        final Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                final ObjectMapper mapper = ObjectMapperFactory.getMapper();
                try {
                    mapper.writeValue(new File(filePath), object);
                } catch (StreamWriteException e) {
                    e.printStackTrace();
                } catch (DatabindException e1) {
                    e1.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
        t1.start();
    }

    public static <X> X deserialize(final String filePath, final TypeReference<? extends X> typeReference) throws IOException {
        final ObjectMapper mapper = ObjectMapperFactory.getMapper();
        return (X) mapper.readValue(new File(filePath), typeReference);
    }

}
