package unieuroop.controller.serialization;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public final class Serialization {
    private Serialization() { }
    public static <X> void serialize(final String filePath, final X obj) {
        final Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
                gson.toJson(obj, writer);
            } catch (IOException e) {
                e.printStackTrace();
        } 
    }
}
