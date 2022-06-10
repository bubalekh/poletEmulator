package app.backend;

import app.backend.models.Parameters;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Backend implements Runnable {
    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();
        Parameters parameters;
        try {
            parameters = mapper.readValue(new File("target/test.json"), Parameters.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(parameters.getParameterList().get(0).getDescription());
    }
}
