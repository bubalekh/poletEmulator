package app.backend;

import app.backend.models.Parameters;
import app.backend.models.ParametersWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileExporterTest {

    private Parameters parameters;

    @org.junit.jupiter.api.Test
    void test() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            parameters = mapper.readValue(new File("target/test.json"), Parameters.class);
            ParametersWrapper.getInstance().getParameterList().addAll(parameters.getParameterList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileExporter fileExporter = new FileExporter();
            String exportResultStr = fileExporter.Test(parameters.getParameterList());
            assertEquals("061023000f001001800000961045", exportResultStr);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}