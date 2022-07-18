package app.backend;

import app.backend.models.Parameters;
import app.backend.models.ParametersWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileExporterTest {

    @org.junit.jupiter.api.Test
    void export() {

        ObjectMapper mapper = new ObjectMapper();
        Parameters parameters;
        try {
            parameters = mapper.readValue(new File("configs/parameters.json"), Parameters.class);
            ParametersWrapper.getInstance().getParameterList().addAll(parameters.getParameterList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileExporter fileExporter = new FileExporter();
            String exportResultStr = fileExporter.export(parameters.getParameterList());
            assertEquals("061023000f001001800000961045", exportResultStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void process() {

        ObjectMapper mapper = new ObjectMapper();
        Parameters parameters;
        try {
            parameters = mapper.readValue(new File("configs/parameters.json"), Parameters.class);
            ParametersWrapper.getInstance().getParameterList().addAll(parameters.getParameterList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileExporter fileExporter = new FileExporter();
            byte[] exportResult = fileExporter.process(parameters.getParameterList());
            StringBuilder sb = new StringBuilder(exportResult.length * 2);
            for(byte b: exportResult)
                sb.append(String.format("%02x", b));
            System.out.println("Exported package is: " + sb.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}