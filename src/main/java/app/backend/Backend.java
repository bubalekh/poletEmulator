package app.backend;

import app.backend.models.Parameters;
import app.backend.models.ParametersWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Backend implements Runnable {

    private Parameters parameters;

    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            parameters = mapper.readValue(new File("target/test.json"), Parameters.class);
            ParametersWrapper.getInstance().getParameterList().addAll(parameters.getParameterList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
