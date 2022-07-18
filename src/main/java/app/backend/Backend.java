package app.backend;

import app.backend.models.Parameters;
import app.backend.models.ParametersWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Backend implements Runnable {

    private Parameters parameters;
    private final FileExporter fileExporter = new FileExporter();

    public Backend() throws IOException {
    }

    @Override
    public void run() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            parameters = mapper.readValue(new File("configs/parameters.json"), Parameters.class);
            ParametersWrapper.getInstance().getParameterList().addAll(parameters.getParameterList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileExporter.export(parameters.getParameterList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
